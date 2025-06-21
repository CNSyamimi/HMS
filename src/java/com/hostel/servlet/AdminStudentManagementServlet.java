package com.hostel.servlet;

import com.hostel.dao.StudentDAO; // You'll need to create a StudentDAO
import com.hostel.model.Student;   // You'll need to create a Student model

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminStudentManagement")
public class AdminStudentManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO(); // Initialize your StudentDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "list":
                listStudents(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            case "add":
                request.getRequestDispatcher("addStudent.jsp").forward(request, response); // Forward to add student form
                break;
            default:
                listStudents(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "addUpdate"; // Default action for POST
        }

        switch (action) {
            case "addUpdate":
                addUpdateStudent(request, response);
                break;
            // You might have other POST actions like "search"
            default:
                listStudents(request, response);
                break;
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = studentDAO.getAllStudents(); // Implement getAllStudents in StudentDAO
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("adminStudentList.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        Student student = studentDAO.getStudentById(studentID); // Implement getStudentById in StudentDAO
        request.setAttribute("student", student);
        request.getRequestDispatcher("editStudent.jsp").forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        boolean deleted = studentDAO.deleteStudent(studentID); // Implement deleteStudent in StudentDAO

        if (deleted) {
            request.setAttribute("message", "Student deleted successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to delete student.");
        }
        listStudents(request, response); // Refresh the list
    }

    private void addUpdateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all student parameters from the form
        int studentID = 0; // For new students, this will be 0 or handled by auto-increment
        String studentIDParam = request.getParameter("studentID");
        if (studentIDParam != null && !studentIDParam.isEmpty()) {
            studentID = Integer.parseInt(studentIDParam);
        }

        String sName = request.getParameter("sName");
        String sPass = request.getParameter("sPass");
        String program = request.getParameter("program");
        int semester = Integer.parseInt(request.getParameter("semester"));
        String sPho = request.getParameter("sPho");
        String sEmail = request.getParameter("sEmail");
        String adminID = request.getParameter("adminID"); // Admin who managed this student
        double merit = Double.parseDouble(request.getParameter("merit"));
        char gender = request.getParameter("gender").charAt(0);

        Student student = new Student(studentID, sName, sPass, program, semester, sPho, sEmail, adminID, merit, gender);

        boolean success;
        if (studentID == 0) { // Assuming 0 for new student, or check if student with ID exists
            success = studentDAO.addStudent(student); // Implement addStudent
            if (success) {
                request.setAttribute("message", "Student added successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to add student.");
            }
        } else {
            success = studentDAO.updateStudent(student); // Implement updateStudent
            if (success) {
                request.setAttribute("message", "Student updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to update student.");
            }
        }
        listStudents(request, response); // Refresh the list
    }
}