package hms_controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database configuration - should be externalized in production
    private static final String DB_URL = "nanti_buat";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<String> errorMsgs = new LinkedList<String>();
        RequestDispatcher dispatcher = null;
        
        try {
            // Retrieve all form parameters
            String fullName = request.getParameter("fullName");
            String matricNumber = request.getParameter("matricNumber");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String program = request.getParameter("program");
            String semester = request.getParameter("semester");
            String phone = request.getParameter("phone");
            String terms = request.getParameter("terms");
            
            // Form validation
            if (fullName == null || fullName.trim().length() == 0) {
                errorMsgs.add("Full name is required.");
            } else if (fullName.trim().length() < 3) {
                errorMsgs.add("Full name must be at least 3 characters.");
            }
            
            if (matricNumber == null || matricNumber.trim().length() == 0) {
                errorMsgs.add("Matric number is required.");
            } else if (!matricNumber.matches("^\\d{10}$")) {
                errorMsgs.add("Matric number must be 10 digits.");
            }
            
            if (email == null || email.trim().length() == 0) {
                errorMsgs.add("Email address is required.");
            } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                errorMsgs.add("Please enter a valid email address.");
            }
            
            if (password == null || password.trim().length() == 0) {
                errorMsgs.add("Password is required.");
            } else if (password.length() < 8) {
                errorMsgs.add("Password must be at least 8 characters.");
            }
            
            if (!password.equals(confirmPassword)) {
                errorMsgs.add("Passwords do not match.");
            }
            
            if (program == null || program.trim().length() == 0) {
                errorMsgs.add("Program selection is required.");
            }
            
            if (semester == null || semester.trim().length() == 0) {
                errorMsgs.add("Current semester is required.");
            } else {
                try {
                    int sem = Integer.parseInt(semester);
                    if (sem < 1 || sem > 8) {
                        errorMsgs.add("Semester must be between 1 and 8.");
                    }
                } catch (NumberFormatException e) {
                    errorMsgs.add("Semester must be a valid number.");
                }
            }
            
            if (phone == null || phone.trim().length() == 0) {
                errorMsgs.add("Phone number is required.");
            } else if (!phone.matches("^\\d{10,12}$")) {
                errorMsgs.add("Phone number must be 10-12 digits.");
            }
            
            if (terms == null) {
                errorMsgs.add("You must agree to the terms and conditions.");
            }

            // Return to register page if errors exist
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                // Preserve all form inputs
                request.setAttribute("preservedValues", request.getParameterMap());
                dispatcher = request.getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
                return;
            }
            
            // Database operations
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                
                // Check if email or matric number already exists
                String checkQuery = "SELECT email, matric_no FROM users WHERE email = ? OR matric_no = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, email);
                    checkStmt.setString(2, matricNumber);
                    
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {
                            if (rs.getString("email").equalsIgnoreCase(email)) {
                                errorMsgs.add("This email is already registered.");
                            }
                            if (rs.getString("matric_no").equalsIgnoreCase(matricNumber)) {
                                errorMsgs.add("This matric number is already registered.");
                            }
                            
                            request.setAttribute("errorMsgs", errorMsgs);
                            request.setAttribute("preservedValues", request.getParameterMap());
                            dispatcher = request.getRequestDispatcher("/register.jsp");
                            dispatcher.forward(request, response);
                            return;
                        }
                    }
                }
                
                // Insert new student
                String insertQuery = "INSERT INTO users (name, email, password, role, matric_no, program, semester, contact_no) " +
                                     "VALUES (?, ?, ?, 'student', ?, ?, ?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(insertQuery)) {
                    pst.setString(1, fullName);
                    pst.setString(2, email);
                    pst.setString(3, password); // Note: Should hash password in production
                    pst.setString(4, matricNumber);
                    pst.setString(5, program);
                    pst.setInt(6, Integer.parseInt(semester));
                    pst.setString(7, phone);
                    
                    int rowsAffected = pst.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        // Registration successful
                        request.setAttribute("success", "Registration successful! You can now login.");
                        dispatcher = request.getRequestDispatcher("/login.jsp");
                    } else {
                        errorMsgs.add("Registration failed. Please try again.");
                        request.setAttribute("errorMsgs", errorMsgs);
                        request.setAttribute("preservedValues", request.getParameterMap());
                        dispatcher = request.getRequestDispatcher("/register.jsp");
                    }
                }
            }
            
            // Forward to appropriate page
            dispatcher.forward(request, response);
            
        } catch (ClassNotFoundException e) {
            handleError(request, response, "Database driver not found: " + e.getMessage());
        } catch (SQLException e) {
            handleError(request, response, "Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            handleError(request, response, "An unexpected error occurred: " + e.getMessage());
        }
    }
    
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMsg) 
            throws ServletException, IOException {
        List<String> errorMsgs = new LinkedList<String>();
        errorMsgs.add(errorMsg);
        request.setAttribute("errorMsgs", errorMsgs);
        
        // Try to preserve form inputs if available
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && !params.isEmpty()) {
            request.setAttribute("preservedValues", params);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }
}