package hms_model;

import java.sql.Date;

public class Allocation {
    private int allocationID;
    private Date date_from;
    private Date date_to;
    private String status;
    private int studentID;
    private String roomID;
    private String blockID;

    public Allocation() {
    }

    public Allocation(int allocationID, Date date_from, Date date_to, String status, int studentID, String roomID, String blockID) {
        this.allocationID = allocationID;
        this.date_from = date_from;
        this.date_to = date_to;
        this.status = status;
        this.studentID = studentID;
        this.roomID = roomID;
        this.blockID = blockID;
    }

    // Getters and Setters
    public int getAllocationID() {
        return allocationID;
    }

    public void setAllocationID(int allocationID) {
        this.allocationID = allocationID;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }
}