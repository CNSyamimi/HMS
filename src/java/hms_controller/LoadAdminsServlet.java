package hms_controller;

import hms_model.Admin;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loadAdmins")
public class LoadAdminsServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Admin> admins = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT admin_id, admin_name FROM admin")) {
                
                while (rs.next()) {
                    admins.add(new Admin(rs.getInt("admin_id"), rs.getString("admin_name")));
                }
                request.setAttribute("admins", admins);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading administrators");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}