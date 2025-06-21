package com.hostel.dao;

import com.hostel.model.HostelBlock;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HostelBlockDAO {

    /**
     * Adds a new hostel block to the database.
     * @param block The HostelBlock object to add.
     * @return True if the block was added successfully, false otherwise.
     */
    public boolean addHostelBlock(HostelBlock block) {
        String sql = "INSERT INTO hostelblock (blockID, blockName, totalRooms, wardenID) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, block.getBlockID());
            ps.setString(2, block.getBlockName());
            ps.setInt(3, block.getTotalRooms());
            ps.setString(4, block.getWardenID()); // Can be null

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding hostel block: " + e.getMessage());
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
     * Retrieves a hostel block by its ID.
     * @param blockID The ID of the block to retrieve.
     * @return A HostelBlock object if found, null otherwise.
     */
    public HostelBlock getHostelBlockById(String blockID) {
        String sql = "SELECT * FROM hostelblock WHERE blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HostelBlock block = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, blockID);
            rs = ps.executeQuery();

            if (rs.next()) {
                block = new HostelBlock();
                block.setBlockID(rs.getString("blockID"));
                block.setBlockName(rs.getString("blockName"));
                block.setTotalRooms(rs.getInt("totalRooms"));
                block.setWardenID(rs.getString("wardenID"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting hostel block by ID: " + e.getMessage());
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
        return block;
    }

    /**
     * Updates an existing hostel block's information.
     * @param block The HostelBlock object with updated information.
     * @return True if the block was updated successfully, false otherwise.
     */
    public boolean updateHostelBlock(HostelBlock block) {
        String sql = "UPDATE hostelblock SET blockName = ?, totalRooms = ?, wardenID = ? WHERE blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, block.getBlockName());
            ps.setInt(2, block.getTotalRooms());
            ps.setString(3, block.getWardenID());
            ps.setString(4, block.getBlockID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating hostel block: " + e.getMessage());
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
     * Deletes a hostel block from the database by ID.
     * @param blockID The ID of the block to delete.
     * @return True if the block was deleted successfully, false otherwise.
     */
    public boolean deleteHostelBlock(String blockID) {
        String sql = "DELETE FROM hostelblock WHERE blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, blockID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting hostel block: " + e.getMessage());
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
     * Retrieves all hostel blocks from the database.
     * @return A list of HostelBlock objects.
     */
    public List<HostelBlock> getAllHostelBlocks() {
        List<HostelBlock> blockList = new ArrayList<>();
        String sql = "SELECT * FROM hostelblock";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HostelBlock block = new HostelBlock();
                block.setBlockID(rs.getString("blockID"));
                block.setBlockName(rs.getString("blockName"));
                block.setTotalRooms(rs.getInt("totalRooms"));
                block.setWardenID(rs.getString("wardenID"));
                blockList.add(block);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all hostel blocks: " + e.getMessage());
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
        return blockList;
    }
}