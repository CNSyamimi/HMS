package com.hostel.model;

public class HostelBlock {
    private String blockID;
    private String blockName;
    private int totalRooms;
    private String wardenID; // Foreign key from Warden table (can be null if no warden assigned)

    public HostelBlock() {
    }

    public HostelBlock(String blockID, String blockName, int totalRooms, String wardenID) {
        this.blockID = blockID;
        this.blockName = blockName;
        this.totalRooms = totalRooms;
        this.wardenID = wardenID;
    }

    // Getters and Setters
    public String getBlockID() { return blockID; }
    public void setBlockID(String blockID) { this.blockID = blockID; }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }

    public int getTotalRooms() { return totalRooms; }
    public void setTotalRooms(int totalRooms) { this.totalRooms = totalRooms; }

    public String getWardenID() { return wardenID; }
    public void setWardenID(String wardenID) { this.wardenID = wardenID; }

    @Override
    public String toString() {
        return "HostelBlock{" +
               "blockID='" + blockID + '\'' +
               ", blockName='" + blockName + '\'' +
               ", totalRooms=" + totalRooms +
               ", wardenID='" + wardenID + '\'' +
               '}';
    }
}