package hms_controller;

import hms_model.Allocation;
import hms_model.Bill;
import hms_model.Maintenance;
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

@WebServlet("/StudentDashboardServlet")
public class StudentDashboardServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Add your database password here

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("student_id");
        
        // Check if student is logged in
        if (studentId == null) {
            response.sendRedirect("studentLogin.jsp");
            return;
        }

        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Get student details
            Student student = getStudentById(studentId);
            if (student == null) {
                session.invalidate();
                response.sendRedirect("studentLogin.jsp");
                return;
            }
            
            // Get student's allocation
            Allocation allocation = getAllocationByStudentId(studentId);
            
            // Get student's bills
            List<Bill> bills = getBillsByStudentId(studentId);
            
            // Get student's maintenance requests
            List<Maintenance> maintenanceRequests = getMaintenanceRequestsByStudentId(studentId);
            
            // Calculate pending bills
            long pendingBillsCount = bills.stream().filter(b -> "Unpaid".equals(b.getStatus())).count();
            
            // Set attributes for JSP
            request.setAttribute("student", student);
            request.setAttribute("allocation", allocation);
            request.setAttribute("bills", bills);
            request.setAttribute("pendingBillsCount", pendingBillsCount);
            request.setAttribute("maintenanceRequests", maintenanceRequests);
            
            request.getRequestDispatcher("/WEB-INF/views/studentDashboard.jsp").forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving student data: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
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
                student.setMerit(rs.getDouble("merit"));
                return student;
            }
        }
        return null;
    }

    private Allocation getAllocationByStudentId(int studentId) throws SQLException {
        String sql = "SELECT * FROM allocation WHERE studentID = ? AND status = 'Active'";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Allocation allocation = new Allocation();
                allocation.setAllocationID(rs.getInt("allocationID"));
                allocation.setDate_from(rs.getDate("date_from"));
                allocation.setDate_to(rs.getDate("date_to"));
                allocation.setStatus(rs.getString("status"));
                allocation.setStudentID(rs.getInt("studentID"));
                allocation.setRoomID(rs.getString("roomID"));
                allocation.setBlockID(rs.getString("blockID"));
                return allocation;
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

    private List<Maintenance> getMaintenanceRequestsByStudentId(int studentId) throws SQLException {
        List<Maintenance> requests = new ArrayList<>();
        
        String sql = "SELECT m.*, r.blockID FROM maintenance m " +
                     "LEFT JOIN room r ON m.roomID = r.roomID " +
                     "WHERE m.studentID = ? ORDER BY m.request_date DESC LIMIT 3";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
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
                request.setBlockID(rs.getString("blockID"));
                requests.add(request);
            }
        }
        return requests;
    }
}