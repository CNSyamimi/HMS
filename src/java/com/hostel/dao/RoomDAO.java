package com.hostel.dao;

import com.hostel.model.Room;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    /**
     * Adds a new room to the database.
     * @param room The Room object to add.
     * @return True if the room was added successfully, false otherwise.
     */
    public boolean addRoom(Room room) {
        String sql = "INSERT INTO room (roomID, blockID, capacity, curOcc, roomType, gender) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, room.getRoomID());
            ps.setString(2, room.getBlockID());
            ps.setInt(3, room.getCapacity());
            ps.setInt(4, room.getCurOcc());
            ps.setString(5, room.getRoomType());
            ps.setString(6, String.valueOf(room.getGender()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
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
     * Retrieves a room by its composite primary key (roomID and blockID).
     * @param roomID The ID of the room.
     * @param blockID The ID of the block the room belongs to.
     * @return A Room object if found, null otherwise.
     */
    public Room getRoomById(String roomID, String blockID) {
        String sql = "SELECT * FROM room WHERE roomID = ? AND blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Room room = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, roomID);
            ps.setString(2, blockID);
            rs = ps.executeQuery();

            if (rs.next()) {
                room = new Room();
                room.setRoomID(rs.getString("roomID"));
                room.setBlockID(rs.getString("blockID"));
                room.setCapacity(rs.getInt("capacity"));
                room.setCurOcc(rs.getInt("curOcc"));
                room.setRoomType(rs.getString("roomType"));
                room.setGender(rs.getString("gender").charAt(0));
            }
        } catch (SQLException e) {
            System.err.println("Error getting room by ID: " + e.getMessage());
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
        return room;
    }

    /**
     * Updates an existing room's information.
     * @param room The Room object with updated information.
     * @return True if the room was updated successfully, false otherwise.
     */
    public boolean updateRoom(Room room) {
        String sql = "UPDATE room SET capacity = ?, curOcc = ?, roomType = ?, gender = ? WHERE roomID = ? AND blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, room.getCapacity());
            ps.setInt(2, room.getCurOcc());
            ps.setString(3, room.getRoomType());
            ps.setString(4, String.valueOf(room.getGender()));
            ps.setString(5, room.getRoomID());
            ps.setString(6, room.getBlockID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating room: " + e.getMessage());
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
     * Deletes a room from the database by its composite primary key.
     * @param roomID The ID of the room to delete.
     * @param blockID The ID of the block the room belongs to.
     * @return True if the room was deleted successfully, false otherwise.
     */
    public boolean deleteRoom(String roomID, String blockID) {
        String sql = "DELETE FROM room WHERE roomID = ? AND blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, roomID);
            ps.setString(2, blockID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting room: " + e.getMessage());
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
     * Retrieves all rooms from the database.
     * @return A list of Room objects.
     */
    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM room";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setRoomID(rs.getString("roomID"));
                room.setBlockID(rs.getString("blockID"));
                room.setCapacity(rs.getInt("capacity"));
                room.setCurOcc(rs.getInt("curOcc"));
                room.setRoomType(rs.getString("roomType"));
                room.setGender(rs.getString("gender").charAt(0));
                roomList.add(room);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all rooms: " + e.getMessage());
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
        return roomList;
    }

    /**
     * Retrieves rooms within a specific block.
     * @param blockID The ID of the block.
     * @return A list of Room objects in the specified block.
     */
    public List<Room> getRoomsByBlockID(String blockID) {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE blockID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, blockID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setRoomID(rs.getString("roomID"));
                room.setBlockID(rs.getString("blockID"));
                room.setCapacity(rs.getInt("capacity"));
                room.setCurOcc(rs.getInt("curOcc"));
                room.setRoomType(rs.getString("roomType"));
                room.setGender(rs.getString("gender").charAt(0));
                roomList.add(room);
            }
        } catch (SQLException e) {
            System.err.println("Error getting rooms by block ID: " + e.getMessage());
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
        return roomList;
    }
}