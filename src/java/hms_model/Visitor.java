package hms_model;

import java.sql.Date;
import java.sql.Time;

public class Visitor {
    private int visitorID;
    private int studentID;
    private String visitorName;
    private Date visitDate;
    private Time visitTime;
    private String visPass;

    public Visitor() {
    }

    public Visitor(int visitorID, int studentID, String visitorName, Date visitDate, Time visitTime, String visPass) {
        this.visitorID = visitorID;
        this.studentID = studentID;
        this.visitorName = visitorName;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.visPass = visPass;
    }

    // Getters and Setters
    public int getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(int visitorID) {
        this.visitorID = visitorID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Time getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Time visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisPass() {
        return visPass;
    }

    public void setVisPass(String visPass) {
        this.visPass = visPass;
    }
}