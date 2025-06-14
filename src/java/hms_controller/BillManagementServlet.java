package hms_controller;

import hms_model.Bill;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/billManagement")
public class BillManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection parameters (consistent with LoginServlet)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        // Check if user is logged in and has appropriate role
        if (session.getAttribute("userId") == null || 
            (!"admin".equalsIgnoreCase(role) && !"warden".equalsIgnoreCase(role))) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String searchTerm = request.getParameter("search");
        List<Bill> billList = new ArrayList<>();
        String error = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                
                // Base query to get bill information with student and room details
                String query = "SELECT b.billID, b.billType, b.amount, b.status, b.dueDate, " +
                              "s.studentID, s.sName, s.sPho, s.sEmail, " +
                              "r.roomID, r.roomType, h.blockName " +
                              "FROM bill b " +
                              "JOIN student s ON b.studentID = s.studentID " +
                              "LEFT JOIN allocation a ON b.allocationID = a.allocationID " +
                              "LEFT JOIN room r ON a.roomID = r.roomID " +
                              "LEFT JOIN hostelblock h ON r.blockID = h.blockID";
                
                // Add search condition if search term exists
                if (searchTerm != null && !searchTerm.isEmpty()) {
                    query += " WHERE s.sName LIKE ? OR s.studentID LIKE ? OR s.sEmail LIKE ?";
                }
                
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    // Set parameters if searching
                    if (searchTerm != null && !searchTerm.isEmpty()) {
                        String likeTerm = "%" + searchTerm + "%";
                        pstmt.setString(1, likeTerm);
                        pstmt.setString(2, likeTerm);
                        pstmt.setString(3, likeTerm);
                    }
                    
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            Bill bill = new Bill();
                            bill.setBillID(rs.getInt("billID"));
                            bill.setBillType(rs.getString("billType"));
                            bill.setAmount(rs.getBigDecimal("amount"));
                            bill.setDueDate(rs.getDate("dueDate"));
                            bill.setStatus(rs.getString("status"));
                            bill.setStudentID(rs.getInt("studentID"));
                            
                            // Set display fields
                            bill.setStudentName(rs.getString("sName"));
                            bill.setMatricNo(String.valueOf(rs.getInt("studentID")));
                            bill.setPhoneNo(rs.getString("sPho"));
                            bill.setEmail(rs.getString("sEmail"));
                            bill.setRoomInfo(rs.getString("blockName"), rs.getString("roomType"), rs.getInt("roomID"));
                            
                            billList.add(bill);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            error = "Database driver not found";
        } catch (SQLException e) {
            error = "Database error: " + e.getMessage();
        }
        
        if (error != null) {
            request.setAttribute("error", error);
        }
        
        request.setAttribute("billList", billList);
        request.getRequestDispatcher("billManagement.jsp").forward(request, response);
    }
}