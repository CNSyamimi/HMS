package com.hostel.model;

import java.sql.Date; // Use java.sql.Date for database compatibility

public class Bill {
    private String billID;
    private String billType;
    private double amount;
    private Date dueDate;
    private String status;
    private Integer studentID; // Can be null as per SQL if it's a foreign key with ON DELETE SET NULL
    private int allocationID;

    public Bill() {
    }

    public Bill(String billID, String billType, double amount, Date dueDate, String status, Integer studentID, int allocationID) {
        this.billID = billID;
        this.billType = billType;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.studentID = studentID;
        this.allocationID = allocationID;
    }

    // Getters and Setters
    public String getBillID() { return billID; }
    public void setBillID(String billID) { this.billID = billID; }

    public String getBillType() { return billType; }
    public void setBillType(String billType) { this.billType = billType; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getStudentID() { return studentID; }
    public void setStudentID(Integer studentID) { this.studentID = studentID; }

    public int getAllocationID() { return allocationID; }
    public void setAllocationID(int allocationID) { this.allocationID = allocationID; }

    @Override
    public String toString() {
        return "Bill{" +
               "billID='" + billID + '\'' +
               ", billType='" + billType + '\'' +
               ", amount=" + amount +
               ", dueDate=" + dueDate +
               ", status='" + status + '\'' +
               ", studentID=" + studentID +
               ", allocationID=" + allocationID +
               '}';
    }
}