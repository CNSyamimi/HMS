// src/main/java/com/hostel/servlet/StudentLoginServlet.java
package com.hostel.servlet;

import com.hostel.dao.StudentDAO;
import com.hostel.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/studentLogin")
public class StudentLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Just forward to the login JSP for GET requests
        request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // 'login' or 'register'

        if ("register".equals(action)) {
            handleStudentRegistration(request, response);
        } else if ("login".equals(action)) {
            handleStudentLogin(request, response);
        } else {
            // Default to login if action is not specified or invalid
            handleStudentLogin(request, response);
        }
    }

    private void handleStudentLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIDStr = request.getParameter("studentID");
        String password = request.getParameter("sPass"); // Renamed from 'password' to 'sPass' based on Student model

        if (studentIDStr == null || studentIDStr.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("loginErrorMessage", "Student ID and Password are required.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
            return;
        }

        int studentID;
        try {
            studentID = Integer.parseInt(studentIDStr);
        } catch (NumberFormatException e) {
            request.setAttribute("loginErrorMessage", "Invalid Student ID format.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
            return;
        }

        Student student = studentDAO.authenticateStudent(studentID, password);

        if (student != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentStudent", student);
            response.sendRedirect("studentDashboard.jsp"); // Redirect to student dashboard
        } else {
            request.setAttribute("loginErrorMessage", "Invalid Student ID or Password.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response); // Forward back to login page
        }
    }

    private void handleStudentRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentID = 0; // Will be parsed from input
        String studentIDParam = request.getParameter("studentID");
        if (studentIDParam != null && !studentIDParam.isEmpty()) {
            try {
                studentID = Integer.parseInt(studentIDParam);
            } catch (NumberFormatException e) {
                request.setAttribute("registerErrorMessage", "Invalid Student ID format.");
                request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("registerErrorMessage", "Student ID is required.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
            return;
        }

        // Check if studentID already exists
        if (studentDAO.getStudentById(studentID) != null) {
            request.setAttribute("registerErrorMessage", "Student ID already exists. Please choose a different ID or log in.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
            return;
        }

        String sName = request.getParameter("sName");
        String sPass = request.getParameter("sPass");
        String confirmSPass = request.getParameter("confirmSPass");
        String program = request.getParameter("program");
        String semesterStr = request.getParameter("semester");
        String sPho = request.getParameter("sPho");
        String sEmail = request.getParameter("sEmail");
        // For self-registration, adminID can be null or a default value, or omitted
        String adminID = null; // No admin involved in self-registration
        double merit = 0.0; // Default merit for new registrations
        char gender = request.getParameter("gender").charAt(0);

        // Basic validation
        if (sName == null || sName.isEmpty() ||
            sPass == null || sPass.isEmpty() ||
            confirmSPass == null || confirmSPass.isEmpty() ||
            program == null || program.isEmpty() ||
            semesterStr == null || semesterStr.isEmpty() ||
            sPho == null || sPho.isEmpty() ||
            sEmail == null || sEmail.isEmpty() ||
            request.getParameter("gender") == null || request.getParameter("gender").isEmpty()) {
            request.setAttribute("registerErrorMessage", "All fields are required.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
            return;
        }

        if (!sPass.equals(confirmSPass)) {
            request.setAttribute("registerErrorMessage", "Passwords do not match.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
            return;
        }

        int semester;
        try {
            semester = Integer.parseInt(semesterStr);
        } catch (NumberFormatException e) {
            request.setAttribute("registerErrorMessage", "Invalid Semester format.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
            return;
        }

        Student newStudent = new Student(studentID, sName, sPass, program, semester, sPho, sEmail, adminID, merit, gender);

        boolean success = studentDAO.addStudent(newStudent);

        if (success) {
            request.setAttribute("loginMessage", "Registration successful! You can now log in.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
        } else {
            request.setAttribute("registerErrorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("studentLogin.jsp").forward(request, response);
        }
    }
}
