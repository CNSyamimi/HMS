package hms_model;

import java.math.BigDecimal;
import java.sql.Date;

public class Bill {
    private int billID;
    private String billType;
    private BigDecimal amount;
    private Date dueDate;
    private String status;
    private int studentID;
    private int allocationID;

    public Bill() {
    }

    public Bill(int billID, String billType, BigDecimal amount, Date dueDate, String status, int studentID, int allocationID) {
        this.billID = billID;
        this.billType = billType;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.studentID = studentID;
        this.allocationID = allocationID;
    }

    // Getters and Setters
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    public int getAllocationID() {
        return allocationID;
    }

    public void setAllocationID(int allocationID) {
        this.allocationID = allocationID;
    }
}