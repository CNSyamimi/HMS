package com.hostel.servlet;

import com.hostel.dao.WardenDAO; // You'll need to create a WardenDAO
import com.hostel.model.Warden;   // You'll need to create a Warden model

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminWardenManagement")
public class AdminWardenManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WardenDAO wardenDAO;

    public void init() {
        wardenDAO = new WardenDAO(); // Initialize your WardenDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "list":
                listWardens(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteWarden(request, response);
                break;
            case "add":
                request.getRequestDispatcher("addWarden.jsp").forward(request, response);
                break;
            default:
                listWardens(request, response);
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
                addUpdateWarden(request, response);
                break;
            default:
                listWardens(request, response);
                break;
        }
    }

    private void listWardens(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Warden> wardenList = wardenDAO.getAllWardens(); // Implement getAllWardens
        request.setAttribute("wardenList", wardenList);
        request.getRequestDispatcher("adminWardenList.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wardenID = request.getParameter("wardenID");
        Warden warden = wardenDAO.getWardenById(wardenID); // Implement getWardenById
        request.setAttribute("warden", warden);
        request.getRequestDispatcher("editWarden.jsp").forward(request, response);
    }

    private void deleteWarden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wardenID = request.getParameter("wardenID");
        boolean deleted = wardenDAO.deleteWarden(wardenID); // Implement deleteWarden

        if (deleted) {
            request.setAttribute("message", "Warden deleted successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to delete warden.");
        }
        listWardens(request, response);
    }

    private void addUpdateWarden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wardenID = request.getParameter("wardenID");
        String wardenName = request.getParameter("wardenName");
        String wardenPass = request.getParameter("wardenPass");
        String wardenPho = request.getParameter("wardenPho");
        String wardenEmail = request.getParameter("wardenEmail");
        String blockID = request.getParameter("blockID");
        String admin_id = request.getParameter("admin_id"); // Admin who added/updated this warden

        Warden warden = new Warden(wardenID, wardenName, wardenPass, wardenPho, wardenEmail, blockID, admin_id);

        boolean success;
        // Assuming if wardenID exists, it's an update, otherwise, it's an add
        if (wardenDAO.getWardenById(wardenID) != null) { // Check if warden exists for update
            success = wardenDAO.updateWarden(warden);
            if (success) {
                request.setAttribute("message", "Warden updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to update warden.");
            }
        } else {
            success = wardenDAO.addWarden(warden);
            if (success) {
                request.setAttribute("message", "Warden added successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to add warden. Warden ID might already exist.");
            }
        }
        listWardens(request, response);
    }
}