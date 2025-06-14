package hms_model;

public class Admin {
    private int adminId;
    private String adminName;
    
    public Admin(int adminId, String adminName) {
        this.adminId = adminId;
        this.adminName = adminName;
    }
    
    // Getters and Setters
    public int getAdminId() { return adminId; }
    public String getAdminName() { return adminName; }
    public void setAdminId(int adminId) { this.adminId = adminId; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
}