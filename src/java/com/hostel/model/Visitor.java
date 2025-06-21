package com.hostel.model;

import java.sql.Date;
import java.sql.Time;

public class Visitor {
    private String visitorID;
    private Integer studentID; // Can be null
    private String visitorName;
    private Date visitDate;
    private Time visitTime;
    private String visPass; // Visitor Pass

    public Visitor() {
    }

    public Visitor(String visitorID, Integer studentID, String visitorName, Date visitDate, Time visitTime, String visPass) {
        this.visitorID = visitorID;
        this.studentID = studentID;
        this.visitorName = visitorName;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.visPass = visPass;
    }

    // Getters and Setters
    public String getVisitorID() { return visitorID; }
    public void setVisitorID(String visitorID) { this.visitorID = visitorID; }

    public Integer getStudentID() { return studentID; }
    public void setStudentID(Integer studentID) { this.studentID = studentID; }

    public String getVisitorName() { return visitorName; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }

    public Date getVisitDate() { return visitDate; }
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }

    public Time getVisitTime() { return visitTime; }
    public void setVisitTime(Time visitTime) { this.visitTime = visitTime; }

    public String getVisPass() { return visPass; }
    public void setVisPass(String visPass) { this.visPass = visPass; }

    @Override
    public String toString() {
        return "Visitor{" +
               "visitorID='" + visitorID + '\'' +
               ", studentID=" + studentID +
               ", visitorName='" + visitorName + '\'' +
               ", visitDate=" + visitDate +
               ", visitTime=" + visitTime +
               ", visPass='" + visPass + '\'' +
               '}';
    }
}
