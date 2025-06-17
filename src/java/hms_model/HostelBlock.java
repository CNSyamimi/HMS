package hms_model;

public class HostelBlock {
    private String blockID;
    private String blockName;
    private int totalRooms;
    private Integer wardenID;

    public HostelBlock() {
    }

    public HostelBlock(String blockID, String blockName, int totalRooms, Integer wardenID) {
        this.blockID = blockID;
        this.blockName = blockName;
        this.totalRooms = totalRooms;
        this.wardenID = wardenID;
    }

    // Getters and Setters
    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public Integer getWardenID() {
        return wardenID;
    }

    public void setWardenID(Integer wardenID) {
        this.wardenID = wardenID;
    }
}