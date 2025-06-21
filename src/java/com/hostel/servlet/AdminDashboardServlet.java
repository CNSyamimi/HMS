package com.hostel.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Do not create a new session if one doesn't exist

        // Check if admin is logged in
        if (session != null && session.getAttribute("currentAdmin") != null) {
            // Admin is logged in, forward to dashboard JSP
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        } else {
            // Admin is not logged in, redirect to login page
            response.sendRedirect("adminLogin.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Typically, a dashboard is viewed via GET. POST can be used for actions
        // like "refresh data" or "submit a dashboard configuration change",
        // but for a simple dashboard view, GET is sufficient.
        doGet(request, response);
    }
}