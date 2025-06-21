package com.hostel.model;

import java.sql.Date; // Use java.sql.Date for database compatibility

public class Maintenance {
    private String requestID;
    private Date request_date;
    private String issue_type;
    private String description;
    private String status;
    private Date resolution_date;
    private Integer studentID; // Can be null
    private String wardenID; // Can be null
    private String roomID;
    private String blockID;

    public Maintenance() {
    }

    public Maintenance(String requestID, Date request_date, String issue_type, String description, String status, Date resolution_date, Integer studentID, String wardenID, String roomID, String blockID) {
        this.requestID = requestID;
        this.request_date = request_date;
        this.issue_type = issue_type;
        this.description = description;
        this.status = status;
        this.resolution_date = resolution_date;
        this.studentID = studentID;
        this.wardenID = wardenID;
        this.roomID = roomID;
        this.blockID = blockID;
    }

    // Getters and Setters
    public String getRequestID() { return requestID; }
    public void setRequestID(String requestID) { this.requestID = requestID; }

    public Date getRequest_date() { return request_date; }
    public void setRequest_date(Date request_date) { this.request_date = request_date; }

    public String getIssue_type() { return issue_type; }
    public void setIssue_type(String issue_type) { this.issue_type = issue_type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getResolution_date() { return resolution_date; }
    public void setResolution_date(Date resolution_date) { this.resolution_date = resolution_date; }

    public Integer getStudentID() { return studentID; }
    public void setStudentID(Integer studentID) { this.studentID = studentID; }

    public String getWardenID() { return wardenID; }
    public void setWardenID(String wardenID) { this.wardenID = wardenID; }

    public String getRoomID() { return roomID; }
    public void setRoomID(String roomID) { this.roomID = roomID; }

    public String getBlockID() { return blockID; }
    public void setBlockID(String blockID) { this.blockID = blockID; }

    @Override
    public String toString() {
        return "Maintenance{" +
               "requestID='" + requestID + '\'' +
               ", request_date=" + request_date +
               ", issue_type='" + issue_type + '\'' +
               ", description='" + description + '\'' +
               ", status='" + status + '\'' +
               ", resolution_date=" + resolution_date +
               ", studentID=" + studentID +
               ", wardenID='" + wardenID + '\'' +
               ", roomID='" + roomID + '\'' +
               ", blockID='" + blockID + '\'' +
               '}';
    }
}