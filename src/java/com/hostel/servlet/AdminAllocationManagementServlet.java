package com.hostel.servlet;

import com.hostel.dao.AllocationDAO; // You'll need to create an AllocationDAO
import com.hostel.model.Allocation;   // You'll need to create an Allocation model

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/adminAllocationManagement")
public class AdminAllocationManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AllocationDAO allocationDAO;

    public void init() {
        allocationDAO = new AllocationDAO(); // Initialize your AllocationDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "list":
                listAllocations(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteAllocation(request, response);
                break;
            case "add":
                request.getRequestDispatcher("addAllocation.jsp").forward(request, response);
                break;
            default:
                listAllocations(request, response);
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
                addUpdateAllocation(request, response);
                break;
            default:
                listAllocations(request, response);
                break;
        }
    }

    private void listAllocations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Allocation> allocationList = allocationDAO.getAllAllocations(); // Implement getAllAllocations
        request.setAttribute("allocationList", allocationList);
        request.getRequestDispatcher("adminAllocationList.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int allocationID = Integer.parseInt(request.getParameter("allocationID"));
        Allocation allocation = allocationDAO.getAllocationById(allocationID); // Implement getAllocationById
        request.setAttribute("allocation", allocation);
        request.getRequestDispatcher("editAllocation.jsp").forward(request, response);
    }

    private void deleteAllocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int allocationID = Integer.parseInt(request.getParameter("allocationID"));
        boolean deleted = allocationDAO.deleteAllocation(allocationID); // Implement deleteAllocation

        if (deleted) {
            request.setAttribute("message", "Allocation deleted successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to delete allocation.");
        }
        listAllocations(request, response);
    }

    private void addUpdateAllocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int allocationID = 0; // Default for new, will be set for update
        String allocationIDParam = request.getParameter("allocationID");
        if (allocationIDParam != null && !allocationIDParam.isEmpty()) {
            allocationID = Integer.parseInt(allocationIDParam);
        }

        String dateFromStr = request.getParameter("date_from");
        String dateToStr = request.getParameter("date_to");
        String status = request.getParameter("status");
        Integer studentID = null; // Use Integer for nullable
        String studentIDParam = request.getParameter("studentID");
        if (studentIDParam != null && !studentIDParam.isEmpty()) {
            try {
                studentID = Integer.parseInt(studentIDParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid Student ID format.");
                listAllocations(request, response);
                return;
            }
        }
        String roomID = request.getParameter("roomID");
        String blockID = request.getParameter("blockID");

        Date date_from = null;
        if (dateFromStr != null && !dateFromStr.isEmpty()) {
            try {
                date_from = Date.valueOf(dateFromStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "Invalid 'Date From' format. Please use YYYY-MM-DD.");
                listAllocations(request, response);
                return;
            }
        }

        Date date_to = null;
        if (dateToStr != null && !dateToStr.isEmpty()) {
            try {
                date_to = Date.valueOf(dateToStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "Invalid 'Date To' format. Please use YYYY-MM-DD.");
                listAllocations(request, response);
                return;
            }
        }

        // Create Allocation object with new attributes
        Allocation allocation = new Allocation(allocationID, date_from, date_to, status, studentID, roomID, blockID);

        boolean success;
        if (allocationID == 0) { // Add new allocation
            success = allocationDAO.addAllocation(allocation); // Implement addAllocation
            if (success) {
                request.setAttribute("message", "Allocation added successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to add allocation. Allocation ID might already exist or invalid data.");
            }
        } else { // Update existing allocation
            success = allocationDAO.updateAllocation(allocation); // Implement updateAllocation
            if (success) {
                request.setAttribute("message", "Allocation updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to update allocation.");
            }
        }
        listAllocations(request, response);
    }
}
