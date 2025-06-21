// src/main/java/com/hostel/model/Admin.java
package com.hostel.model;

public class Admin {
    private String adminID;
    private String adminName;
    private String adminPho; // Phone number
    private String adminPass; // Password
    private String adminEmail;

    // Default constructor
    public Admin() {
    }

    // Parameterized constructor
    public Admin(String adminID, String adminName, String adminPho, String adminPass, String adminEmail) {
        this.adminID = adminID;
        this.adminName = adminName;
        this.adminPho = adminPho;
        this.adminPass = adminPass;
        this.adminEmail = adminEmail;
    }

    // Getters and Setters
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPho() {
        return adminPho;
    }

    public void setAdminPho(String adminPho) {
        this.adminPho = adminPho;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Override
    public String toString() {
        return "Admin{" +
               "adminID='" + adminID + '\'' +
               ", adminName='" + adminName + '\'' +
               ", adminPho='" + adminPho + '\'' +
               ", adminEmail='" + adminEmail + '\'' +
               '}';
    }
}
