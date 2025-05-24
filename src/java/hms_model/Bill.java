/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms_model;

/**
 *
 * @author Ailurophile
 */
public class Bill {
    private int billId;
    private int studentId;
    private double amount;
    private String dueDate;
    private String status; // paid or unpaid

    public Bill(int billId, int studentId, double amount, String dueDate, String status) {
        this.billId = billId;
        this.studentId = studentId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters
}