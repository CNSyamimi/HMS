package hms_controller;

import hms_model.Admin;
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

@WebServlet("/AdminProfileServlet")
public class AdminProfileServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Add your database password here

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer) session.getAttribute("admin_id");
        
        if (admin_id == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        Admin admin = getAdminById(admin_id);
        
        if (admin != null) {
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Admin not found");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer) session.getAttribute("admin_id");
        
        if (admin_id == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        String action = request.getParameter("action");
        
        if ("update".equals(action)) {
            String name = request.getParameter("adminName");
            String phone = request.getParameter("adminPho");
            String email = request.getParameter("adminEmail");
            String password = request.getParameter("adminPass");
            
            boolean success = updateAdmin(admin_id, name, phone, email, password);
            
            if (success) {
                request.setAttribute("successMessage", "Profile updated successfully");
            } else {
                request.setAttribute("errorMessage", "Failed to update profile");
            }
            
            // Refresh the admin data
            Admin admin = getAdminById(admin_id);
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
        }
    }

    private Admin getAdminById(int admin_id) {
        Admin admin = null;
        String adminSql = "SELECT * FROM admin WHERE admin_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(adminSql)) {
            
            stmt.setInt(1, admin_id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                admin = new Admin();
                admin.setAdmin_id(rs.getInt("admin_id"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setAdminPho(rs.getString("adminPho"));
                admin.setAdminPass(rs.getString("adminPass"));
                admin.setAdminEmail(rs.getString("adminEmail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return admin;
    }

    private boolean updateAdmin(int admin_id, String name, String phone, String email, String password) {
        String adminSql = "UPDATE admin SET adminName = ?, adminPho = ?, adminEmail = ?, adminPass = ? WHERE admin_id = ?";
        boolean success = false;
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(adminSql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setInt(5, admin_id);
            
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return success;
    }
}