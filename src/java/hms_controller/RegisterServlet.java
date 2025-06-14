package hms_controller;

import hms_model.Student;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve form parameters
        String sName = request.getParameter("fullName");
        String studentID = request.getParameter("matricNumber");
        String sEmail = request.getParameter("email");
        String sPass = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String program = request.getParameter("program");
        String semesterStr = request.getParameter("semester");
        String sPho = request.getParameter("phone");
        String terms = request.getParameter("terms");
        
      

        // Validate required fields
        if (sName == null || sName.isEmpty() ||
            studentID == null || studentID.isEmpty() ||
            sEmail == null || sEmail.isEmpty() ||
            sPass == null || sPass.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty() ||
            program == null || program.isEmpty() ||
            semesterStr == null || semesterStr.isEmpty() ||
            sPho == null || sPho.isEmpty() ||
            terms == null) {
            
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Validate password match
        if (!sPass.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Convert semester to integer
        int semester;
        try {
            semester = Integer.parseInt(semesterStr);
            if (semester < 1 || semester > 8) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid semester. Must be between 1-8.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Create Student object with database-compatible field names
        Student student = new Student();
        student.setSName(sName);
        student.setStudentID(Integer.parseInt(studentID));
        student.setSEmail(sEmail);
        student.setSPass(sPass);
        student.setProgram(program);
        student.setSemester(semester);
        student.setSPho(sPho);
        student.setAdmin_id(0); // Default admin_id if not provided

        // Database operations
        String sql = "INSERT INTO student (sName, studentID, sEmail, sPass, program, semester, sPho, admin_id) " +
           "VALUES (?, ?, ?, ?, ?, ?, ?, NULL)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, student.getSName());
                pstmt.setInt(2, student.getStudentID());
                pstmt.setString(3, student.getSEmail());
                pstmt.setString(4, student.getSPass());
                pstmt.setString(5, student.getProgram());
                pstmt.setInt(6, student.getSemester());
                pstmt.setString(7, student.getSPho());
                
          
               
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    request.setAttribute("success", "Registration successful! Please login.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Registration failed. Please try again.");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "MySQL JDBC Driver not found.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("studentID")) {
                request.setAttribute("error", "Matric number already exists.");
            } else if (e.getMessage().contains("sEmail")) {
                request.setAttribute("error", "Email already registered.");
            } else {
                request.setAttribute("error", "Database error: " + e.getMessage());
            }
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/register.jsp");
    }
}