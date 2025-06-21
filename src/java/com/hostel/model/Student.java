package com.hostel.model;

public class Student {
    private int studentID;
    private String sName;
    private String sPass; // Password - In real apps, hash this!
    private String program;
    private int semester;
    private String sPho; // Student Phone
    private String sEmail;
    private String adminID; // Foreign key to Admin
    private double merit;
    private char gender; // 'M' or 'F'

    public Student() {
    }

    public Student(int studentID, String sName, String sPass, String program, int semester, String sPho, String sEmail, String adminID, double merit, char gender) {
        this.studentID = studentID;
        this.sName = sName;
        this.sPass = sPass;
        this.program = program;
        this.semester = semester;
        this.sPho = sPho;
        this.sEmail = sEmail;
        this.adminID = adminID;
        this.merit = merit;
        this.gender = gender;
    }

    // Getters and Setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSPass() {
        return sPass;
    }

    public void setSPass(String sPass) {
        this.sPass = sPass;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSPho() {
        return sPho;
    }

    public void setSPho(String sPho) {
        this.sPho = sPho;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public double getMerit() {
        return merit;
    }

    public void setMerit(double merit) {
        this.merit = merit;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}