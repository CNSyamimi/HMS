package hms_controller;

import hms_model.Maintenance;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MaintenancePendingRequestServlet")
public class MaintenancePendingRequestServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Add your database password here

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("admin_id");
        
        // Check if admin is logged in
        if (adminId == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Get pending maintenance requests
            List<Maintenance> pendingRequests = getPendingMaintenanceRequests();
            
            // Set attributes for JSP
            request.setAttribute("pendingRequests", pendingRequests);
            request.getRequestDispatcher("maintenancePendingRequest.jsp").forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving pending requests: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("admin_id");
        
        // Check if admin is logged in
        if (adminId == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        String action = request.getParameter("action");
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        
        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            if ("approve".equals(action)) {
                updateMaintenanceStatus(requestId, "In Progress", adminId);
            } else if ("reject".equals(action)) {
                updateMaintenanceStatus(requestId, "Rejected", adminId);
            }
            
            response.sendRedirect("MaintenancePendingRequestServlet");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating request status: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private List<Maintenance> getPendingMaintenanceRequests() throws SQLException {
        List<Maintenance> requests = new ArrayList<>();
        
        String sql = "SELECT m.*, s.sName as studentName, r.blockID " +
                     "FROM maintenance m " +
                     "LEFT JOIN student s ON m.studentID = s.studentID " +
                     "LEFT JOIN room r ON m.roomID = r.roomID " +
                     "WHERE m.status = 'Pending' " +
                     "ORDER BY m.request_date DESC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Maintenance request = new Maintenance();
                request.setRequestID(rs.getInt("requestID"));
                request.setRequest_date(rs.getDate("request_date"));
                request.setIssue_type(rs.getString("issue_type"));
                request.setDescription(rs.getString("description"));
                request.setStatus(rs.getString("status"));
                request.setResolution_date(rs.getDate("resolution_date"));
                request.setStudentID(rs.getInt("studentID"));
                request.setWardenID(rs.getInt("wardenID"));
                request.setRoomID(rs.getString("roomID"));
                requests.add(request);
            }
        }
        
        return requests;
    }

    private void updateMaintenanceStatus(int requestId, String status, int adminId) throws SQLException {
        String sql = "UPDATE maintenance SET status = ?, wardenID = ? WHERE requestID = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, adminId);
            stmt.setInt(3, requestId);
            stmt.executeUpdate();
        }
    }
}