package hms_controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database configuration - should be moved to config file in production
    private static final String DB_URL = "nanti_buat";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<String> errorMsgs = new LinkedList<String>();
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        
        try {
            // Retrieve and validate form parameters
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            
            // Form validation
            if (email == null || email.trim().length() == 0) {
                errorMsgs.add("Please enter your email address.");
            } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                errorMsgs.add("Please enter a valid email address.");
            }
            
            if (password == null || password.trim().length() == 0) {
                errorMsgs.add("Please enter your password.");
            }
            
            if (role == null || role.trim().length() == 0) {
                errorMsgs.add("Please select your role.");
            }
            
            // Return to login page if errors exist
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
            
            // Database operations
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                
                String query = "SELECT id, name, email, role FROM users WHERE email = ? AND password = ? AND role = ?";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, email);
                    pst.setString(2, password);
                    pst.setString(3, role);
                    
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            // Successful login - set session attributes
                            session.setAttribute("userId", rs.getInt("id"));
                            session.setAttribute("name", rs.getString("name"));
                            session.setAttribute("email", email);
                            session.setAttribute("role", role);
                            
                            // Redirect based on role
                            switch(role) {
                                case "admin":
                                    dispatcher = request.getRequestDispatcher("/adminDashboard.jsp");
                                    break;
                                case "warden":
                                    dispatcher = request.getRequestDispatcher("/wardenDashboard.jsp");
                                    break;
                                case "student":
                                    dispatcher = request.getRequestDispatcher("/studentDashboard.jsp");
                                    break;
                                default:
                                    dispatcher = request.getRequestDispatcher("/index.jsp");
                            }
                        } else {
                            errorMsgs.add("Invalid email, password, or role combination.");
                            request.setAttribute("errorMsgs", errorMsgs);
                            dispatcher = request.getRequestDispatcher("/login.jsp");
                        }
                    }
                }
            }
            
            // Forward to appropriate page
            dispatcher.forward(request, response);
            
        } catch (ClassNotFoundException e) {
            errorMsgs.add("Database driver not found: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (SQLException e) {
            errorMsgs.add("Database error occurred: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            errorMsgs.add("An unexpected error occurred: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}