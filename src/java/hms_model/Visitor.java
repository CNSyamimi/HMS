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
public class Visitor {
    private int visitorId;
    private String name;
    private String relationship;
    private String visitDate;
    private int studentId;

    public Visitor(int visitorId, String name, String relationship, String visitDate, int studentId) {
        this.visitorId = visitorId;
        this.name = name;
        this.relationship = relationship;
        this.visitDate = visitDate;
        this.studentId = studentId;
    }

    // Getters and Setters
    public int getVisitorId(int visitorId){
        return visitorId;
    }
    public void setVisitorId(int visitorId){
        this.visitorId = visitorId;
    }
    
    public String getName(String name){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getRelationship(String relationship){
        return relationship;
    }
    public void setRelationship(String relationship){
        this.relationship = relationship;
    }
    
    public String getVisitDate(String visitDate){
        return visitDate;
    }
    public void setVisitDate(String visitDate){
        this.visitDate = visitDate;
    }
    
    public int getStudentId(int studentId){
        return studentId;
    }
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }
}