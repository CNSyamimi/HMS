package com.hostel.servlet;

import com.hostel.dao.AdminDAO;
import com.hostel.model.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;

    public void init() {
        adminDAO = new AdminDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminID = request.getParameter("adminID");
        String password = request.getParameter("password");

        Admin admin = adminDAO.authenticateAdmin(adminID, password);

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentAdmin", admin);
            response.sendRedirect("adminDashboard.jsp"); // Redirect to admin dashboard
        } else {
            request.setAttribute("errorMessage", "Invalid Admin ID or Password.");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response); // Forward back to login page
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For GET requests, simply forward to the login JSP
        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
    }
}