package hms_controller;

import hms_model.Maintenance;
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

@WebServlet("/MaintenanceOverviewServlet")
public class MaintenanceOverviewServlet extends HttpServlet {
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

        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Get maintenance statistics
            int totalRequests = getTotalMaintenanceRequests();
            int pendingRequests = getPendingMaintenanceRequests();
            int completedRequests = getCompletedMaintenanceRequests();
            int inProgressRequests = getInProgressMaintenanceRequests();
            
            // Get recent maintenance requests
            List<Maintenance> recentRequests = getRecentMaintenanceRequests(5);
            
            // Set attributes for JSP
            request.setAttribute("totalRequests", totalRequests);
            request.setAttribute("pendingRequests", pendingRequests);
            request.setAttribute("completedRequests", completedRequests);
            request.setAttribute("inProgressRequests", inProgressRequests);
            request.setAttribute("recentRequests", recentRequests);
            
            request.getRequestDispatcher("maintenanceOverview.jsp").forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving maintenance data: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private int getTotalMaintenanceRequests() throws SQLException {
        String sql = "SELECT COUNT(*) FROM maintenance";
        return getCountFromQuery(sql);
    }

    private int getPendingMaintenanceRequests() throws SQLException {
        String sql = "SELECT COUNT(*) FROM maintenance WHERE status = 'Pending'";
        return getCountFromQuery(sql);
    }

    private int getCompletedMaintenanceRequests() throws SQLException {
        String sql = "SELECT COUNT(*) FROM maintenance WHERE status = 'Completed'";
        return getCountFromQuery(sql);
    }

    private int getInProgressMaintenanceRequests() throws SQLException {
        String sql = "SELECT COUNT(*) FROM maintenance WHERE status = 'In Progress'";
        return getCountFromQuery(sql);
    }

    private int getCountFromQuery(String sql) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private List<Maintenance> getRecentMaintenanceRequests(int limit) throws SQLException {
        List<Maintenance> requests = new ArrayList<>();
        
        String sql = "SELECT * FROM maintenance ORDER BY request_date DESC LIMIT ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, limit);
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
                requests.add(request);
            }
        }
        
        return requests;
    }
}