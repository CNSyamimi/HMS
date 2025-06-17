package hms_model;

import java.sql.Date;

public class Maintenance {
    private int requestID;
    private Date request_date;
    private String issue_type;
    private String description;
    private String status;
    private Date resolution_date;
    private int studentID;
    private int wardenID;
    private String roomID;

    public Maintenance() {
    }

    public Maintenance(int requestID, Date request_date, String issue_type, String description, String status, Date resolution_date, int studentID, int wardenID, String roomID) {
        this.requestID = requestID;
        this.request_date = request_date;
        this.issue_type = issue_type;
        this.description = description;
        this.status = status;
        this.resolution_date = resolution_date;
        this.studentID = studentID;
        this.wardenID = wardenID;
        this.roomID = roomID;
    }

    // Getters and Setters
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public String getIssue_type() {
        return issue_type;
    }

    public void setIssue_type(String issue_type) {
        this.issue_type = issue_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getResolution_date() {
        return resolution_date;
    }

    public void setResolution_date(Date resolution_date) {
        this.resolution_date = resolution_date;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getWardenID() {
        return wardenID;
    }

    public void setWardenID(int wardenID) {
        this.wardenID = wardenID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setBlockID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}