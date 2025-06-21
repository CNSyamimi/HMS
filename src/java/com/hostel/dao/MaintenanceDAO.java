package com.hostel.dao;

import com.hostel.model.Maintenance;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class MaintenanceDAO {

    /**
     * Adds a new maintenance request to the database.
     * @param maintenance The Maintenance object to add.
     * @return True if the request was added successfully, false otherwise.
     */
    public boolean addMaintenanceRequest(Maintenance maintenance) {
        String sql = "INSERT INTO maintenance (requestID, request_date, issue_type, description, status, resolution_date, studentID, wardenID, roomID, blockID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maintenance.getRequestID());
            ps.setDate(2, maintenance.getRequest_date());
            ps.setString(3, maintenance.getIssue_type());
            ps.setString(4, maintenance.getDescription());
            ps.setString(5, maintenance.getStatus());
            ps.setDate(6, maintenance.getResolution_date()); // Can be null
            if (maintenance.getStudentID() != null) {
                ps.setInt(7, maintenance.getStudentID());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }
            ps.setString(8, maintenance.getWardenID()); // Can be null
            ps.setString(9, maintenance.getRoomID());
            ps.setString(10, maintenance.getBlockID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding maintenance request: " + e.getMessage());
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
     * Retrieves a maintenance request by its ID.
     * @param requestID The ID of the request to retrieve.
     * @return A Maintenance object if found, null otherwise.
     */
    public Maintenance getMaintenanceRequestById(String requestID) {
        String sql = "SELECT * FROM maintenance WHERE requestID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Maintenance maintenance = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, requestID);
            rs = ps.executeQuery();

            if (rs.next()) {
                maintenance = new Maintenance();
                maintenance.setRequestID(rs.getString("requestID"));
                maintenance.setRequest_date(rs.getDate("request_date"));
                maintenance.setIssue_type(rs.getString("issue_type"));
                maintenance.setDescription(rs.getString("description"));
                maintenance.setStatus(rs.getString("status"));
                maintenance.setResolution_date(rs.getDate("resolution_date"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    maintenance.setStudentID(null);
                } else {
                    maintenance.setStudentID(studentID);
                }
                maintenance.setWardenID(rs.getString("wardenID"));
                maintenance.setRoomID(rs.getString("roomID"));
                maintenance.setBlockID(rs.getString("blockID"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting maintenance request by ID: " + e.getMessage());
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
        return maintenance;
    }

    /**
     * Updates an existing maintenance request's information.
     * @param maintenance The Maintenance object with updated information.
     * @return True if the request was updated successfully, false otherwise.
     */
    public boolean updateMaintenanceRequest(Maintenance maintenance) {
        String sql = "UPDATE maintenance SET request_date = ?, issue_type = ?, description = ?, status = ?, resolution_date = ?, studentID = ?, wardenID = ?, roomID = ?, blockID = ? WHERE requestID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, maintenance.getRequest_date());
            ps.setString(2, maintenance.getIssue_type());
            ps.setString(3, maintenance.getDescription());
            ps.setString(4, maintenance.getStatus());
            ps.setDate(5, maintenance.getResolution_date());
            if (maintenance.getStudentID() != null) {
                ps.setInt(6, maintenance.getStudentID());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.setString(7, maintenance.getWardenID());
            ps.setString(8, maintenance.getRoomID());
            ps.setString(9, maintenance.getBlockID());
            ps.setString(10, maintenance.getRequestID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating maintenance request: " + e.getMessage());
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
     * Updates only the status and resolution date of a maintenance request.
     * This is useful for warden/admin actions.
     * @param requestID The ID of the request to update.
     * @param status The new status.
     * @param resolutionDate The new resolution date (can be null).
     * @return True if the request was updated successfully, false otherwise.
     */
    public boolean updateMaintenanceStatus(String requestID, String status, Date resolutionDate) {
        String sql = "UPDATE maintenance SET status = ?, resolution_date = ? WHERE requestID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setDate(2, resolutionDate);
            ps.setString(3, requestID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating maintenance status: " + e.getMessage());
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
     * Deletes a maintenance request from the database by ID.
     * @param requestID The ID of the request to delete.
     * @return True if the request was deleted successfully, false otherwise.
     */
    public boolean deleteMaintenanceRequest(String requestID) {
        String sql = "DELETE FROM maintenance WHERE requestID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, requestID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting maintenance request: " + e.getMessage());
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
     * Retrieves all maintenance requests from the database.
     * @return A list of Maintenance objects.
     */
    public List<Maintenance> getAllMaintenanceRequests() {
        List<Maintenance> maintenanceList = new ArrayList<>();
        String sql = "SELECT * FROM maintenance";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Maintenance maintenance = new Maintenance();
                maintenance.setRequestID(rs.getString("requestID"));
                maintenance.setRequest_date(rs.getDate("request_date"));
                maintenance.setIssue_type(rs.getString("issue_type"));
                maintenance.setDescription(rs.getString("description"));
                maintenance.setStatus(rs.getString("status"));
                maintenance.setResolution_date(rs.getDate("resolution_date"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    maintenance.setStudentID(null);
                } else {
                    maintenance.setStudentID(studentID);
                }
                maintenance.setWardenID(rs.getString("wardenID"));
                maintenance.setRoomID(rs.getString("roomID"));
                maintenance.setBlockID(rs.getString("blockID"));
                maintenanceList.add(maintenance);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all maintenance requests: " + e.getMessage());
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
        return maintenanceList;
    }

    /**
     * Retrieves maintenance requests for a specific student.
     * @param studentID The ID of the student.
     * @return A list of Maintenance objects for the student.
     */
    public List<Maintenance> getMaintenanceRequestsByStudentID(int studentID) {
        List<Maintenance> maintenanceList = new ArrayList<>();
        String sql = "SELECT * FROM maintenance WHERE studentID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Maintenance maintenance = new Maintenance();
                maintenance.setRequestID(rs.getString("requestID"));
                maintenance.setRequest_date(rs.getDate("request_date"));
                maintenance.setIssue_type(rs.getString("issue_type"));
                maintenance.setDescription(rs.getString("description"));
                maintenance.setStatus(rs.getString("status"));
                maintenance.setResolution_date(rs.getDate("resolution_date"));
                maintenance.setStudentID(rs.getInt("studentID"));
                maintenance.setWardenID(rs.getString("wardenID"));
                maintenance.setRoomID(rs.getString("roomID"));
                maintenance.setBlockID(rs.getString("blockID"));
                maintenanceList.add(maintenance);
            }
        } catch (SQLException e) {
            System.err.println("Error getting maintenance requests by student ID: " + e.getMessage());
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
        return maintenanceList;
    }

    /**
     * Retrieves maintenance requests assigned to a specific warden.
     * @param wardenID The ID of the warden.
     * @return A list of Maintenance objects assigned to the warden.
     */
    public List<Maintenance> getMaintenanceRequestsByWardenID(String wardenID) {
        List<Maintenance> maintenanceList = new ArrayList<>();
        String sql = "SELECT * FROM maintenance WHERE wardenID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, wardenID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Maintenance maintenance = new Maintenance();
                maintenance.setRequestID(rs.getString("requestID"));
                maintenance.setRequest_date(rs.getDate("request_date"));
                maintenance.setIssue_type(rs.getString("issue_type"));
                maintenance.setDescription(rs.getString("description"));
                maintenance.setStatus(rs.getString("status"));
                maintenance.setResolution_date(rs.getDate("resolution_date"));
                maintenance.setStudentID(rs.getInt("studentID"));
                maintenance.setWardenID(rs.getString("wardenID"));
                maintenance.setRoomID(rs.getString("roomID"));
                maintenance.setBlockID(rs.getString("blockID"));
                maintenanceList.add(maintenance);
            }
        } catch (SQLException e) {
                System.err.println("Error getting maintenance requests by warden ID: " + e.getMessage());
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
        return maintenanceList;
    }
}