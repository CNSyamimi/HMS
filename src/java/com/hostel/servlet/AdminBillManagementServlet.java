package com.hostel.servlet;

import com.hostel.dao.BillDAO;
import com.hostel.model.Bill;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminBillManagement")
public class AdminBillManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int RECORDS_PER_PAGE = 5; // Number of records to show per page
    private BillDAO billDAO;

    public void init() {
        billDAO = new BillDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listBills(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteBill(request, response);
                break;
            case "add":
                request.getRequestDispatcher("addBill.jsp").forward(request, response);
                break;
            default:
                listBills(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "addUpdate";
        }

        switch (action) {
            case "addUpdate":
                addUpdateBill(request, response);
                break;
            default:
                listBills(request, response);
                break;
        }
    }

    private void listBills(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get search parameter if exists
        String search = request.getParameter("search");
        
        // Get current page or default to 1
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            // Default to page 1 if page parameter is invalid
        }
        
        // Calculate pagination parameters
        int totalRecords;
        List<Bill> billList;
        
        if (search != null && !search.isEmpty()) {
            totalRecords = billDAO.getSearchBillCount(search);
            billList = billDAO.searchBills(search, currentPage, RECORDS_PER_PAGE);
        } else {
            totalRecords = billDAO.getBillCount();
            billList = billDAO.getAllBills(currentPage, RECORDS_PER_PAGE);
        }
        
        int totalPages = (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE);
        
        // Calculate start and end item numbers
        int startItem = (currentPage - 1) * RECORDS_PER_PAGE + 1;
        int endItem = Math.min(currentPage * RECORDS_PER_PAGE, totalRecords);
        
        // Calculate start and end page numbers for pagination display
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(totalPages, currentPage + 2);
        
        // Set attributes for JSP
        request.setAttribute("billList", billList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalItems", totalRecords);
        request.setAttribute("startItem", startItem);
        request.setAttribute("endItem", endItem);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        
        if (search != null) {
            request.setAttribute("search", search);
        }
        
        request.getRequestDispatcher("adminBillList.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billID = request.getParameter("billID");
        Bill bill = billDAO.getBillById(billID);
        request.setAttribute("bill", bill);
        request.getRequestDispatcher("editBill.jsp").forward(request, response);
    }

    private void deleteBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billID = request.getParameter("billID");
        boolean deleted = billDAO.deleteBill(billID);

        if (deleted) {
            request.setAttribute("message", "Bill deleted successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to delete bill.");
        }
        listBills(request, response);
    }

    private void addUpdateBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billID = request.getParameter("billID");
        String billType = request.getParameter("billType");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String dueDateStr = request.getParameter("dueDate");
        String status = request.getParameter("status");
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        int allocationID = Integer.parseInt(request.getParameter("allocationID"));

        java.sql.Date dueDate = null;
        if (dueDateStr != null && !dueDateStr.isEmpty()) {
            try {
                dueDate = java.sql.Date.valueOf(dueDateStr);
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "Invalid Due Date format. Please use YYYY-MM-DD.");
                listBills(request, response);
                return;
            }
        }

        Bill bill = new Bill(billID, billType, amount, dueDate, status, studentID, allocationID);

        boolean success;
        if (billDAO.getBillById(billID) != null) {
            success = billDAO.updateBill(bill);
            if (success) {
                request.setAttribute("message", "Bill updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to update bill.");
            }
        } else {
            success = billDAO.addBill(bill);
            if (success) {
                request.setAttribute("message", "Bill added successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to add bill. Bill ID might already exist.");
            }
        }
        listBills(request, response);
    }
}