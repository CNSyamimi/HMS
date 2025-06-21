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

@WebServlet("/adminProfile")
public class AdminProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;

    public void init() {
        adminDAO = new AdminDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Do not create a new session if one doesn't exist

        if (session != null && session.getAttribute("currentAdmin") != null) {
            String action = request.getParameter("action");
            Admin admin = (Admin) session.getAttribute("currentAdmin");

            if ("edit".equals(action)) {
                // Fetch the latest admin data from DB for editing
                Admin latestAdmin = adminDAO.getAdminById(admin.getAdminID());
                if (latestAdmin != null) {
                    request.setAttribute("admin", latestAdmin);
                    request.getRequestDispatcher("adminProfileEdit.jsp").forward(request, response);
                } else {
                    session.invalidate(); // Invalidate if admin not found in DB
                    request.setAttribute("errorMessage", "Admin data not found. Please log in again.");
                    request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
                }
            } else {
                // Default action: show profile view
                request.getRequestDispatcher("adminProfileView.jsp").forward(request, response); // This is now adminProfileView.jsp
            }
        } else {
            response.sendRedirect("adminLogin.jsp"); // Redirect to login if not authenticated
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("currentAdmin") != null) {
            Admin currentAdmin = (Admin) session.getAttribute("currentAdmin");

            // Get updated parameters from the form
            String adminName = request.getParameter("adminName");
            String adminPho = request.getParameter("adminPho");
            String adminEmail = request.getParameter("adminEmail");
            String adminPass = request.getParameter("adminPass"); // Assuming password can be updated

            // Update the current admin object
            currentAdmin.setAdminName(adminName);
            currentAdmin.setAdminPho(adminPho);
            currentAdmin.setAdminEmail(adminEmail);
            if (adminPass != null && !adminPass.isEmpty()) {
                currentAdmin.setAdminPass(adminPass); // Update password only if provided
            }

            // Update in the database
            boolean updated = adminDAO.updateAdmin(currentAdmin);

            if (updated) {
                session.setAttribute("currentAdmin", currentAdmin); // Update session with new data
                request.setAttribute("message", "Profile updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to update profile.");
            }
            // After POST, redirect to the profile view page (GET request)
            // Use sendRedirect to prevent form resubmission on refresh
            response.sendRedirect("adminProfile?message=" + (updated ? "updated" : "failed")); // Pass message via URL param
        } else {
            response.sendRedirect("adminLogin.jsp");
        }
    }
}