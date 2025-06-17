package hms_controller;

import hms_model.Bill;
import hms_model.Student;
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

@WebServlet("/ListStudentBillServlet")
public class ListStudentBillServlet extends HttpServlet {
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

        // Get student ID from request parameter
        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam == null || studentIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "Student ID is required");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            int studentId = Integer.parseInt(studentIdParam);
            
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Get student details
            Student student = getStudentById(studentId);
            if (student == null) {
                request.setAttribute("errorMessage", "Student not found");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            
            // Get all bills for the student
            List<Bill> bills = getBillsByStudentId(studentId);
            
            // Calculate summary statistics
            double totalPaid = calculateTotalPaid(bills);
            double totalUnpaid = calculateTotalUnpaid(bills);
            int paidCount = countByStatus(bills, "Paid");
            int unpaidCount = countByStatus(bills, "Unpaid");
            
            // Set attributes for JSP
            request.setAttribute("student", student);
            request.setAttribute("bills", bills);
            request.setAttribute("totalPaid", totalPaid);
            request.setAttribute("totalUnpaid", totalUnpaid);
            request.setAttribute("paidCount", paidCount);
            request.setAttribute("unpaidCount", unpaidCount);
            
            request.getRequestDispatcher("listStudentBill.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Student ID format");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving bill data: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private Student getStudentById(int studentId) throws SQLException {
        String sql = "SELECT * FROM student WHERE studentID = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setSName(rs.getString("sName"));
                student.setProgram(rs.getString("program"));
                student.setSemester(rs.getInt("semester"));
                student.setGender(rs.getString("gender"));
                student.setSEmail(rs.getString("sEmail"));
                student.setSPho(rs.getString("sPho"));
                return student;
            }
        }
        return null;
    }

    private List<Bill> getBillsByStudentId(int studentId) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        
        String sql = "SELECT * FROM bill WHERE studentID = ? ORDER BY dueDate DESC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getInt("billID"));
                bill.setBillType(rs.getString("billType"));
                bill.setAmount(rs.getBigDecimal("amount"));
                bill.setDueDate(rs.getDate("dueDate"));
                bill.setStatus(rs.getString("status"));
                bill.setStudentID(rs.getInt("studentID"));
                bill.setAllocationID(rs.getInt("allocationID"));
                bills.add(bill);
            }
        }
        
        return bills;
    }

    private double calculateTotalPaid(List<Bill> bills) {
        return bills.stream()
                .filter(b -> "Paid".equals(b.getStatus()))
                .mapToDouble(b -> b.getAmount().doubleValue())
                .sum();
    }

    private double calculateTotalUnpaid(List<Bill> bills) {
        return bills.stream()
                .filter(b -> "Unpaid".equals(b.getStatus()))
                .mapToDouble(b -> b.getAmount().doubleValue())
                .sum();
    }

    private int countByStatus(List<Bill> bills, String status) {
        return (int) bills.stream()
                .filter(b -> status.equals(b.getStatus()))
                .count();
    }
}