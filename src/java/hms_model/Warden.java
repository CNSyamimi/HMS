package hms_model;

public class Warden {
    private int wardenID;
    private String wardenName;
    private String wardenPass;
    private String wardenPho;
    private String wardenEmail;
    private String blockID;
    private int admin_id;

    public Warden() {
    }

    public Warden(int wardenID, String wardenName, String wardenPass, String wardenPho, String wardenEmail, String blockID, int admin_id) {
        this.wardenID = wardenID;
        this.wardenName = wardenName;
        this.wardenPass = wardenPass;
        this.wardenPho = wardenPho;
        this.wardenEmail = wardenEmail;
        this.blockID = blockID;
        this.admin_id = admin_id;
    }

    // Getters and Setters
    public int getWardenID() {
        return wardenID;
    }

    public void setWardenID(int wardenID) {
        this.wardenID = wardenID;
    }

    public String getWardenName() {
        return wardenName;
    }

    public void setWardenName(String wardenName) {
        this.wardenName = wardenName;
    }

    public String getWardenPass() {
        return wardenPass;
    }

    public void setWardenPass(String wardenPass) {
        this.wardenPass = wardenPass;
    }

    public String getWardenPho() {
        return wardenPho;
    }

    public void setWardenPho(String wardenPho) {
        this.wardenPho = wardenPho;
    }

    public String getWardenEmail() {
        return wardenEmail;
    }

    public void setWardenEmail(String wardenEmail) {
        this.wardenEmail = wardenEmail;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}