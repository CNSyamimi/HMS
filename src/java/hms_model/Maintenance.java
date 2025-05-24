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
public class Maintenance {
    private int requestId;
    private int roomId;
    private String issue;
    private String status; // e.g., pending, completed

    public Maintenance(int requestId, int roomId, String issue, String status) {
        this.requestId = requestId;
        this.roomId = roomId;
        this.issue = issue;
        this.status = status;
    }

    // Getters and Setters
}