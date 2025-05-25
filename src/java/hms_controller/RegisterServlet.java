package hms_controller;

import hms_model.Student;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve form parameters
        String fullName = request.getParameter("fullName");
        String matricNumber = request.getParameter("matricNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String program = request.getParameter("program");
        String semesterStr = request.getParameter("semester");
        String phone = request.getParameter("phone");
        String terms = request.getParameter("terms");

        // Validate required fields
        if (fullName == null || fullName.isEmpty() ||
            matricNumber == null || matricNumber.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty() ||
            program == null || program.isEmpty() ||
            semesterStr == null || semesterStr.isEmpty() ||
            phone == null || phone.isEmpty() ||
            terms == null) {
            
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Validate password match
        if (!password.equals(confirmPassword)) {
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

        // Create Student object
        Student student = new Student();
        student.setFullName(fullName);
        student.setMatricNumber(matricNumber);
        student.setEmail(email);
        student.setPassword(password);
        student.setProgram(program);
        student.setSemester(semester);
        student.setPhone(phone);

        // Database operations
        String sql = "INSERT INTO student (fullname, matricnumber, email, password, program, semester, phone) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/HostelManagement", "app", "app");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters
            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getMatricNumber());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPassword());
            pstmt.setString(5, student.getProgram());
            pstmt.setInt(6, student.getSemester());
            pstmt.setString(7, student.getPhone());

            // Execute update
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                request.setAttribute("success", "Registration successful! Please login.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("matric_number")) {
                request.setAttribute("error", "Matric number already exists.");
            } else if (e.getMessage().contains("email")) {
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
        // Redirect to registration page if accessed via GET
        response.sendRedirect(request.getContextPath() + "/register.jsp");
    }
}