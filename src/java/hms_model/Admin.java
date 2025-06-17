package hms_model;

public class Admin {
    private int admin_id;
    private String adminName;
    private String adminEmail;
    private String adminPho;
    private String adminPass;
    
    // Constructors
    public Admin() {
        // Default constructor
    }
    
    public Admin(int admin_id, String adminName, String adminEmail, String adminPho) {
        this.admin_id = admin_id;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPho = adminPho;
    }
    
    public Admin(int admin_id, String adminName, String adminEmail, String adminPho, String adminPass) {
        this.admin_id = admin_id;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPho = adminPho;
        this.adminPass = adminPass;
    }

    public Admin(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Getters and Setters
    public int getAdmin_id() {
        return admin_id;
    }
    
    public void setAdminId(int admin_id) {
        this.admin_id = admin_id;
    }
    
    public String getAdminName() {
        return adminName;
    }
    
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    public String getAdminEmail() {
        return adminEmail;
    }
    
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
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
    
    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", adminName='" + adminName + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPho='" + adminPho + '\'' +
                '}';
    }

    public void setAdmin_id(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}