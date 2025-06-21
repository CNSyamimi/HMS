package com.hostel.dao;

import com.hostel.model.Visitor;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

public class VisitorDAO {

    /**
     * Adds a new visitor to the database.
     * @param visitor The Visitor object to add.
     * @return True if the visitor was added successfully, false otherwise.
     */
    public boolean addVisitor(Visitor visitor) {
        String sql = "INSERT INTO visitor (visitorID, studentID, visitorName, visitDate, visitTime, visPass) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, visitor.getVisitorID());
            if (visitor.getStudentID() != null) {
                ps.setInt(2, visitor.getStudentID());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            ps.setString(3, visitor.getVisitorName());
            ps.setDate(4, visitor.getVisitDate());
            ps.setTime(5, visitor.getVisitTime());
            ps.setString(6, visitor.getVisPass());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding visitor: " + e.getMessage());
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
     * Retrieves a visitor by their ID.
     * @param visitorID The ID of the visitor to retrieve.
     * @return A Visitor object if found, null otherwise.
     */
    public Visitor getVisitorById(String visitorID) {
        String sql = "SELECT * FROM visitor WHERE visitorID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Visitor visitor = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, visitorID);
            rs = ps.executeQuery();

            if (rs.next()) {
                visitor = new Visitor();
                visitor.setVisitorID(rs.getString("visitorID"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    visitor.setStudentID(null);
                } else {
                    visitor.setStudentID(studentID);
                }
                visitor.setVisitorName(rs.getString("visitorName"));
                visitor.setVisitDate(rs.getDate("visitDate"));
                visitor.setVisitTime(rs.getTime("visitTime"));
                visitor.setVisPass(rs.getString("visPass"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting visitor by ID: " + e.getMessage());
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
        return visitor;
    }

    /**
     * Updates an existing visitor's information.
     * @param visitor The Visitor object with updated information.
     * @return True if the visitor was updated successfully, false otherwise.
     */
    public boolean updateVisitor(Visitor visitor) {
        String sql = "UPDATE visitor SET studentID = ?, visitorName = ?, visitDate = ?, visitTime = ?, visPass = ? WHERE visitorID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            if (visitor.getStudentID() != null) {
                ps.setInt(1, visitor.getStudentID());
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
            }
            ps.setString(2, visitor.getVisitorName());
            ps.setDate(3, visitor.getVisitDate());
            ps.setTime(4, visitor.getVisitTime());
            ps.setString(5, visitor.getVisPass());
            ps.setString(6, visitor.getVisitorID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating visitor: " + e.getMessage());
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
     * Deletes a visitor from the database by ID.
     * @param visitorID The ID of the visitor to delete.
     * @return True if the visitor was deleted successfully, false otherwise.
     */
    public boolean deleteVisitor(String visitorID) {
        String sql = "DELETE FROM visitor WHERE visitorID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, visitorID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting visitor: " + e.getMessage());
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
     * Retrieves all visitors from the database.
     * @return A list of Visitor objects.
     */
    public List<Visitor> getAllVisitors() {
        List<Visitor> visitorList = new ArrayList<>();
        String sql = "SELECT * FROM visitor";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Visitor visitor = new Visitor();
                visitor.setVisitorID(rs.getString("visitorID"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    visitor.setStudentID(null);
                } else {
                    visitor.setStudentID(studentID);
                }
                visitor.setVisitorName(rs.getString("visitorName"));
                visitor.setVisitDate(rs.getDate("visitDate"));
                visitor.setVisitTime(rs.getTime("visitTime"));
                visitor.setVisPass(rs.getString("visPass"));
                visitorList.add(visitor);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all visitors: " + e.getMessage());
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
        return visitorList;
    }

    /**
     * Retrieves visitors for a specific student.
     * @param studentID The ID of the student.
     * @return A list of Visitor objects for the student.
     */
    public List<Visitor> getVisitorsByStudentID(int studentID) {
        List<Visitor> visitorList = new ArrayList<>();
        String sql = "SELECT * FROM visitor WHERE studentID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Visitor visitor = new Visitor();
                visitor.setVisitorID(rs.getString("visitorID"));
                visitor.setStudentID(rs.getInt("studentID"));
                visitor.setVisitorName(rs.getString("visitorName"));
                visitor.setVisitDate(rs.getDate("visitDate"));
                visitor.setVisitTime(rs.getTime("visitTime"));
                visitor.setVisPass(rs.getString("visPass"));
                visitorList.add(visitor);
            }
        } catch (SQLException e) {
            System.err.println("Error getting visitors by student ID: " + e.getMessage());
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
        return visitorList;
    }
}
