// src/main/java/com/hostel/dao/StudentDAO.java
package com.hostel.dao;

import com.hostel.model.Student;
import com.hostel.util.DBConnection; // Assuming you have a DBConnection utility class

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    /**
     * Authenticates a student based on student ID and password.
     *
     * @param studentID The student's ID.
     * @param password  The student's password.
     * @return Student object if authentication is successful, null otherwise.
     */
    public Student authenticateStudent(int studentID, String password) {
        String sql = "SELECT * FROM student WHERE studentID = ? AND sPass = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentID);
            ps.setString(2, password); // In a real application, hash passwords!

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setSName(rs.getString("sName"));
                student.setSPass(rs.getString("sPass")); // Be cautious with returning password
                student.setProgram(rs.getString("program"));
                student.setSemester(rs.getInt("semester"));
                student.setSPho(rs.getString("sPho"));
                student.setSEmail(rs.getString("sEmail"));
                student.setAdminID(rs.getString("adminID"));
                student.setMerit(rs.getDouble("merit"));
                student.setGender(rs.getString("gender").charAt(0));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Log the exception properly in a real application
        }
        return null;
    }

    /**
     * Adds a new student to the database.
     *
     * @param student The Student object to add.
     * @return true if the student was added successfully, false otherwise.
     */
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (studentID, sName, sPass, program, semester, sPho, sEmail, adminID, merit, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getStudentID());
            ps.setString(2, student.getSName());
            ps.setString(3, student.getSPass()); // In a real application, store hashed passwords!
            ps.setString(4, student.getProgram());
            ps.setInt(5, student.getSemester());
            ps.setString(6, student.getSPho());
            ps.setString(7, student.getSEmail());
            ps.setString(8, student.getAdminID());
            ps.setDouble(9, student.getMerit());
            ps.setString(10, String.valueOf(student.getGender()));

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Log the exception (e.g., duplicate studentID)
        }
        return false;
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param studentID The ID of the student to retrieve.
     * @return Student object if found, null otherwise.
     */
    public Student getStudentById(int studentID) {
        String sql = "SELECT * FROM student WHERE studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setSName(rs.getString("sName"));
                student.setSPass(rs.getString("sPass"));
                student.setProgram(rs.getString("program"));
                student.setSemester(rs.getInt("semester"));
                student.setSPho(rs.getString("sPho"));
                student.setSEmail(rs.getString("sEmail"));
                student.setAdminID(rs.getString("adminID"));
                student.setMerit(rs.getDouble("merit"));
                student.setGender(rs.getString("gender").charAt(0));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates an existing student's information in the database.
     *
     * @param student The Student object with updated information.
     * @return true if the student was updated successfully, false otherwise.
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET sName = ?, sPass = ?, program = ?, semester = ?, sPho = ?, sEmail = ?, adminID = ?, merit = ?, gender = ? WHERE studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getSName());
            ps.setString(2, student.getSPass()); // Hash passwords in a real app
            ps.setString(3, student.getProgram());
            ps.setInt(4, student.getSemester());
            ps.setString(5, student.getSPho());
            ps.setString(6, student.getSEmail());
            ps.setString(7, student.getAdminID());
            ps.setDouble(8, student.getMerit());
            ps.setString(9, String.valueOf(student.getGender()));
            ps.setInt(10, student.getStudentID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a student from the database.
     *
     * @param studentID The ID of the student to delete.
     * @return true if the student was deleted successfully, false otherwise.
     */
    public boolean deleteStudent(int studentID) {
        String sql = "DELETE FROM student WHERE studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves all students from the database.
     *
     * @return A list of all Student objects.
     */
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setSName(rs.getString("sName"));
                student.setSPass(rs.getString("sPass"));
                student.setProgram(rs.getString("program"));
                student.setSemester(rs.getInt("semester"));
                student.setSPho(rs.getString("sPho"));
                student.setSEmail(rs.getString("sEmail"));
                student.setAdminID(rs.getString("adminID"));
                student.setMerit(rs.getDouble("merit"));
                student.setGender(rs.getString("gender").charAt(0));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
