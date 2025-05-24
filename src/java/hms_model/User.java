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
public class User {
    private int userId;
    private String name;
    private String email;
    private String role; // student, warden, admin

    public User(int userId, String name, String email, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId(int userId){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public String getName(String name){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getEmail(String email){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getRole(String role){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }
}