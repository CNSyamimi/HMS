package hms_model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String fullName;
    private String matricNumber;
    private String email;
    private String password;
    private String program;
    private int semester;
    private String phone;
    
    // Constructors
    public Student() {
        // Default constructor
    }
    
    public Student(String fullName, String matricNumber, String email, String password, 
                  String program, int semester, String phone) {
        this.fullName = fullName;
        this.matricNumber = matricNumber;
        this.email = email;
        this.password = password;
        this.program = program;
        this.semester = semester;
        this.phone = phone;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getMatricNumber() {
        return matricNumber;
    }
    
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", matricNumber='" + matricNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", program='" + program + '\'' +
                ", semester=" + semester +
                ", phone='" + phone + '\'' +
                '}';
    }
}