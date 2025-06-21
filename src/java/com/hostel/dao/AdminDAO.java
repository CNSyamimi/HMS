// src/main/java/com/hostel/dao/AdminDAO.java
package com.hostel.dao;

import com.hostel.model.Admin;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    /**
     * Authenticates an admin based on ID and password.
     * @param adminID The admin's ID.
     * @param password The admin's password.
     * @return An Admin object if authentication is successful, null otherwise.
     */
    public Admin authenticateAdmin(String adminID, String password) {
        String sql = "SELECT * FROM admin WHERE adminID = ? AND adminPass = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminID);
            ps.setString(2, password); // In a real application, hash passwords!
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminID(rs.getString("adminID"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setAdminPho(rs.getString("adminPho"));
                admin.setAdminPass(rs.getString("adminPass"));
                admin.setAdminEmail(rs.getString("adminEmail"));
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating admin: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return admin;
    }

    /**
     * Adds a new admin to the database.
     * @param admin The Admin object to add.
     * @return True if the admin was added successfully, false otherwise.
     */
    public boolean addAdmin(Admin admin) {
        String sql = "INSERT INTO admin (adminID, adminName, adminPho, adminPass, adminEmail) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, admin.getAdminID());
            ps.setString(2, admin.getAdminName());
            ps.setString(3, admin.getAdminPho());
            ps.setString(4, admin.getAdminPass()); // In a real application, hash passwords!
            ps.setString(5, admin.getAdminEmail());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding admin: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowAffected;
    }

    /**
     * Retrieves an admin by their ID.
     * @param adminID The ID of the admin to retrieve.
     * @return An Admin object if found, null otherwise.
     */
    public Admin getAdminById(String adminID) {
        String sql = "SELECT * FROM admin WHERE adminID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminID);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminID(rs.getString("adminID"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setAdminPho(rs.getString("adminPho"));
                admin.setAdminPass(rs.getString("adminPass"));
                admin.setAdminEmail(rs.getString("adminEmail"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting admin by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return admin;
    }

    /**
     * Updates an existing admin's information.
     * @param admin The Admin object with updated information.
     * @return True if the admin was updated successfully, false otherwise.
     */
    public boolean updateAdmin(Admin admin) {
        String sql = "UPDATE admin SET adminName = ?, adminPho = ?, adminPass = ?, adminEmail = ? WHERE adminID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, admin.getAdminName());
            ps.setString(2, admin.getAdminPho());
            ps.setString(3, admin.getAdminPass()); // In a real application, hash passwords!
            ps.setString(4, admin.getAdminEmail());
            ps.setString(5, admin.getAdminID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating admin: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowAffected;
    }

    /**
     * Deletes an admin from the database by ID.
     * @param adminID The ID of the admin to delete.
     * @return True if the admin was deleted successfully, false otherwise.
     */
    public boolean deleteAdmin(String adminID) {
        String sql = "DELETE FROM admin WHERE adminID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting admin: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowAffected;
    }

    /**
     * Retrieves all admins from the database.
     * @return A list of Admin objects.
     */
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminID(rs.getString("adminID"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setAdminPho(rs.getString("adminPho"));
                admin.setAdminPass(rs.getString("adminPass"));
                admin.setAdminEmail(rs.getString("adminEmail"));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all admins: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return adminList;
    }
}
