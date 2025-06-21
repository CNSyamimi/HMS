package com.hostel.dao;

import com.hostel.model.Warden;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardenDAO {

    /**
     * Authenticates a warden based on ID and password.
     * @param wardenID The warden's ID.
     * @param password The warden's password.
     * @return A Warden object if authentication is successful, null otherwise.
     */
    public Warden authenticateWarden(String wardenID, String password) {
        String sql = "SELECT * FROM warden WHERE wardenID = ? AND wardenPass = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Warden warden = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, wardenID);
            ps.setString(2, password); // In a real application, hash passwords!
            rs = ps.executeQuery();

            if (rs.next()) {
                warden = new Warden();
                warden.setWardenID(rs.getString("wardenID"));
                warden.setWardenName(rs.getString("wardenName"));
                warden.setWardenPass(rs.getString("wardenPass"));
                warden.setWardenPho(rs.getString("wardenPho"));
                warden.setWardenEmail(rs.getString("wardenEmail"));
                warden.setBlockID(rs.getString("blockID"));
                warden.setAdmin_id(rs.getString("admin_id"));
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating warden: " + e.getMessage());
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
        return warden;
    }

    /**
     * Adds a new warden to the database.
     * @param warden The Warden object to add.
     * @return True if the warden was added successfully, false otherwise.
     */
    public boolean addWarden(Warden warden) {
        String sql = "INSERT INTO warden (wardenID, wardenName, wardenPass, wardenPho, wardenEmail, blockID, admin_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, warden.getWardenID());
            ps.setString(2, warden.getWardenName());
            ps.setString(3, warden.getWardenPass()); // In a real application, hash passwords!
            ps.setString(4, warden.getWardenPho());
            ps.setString(5, warden.getWardenEmail());
            ps.setString(6, warden.getBlockID());
            ps.setString(7, warden.getAdmin_id());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding warden: " + e.getMessage());
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
     * Retrieves a warden by their ID.
     * @param wardenID The ID of the warden to retrieve.
     * @return A Warden object if found, null otherwise.
     */
    public Warden getWardenById(String wardenID) {
        String sql = "SELECT * FROM warden WHERE wardenID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Warden warden = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, wardenID);
            rs = ps.executeQuery();

            if (rs.next()) {
                warden = new Warden();
                warden.setWardenID(rs.getString("wardenID"));
                warden.setWardenName(rs.getString("wardenName"));
                warden.setWardenPass(rs.getString("wardenPass"));
                warden.setWardenPho(rs.getString("wardenPho"));
                warden.setWardenEmail(rs.getString("wardenEmail"));
                warden.setBlockID(rs.getString("blockID"));
                warden.setAdmin_id(rs.getString("admin_id"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting warden by ID: " + e.getMessage());
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
        return warden;
    }

    /**
     * Updates an existing warden's information.
     * @param warden The Warden object with updated information.
     * @return True if the warden was updated successfully, false otherwise.
     */
    public boolean updateWarden(Warden warden) {
        String sql = "UPDATE warden SET wardenName = ?, wardenPass = ?, wardenPho = ?, wardenEmail = ?, blockID = ?, admin_id = ? WHERE wardenID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, warden.getWardenName());
            ps.setString(2, warden.getWardenPass()); // In a real application, hash passwords!
            ps.setString(3, warden.getWardenPho());
            ps.setString(4, warden.getWardenEmail());
            ps.setString(5, warden.getBlockID());
            ps.setString(6, warden.getAdmin_id());
            ps.setString(7, warden.getWardenID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating warden: " + e.getMessage());
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
     * Deletes a warden from the database by ID.
     * @param wardenID The ID of the warden to delete.
     * @return True if the warden was deleted successfully, false otherwise.
     */
    public boolean deleteWarden(String wardenID) {
        String sql = "DELETE FROM warden WHERE wardenID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, wardenID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting warden: " + e.getMessage());
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
     * Retrieves all wardens from the database.
     * @return A list of Warden objects.
     */
    public List<Warden> getAllWardens() {
        List<Warden> wardenList = new ArrayList<>();
        String sql = "SELECT * FROM warden";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Warden warden = new Warden();
                warden.setWardenID(rs.getString("wardenID"));
                warden.setWardenName(rs.getString("wardenName"));
                warden.setWardenPass(rs.getString("wardenPass"));
                warden.setWardenPho(rs.getString("wardenPho"));
                warden.setWardenEmail(rs.getString("wardenEmail"));
                warden.setBlockID(rs.getString("blockID"));
                warden.setAdmin_id(rs.getString("admin_id"));
                wardenList.add(warden);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all wardens: " + e.getMessage());
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
        return wardenList;
    }

    /**
     * Retrieves a list of wardens by block ID.
     * @param blockID The ID of the block.
     * @return A list of Warden objects associated with the block.
     */
    public List<Warden> getWardensByBlock(String blockID) {
        List<Warden> wardenList = new ArrayList<>();
        String sql = "SELECT * FROM warden WHERE blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, blockID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Warden warden = new Warden();
                warden.setWardenID(rs.getString("wardenID"));
                warden.setWardenName(rs.getString("wardenName"));
                warden.setWardenPass(rs.getString("wardenPass"));
                warden.setWardenPho(rs.getString("wardenPho"));
                warden.setWardenEmail(rs.getString("wardenEmail"));
                warden.setBlockID(rs.getString("blockID"));
                warden.setAdmin_id(rs.getString("admin_id"));
                wardenList.add(warden);
            }
        } catch (SQLException e) {
            System.err.println("Error getting wardens by block ID: " + e.getMessage());
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
        return wardenList;
    }
}