package hms_controller;

import hms_model.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StudentProfileServlet")
public class StudentProfileServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Add your database password here

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("student_id");
        
        // Check if student is logged in
        if (studentId == null) {
            response.sendRedirect("/studentProfile.jsp");
            return;
        }

        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Get student details
            Student student = getStudentById(studentId);
            
            if (student == null) {
                session.invalidate();
                response.sendRedirect("/studentProfile.jsp");
                return;
            }
            
            // Set attributes for JSP
            request.setAttribute("student", student);
            request.getRequestDispatcher("/studentProfile.jsp").forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving student data: " + e.getMessage());
            request.getRequestDispatcher("/studentProfile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("student_id");
        
        // Check if student is logged in
        if (studentId == null) {
            response.sendRedirect("/studentProfile.jsp");
            return;
        }

        // Get form parameters
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        
        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Verify current password if changing password
            if ((newPassword != null && !newPassword.isEmpty()) && 
                !verifyCurrentPassword(studentId, currentPassword)) {
                request.setAttribute("errorMessage", "Current password is incorrect");
                doGet(request, response);
                return;
            }
            
            // Update student profile
            boolean success = updateStudentProfile(studentId, name, phone, email, 
                (newPassword != null && !newPassword.isEmpty()) ? newPassword : null);
            
            if (success) {
                request.setAttribute("successMessage", "Profile updated successfully");
            } else {
                request.setAttribute("errorMessage", "Failed to update profile");
            }
            
            // Refresh student data
            doGet(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating profile: " + e.getMessage());
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

    private boolean verifyCurrentPassword(int studentId, String password) throws SQLException {
        String sql = "SELECT sPass FROM student WHERE studentID = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return password.equals(rs.getString("sPass"));
            }
        }
        return false;
    }

    private boolean updateStudentProfile(int studentId, String name, String phone, 
            String email, String newPassword) throws SQLException {
        String sql;
        if (newPassword != null) {
            sql = "UPDATE student SET sName = ?, sPho = ?, sEmail = ?, sPass = ? WHERE studentID = ?";
        } else {
            sql = "UPDATE student SET sName = ?, sPho = ?, sEmail = ? WHERE studentID = ?";
        }
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, email);
            
            if (newPassword != null) {
                stmt.setString(4, newPassword);
                stmt.setInt(5, studentId);
            } else {
                stmt.setInt(4, studentId);
            }
            
            return stmt.executeUpdate() > 0;
        }
    }
}