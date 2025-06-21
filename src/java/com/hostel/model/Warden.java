package com.hostel.model;

public class Warden {
    private String wardenID;
    private String wardenName;
    private String wardenPass; // Password
    private String wardenPho; // Phone number
    private String wardenEmail;
    private String blockID; // Foreign key from HostelBlock table
    private String admin_id; // Foreign key from Admin table

    public Warden() {
    }

    public Warden(String wardenID, String wardenName, String wardenPass, String wardenPho, String wardenEmail, String blockID, String admin_id) {
        this.wardenID = wardenID;
        this.wardenName = wardenName;
        this.wardenPass = wardenPass;
        this.wardenPho = wardenPho;
        this.wardenEmail = wardenEmail;
        this.blockID = blockID;
        this.admin_id = admin_id;
    }

    // Getters and Setters
    public String getWardenID() { return wardenID; }
    public void setWardenID(String wardenID) { this.wardenID = wardenID; }

    public String getWardenName() { return wardenName; }
    public void setWardenName(String wardenName) { this.wardenName = wardenName; }

    public String getWardenPass() { return wardenPass; }
    public void setWardenPass(String wardenPass) { this.wardenPass = wardenPass; }

    public String getWardenPho() { return wardenPho; }
    public void setWardenPho(String wardenPho) { this.wardenPho = wardenPho; }

    public String getWardenEmail() { return wardenEmail; }
    public void setWardenEmail(String wardenEmail) { this.wardenEmail = wardenEmail; }

    public String getBlockID() { return blockID; }
    public void setBlockID(String blockID) { this.blockID = blockID; }

    public String getAdmin_id() { return admin_id; }
    public void setAdmin_id(String admin_id) { this.admin_id = admin_id; }

    @Override
    public String toString() {
        return "Warden{" +
               "wardenID='" + wardenID + '\'' +
               ", wardenName='" + wardenName + '\'' +
               ", wardenPho='" + wardenPho + '\'' +
               ", wardenEmail='" + wardenEmail + '\'' +
               ", blockID='" + blockID + '\'' +
               ", admin_id='" + admin_id + '\'' +
               '}';
    }
}