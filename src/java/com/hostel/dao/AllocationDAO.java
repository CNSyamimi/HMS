package com.hostel.dao;

import com.hostel.model.Allocation;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class AllocationDAO {

    /**
     * Adds a new allocation to the database.
     * @param allocation The Allocation object to add.
     * @return True if the allocation was added successfully, false otherwise.
     */
    public boolean addAllocation(Allocation allocation) {
        String sql = "INSERT INTO allocation (allocationID, date_from, date_to, status, studentID, roomID, blockID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, allocation.getAllocationID()); // For auto-increment, you might omit this and use `RETURN_GENERATED_KEYS`
            ps.setDate(2, allocation.getDate_from());
            ps.setDate(3, allocation.getDate_to());
            ps.setString(4, allocation.getStatus());
            if (allocation.getStudentID() != null) {
                ps.setInt(5, allocation.getStudentID());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setString(6, allocation.getRoomID());
            ps.setString(7, allocation.getBlockID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
                // If allocationID is auto-incremented, you can retrieve it here
                // ResultSet generatedKeys = ps.getGeneratedKeys();
                // if (generatedKeys.next()) {
                //     allocation.setAllocationID(generatedKeys.getInt(1));
                // }
            }
        } catch (SQLException e) {
            System.err.println("Error adding allocation: " + e.getMessage());
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
     * Retrieves an allocation by its ID.
     * @param allocationID The ID of the allocation to retrieve.
     * @return An Allocation object if found, null otherwise.
     */
    public Allocation getAllocationById(int allocationID) {
        String sql = "SELECT * FROM allocation WHERE allocationID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Allocation allocation = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, allocationID);
            rs = ps.executeQuery();

            if (rs.next()) {
                allocation = new Allocation();
                allocation.setAllocationID(rs.getInt("allocationID"));
                allocation.setDate_from(rs.getDate("date_from"));
                allocation.setDate_to(rs.getDate("date_to"));
                allocation.setStatus(rs.getString("status"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    allocation.setStudentID(null);
                } else {
                    allocation.setStudentID(studentID);
                }
                allocation.setRoomID(rs.getString("roomID"));
                allocation.setBlockID(rs.getString("blockID"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting allocation by ID: " + e.getMessage());
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
        return allocation;
    }

    /**
     * Updates an existing allocation's information.
     * @param allocation The Allocation object with updated information.
     * @return True if the allocation was updated successfully, false otherwise.
     */
    public boolean updateAllocation(Allocation allocation) {
        String sql = "UPDATE allocation SET date_from = ?, date_to = ?, status = ?, studentID = ?, roomID = ?, blockID = ? WHERE allocationID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, allocation.getDate_from());
            ps.setDate(2, allocation.getDate_to());
            ps.setString(3, allocation.getStatus());
            if (allocation.getStudentID() != null) {
                ps.setInt(4, allocation.getStudentID());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            ps.setString(5, allocation.getRoomID());
            ps.setString(6, allocation.getBlockID());
            ps.setInt(7, allocation.getAllocationID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating allocation: " + e.getMessage());
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
     * Deletes an allocation from the database by ID.
     * @param allocationID The ID of the allocation to delete.
     * @return True if the allocation was deleted successfully, false otherwise.
     */
    public boolean deleteAllocation(int allocationID) {
        String sql = "DELETE FROM allocation WHERE allocationID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, allocationID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting allocation: " + e.getMessage());
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
     * Retrieves all allocations from the database.
     * @return A list of Allocation objects.
     */
    public List<Allocation> getAllAllocations() {
        List<Allocation> allocationList = new ArrayList<>();
        String sql = "SELECT * FROM allocation";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Allocation allocation = new Allocation();
                allocation.setAllocationID(rs.getInt("allocationID"));
                allocation.setDate_from(rs.getDate("date_from"));
                allocation.setDate_to(rs.getDate("date_to"));
                allocation.setStatus(rs.getString("status"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    allocation.setStudentID(null);
                } else {
                    allocation.setStudentID(studentID);
                }
                allocation.setRoomID(rs.getString("roomID"));
                allocation.setBlockID(rs.getString("blockID"));
                allocationList.add(allocation);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all allocations: " + e.getMessage());
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
        return allocationList;
    }

    /**
     * Retrieves allocations for a specific student.
     * @param studentID The ID of the student.
     * @return A list of Allocation objects for the student.
     */
    public List<Allocation> getAllocationsByStudentID(int studentID) {
        List<Allocation> allocationList = new ArrayList<>();
        String sql = "SELECT * FROM allocation WHERE studentID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Allocation allocation = new Allocation();
                allocation.setAllocationID(rs.getInt("allocationID"));
                allocation.setDate_from(rs.getDate("date_from"));
                allocation.setDate_to(rs.getDate("date_to"));
                allocation.setStatus(rs.getString("status"));
                allocation.setStudentID(rs.getInt("studentID"));
                allocation.setRoomID(rs.getString("roomID"));
                allocation.setBlockID(rs.getString("blockID"));
                allocationList.add(allocation);
            }
        } catch (SQLException e) {
            System.err.println("Error getting allocations by student ID: " + e.getMessage());
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
        return allocationList;
    }
}