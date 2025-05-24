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
public class Report {
    private int reportId;
    private String type;
    private String description;
    private String generatedDate;

    public Report(int reportId, String type, String description, String generatedDate) {
        this.reportId = reportId;
        this.type = type;
        this.description = description;
        this.generatedDate = generatedDate;
    }

    // Getters and Setters
}