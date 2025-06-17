package hms_model;

public class Room {
    private String roomID;
    private String blockID;
    private int capacity;
    private int curOcc;
    private String roomType;
    private char gender;

    public Room() {
    }

    public Room(String roomID, String blockID, int capacity, int curOcc, String roomType, char gender) {
        this.roomID = roomID;
        this.blockID = blockID;
        this.capacity = capacity;
        this.curOcc = curOcc;
        this.roomType = roomType;
        this.gender = gender;
    }

    // Getters and Setters
    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurOcc() {
        return curOcc;
    }

    public void setCurOcc(int curOcc) {
        this.curOcc = curOcc;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}