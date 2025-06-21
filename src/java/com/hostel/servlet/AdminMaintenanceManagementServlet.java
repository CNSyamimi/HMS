package com.hostel.servlet;

import com.hostel.dao.MaintenanceDAO; // You'll need a MaintenanceDAO
import com.hostel.model.Maintenance;   // You'll need a Maintenance model

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminMaintenanceManagement")
public class AdminMaintenanceManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MaintenanceDAO maintenanceDAO;

    public void init() {
        maintenanceDAO = new MaintenanceDAO(); // Initialize your MaintenanceDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "list":
                listMaintenanceRequests(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteMaintenanceRequest(request, response);
                break;
            default:
                listMaintenanceRequests(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "updateStatus"; // Default action for POST
        }

        switch (action) {
            case "updateStatus":
                updateMaintenanceStatus(request, response);
                break;
            default:
                listMaintenanceRequests(request, response);
                break;
        }
    }

    private void listMaintenanceRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Maintenance> maintenanceList = maintenanceDAO.getAllMaintenanceRequests(); // Implement getAllMaintenanceRequests
        request.setAttribute("maintenanceList", maintenanceList);
        request.getRequestDispatcher("adminMaintenanceList.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestID = request.getParameter("requestID");
        Maintenance maintenance = maintenanceDAO.getMaintenanceRequestById(requestID); // Implement getMaintenanceRequestById
        request.setAttribute("maintenance", maintenance);
        request.getRequestDispatcher("editMaintenance.jsp").forward(request, response);
    }

    private void deleteMaintenanceRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestID = request.getParameter("requestID");
        boolean deleted = maintenanceDAO.deleteMaintenanceRequest(requestID); // Implement deleteMaintenanceRequest

        if (deleted) {
            request.setAttribute("message", "Maintenance request deleted successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to delete maintenance request.");
        }
        listMaintenanceRequests(request, response);
    }

    private void updateMaintenanceStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestID = request.getParameter("requestID");
        String status = request.getParameter("status");
        String resolutionDateStr = request.getParameter("resolutionDate"); // Optional, "YYYY-MM-DD"

        java.sql.Date resolutionDate = null;
        if (resolutionDateStr != null && !resolutionDateStr.isEmpty()) {
            try {
                resolutionDate = java.sql.Date.valueOf(resolutionDateStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "Invalid Resolution Date format. Please use YYYY-MM-DD.");
                listMaintenanceRequests(request, response);
                return;
            }
        }

        boolean updated = maintenanceDAO.updateMaintenanceStatus(requestID, status, resolutionDate); // Implement this method

        if (updated) {
            request.setAttribute("message", "Maintenance request status updated successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to update maintenance request status.");
        }
        listMaintenanceRequests(request, response);
    }
}
