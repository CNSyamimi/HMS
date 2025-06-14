package hms_model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int studentID;  // Changed from id to match DB
    private String sName;   // Changed from fullName
    private String sEmail;  // Changed from email
    private String sPass;   // Changed from password
    private String program;
    private int semester;
    private String sPho;    // Changed from phone
    private int admin_id;   // Added new field
    
    // Constructors
    public Student() {
        // Default constructor
    }
    
    public Student(String sName, String studentID, String sEmail, String sPass, 
                  String program, int semester, String sPho) {
        this.sName = sName;
        this.studentID = Integer.parseInt(studentID); // Assuming studentID is numeric
        this.sEmail = sEmail;
        this.sPass = sPass;
        this.program = program;
        this.semester = semester;
        this.sPho = sPho;
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
    
    public String getSEmail() {
        return sEmail;
    }
    
    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
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
    
    public int getAdmin_id() {
        return admin_id;
    }
    
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", sName='" + sName + '\'' +
                ", sEmail='" + sEmail + '\'' +
                ", sPass='" + sPass + '\'' +
                ", program='" + program + '\'' +
                ", semester=" + semester +
                ", sPho='" + sPho + '\'' +
                ", admin_id=" + admin_id +
                '}';
    }
}