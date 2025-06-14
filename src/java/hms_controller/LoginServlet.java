package hms_controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String redirectPage = "login.jsp";
        String error = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                
                String query = "";
                String idColumn = "";
                String nameColumn = "";
                
                // Determine query based on role
                switch(role.toLowerCase()) {
                    case "admin":
                        query = "SELECT admin_id, adminName FROM admin WHERE adminEmail = ? AND adminPass = ?";
                        idColumn = "admin_id";
                        nameColumn = "adminName";
                        break;
                    case "warden":
                        query = "SELECT wardenID, wardenName FROM warden WHERE wardenEmail = ? AND wardenPass = ?";
                        idColumn = "wardenID";
                        nameColumn = "wardenName";
                        break;
                    case "student":
                        query = "SELECT studentID, sName FROM student WHERE sEmail = ? AND sPass = ?";
                        idColumn = "studentID";
                        nameColumn = "sName";
                        break;
                    default:
                        throw new ServletException("Invalid role specified");
                }
                
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, email);
                    pstmt.setString(2, password);
                    
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            // Successful login - set session attributes
                            session.setAttribute("userId", rs.getString(idColumn));
                            session.setAttribute("name", rs.getString(nameColumn));
                            session.setAttribute("email", email);
                            session.setAttribute("role", role);
                            
                            // Set redirect page based on role
                            switch(role.toLowerCase()) {
                                case "admin":
                                    redirectPage = "adminDashboard.jsp";
                                    break;
                                case "warden":
                                    redirectPage = "wardenDashboard.jsp";
                                    break;
                                case "student":
                                    redirectPage = "studentDashboard.jsp";
                                    break;
                            }
                        } else {
                            error = "Invalid email/password combination for selected role";
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            error = "Database driver not found";
        } catch (SQLException e) {
            error = "Database error: " + e.getMessage();
        } catch (Exception e) {
            error = "System error: " + e.getMessage();
        }
        
        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            response.sendRedirect(redirectPage);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}