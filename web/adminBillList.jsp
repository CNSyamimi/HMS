<%-- 
    Document   : adminBillList
    Created on : 21 Jun 2025, 2:14:50 pm
    Author     : USER
--%>

<%-- src/main/webapp/adminBillList.jsp --%>
<%-- 
    Document   : adminBillList
    Created on : 21 Jun 2025, 2:14:50 pm
    Author     : USER
--%>

<%-- src/main/webapp/adminBillList.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Bills - Admin Panel</title>
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
        }
        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            color: white;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
            min-height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
        }
        .sidebar h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #ecf0f1;
            font-size: 1.5em;
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
        }
        .sidebar ul li {
            margin-bottom: 15px;
        }
        .sidebar ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            display: block;
            border-radius: 8px;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .sidebar ul li a:hover {
            background-color: #34495e;
            transform: translateX(5px);
        }
        .sidebar ul li a.active {
            background-color: #007bff;
            font-weight: bold;
        }
        .main-content {
            margin-left: 270px;
            padding: 30px;
            flex-grow: 1;
            width: calc(100% - 270px);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            background-color: #ffffff;
            padding: 20px 30px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
        }
        .header h1 {
            margin: 0;
            color: #2c3e50;
            font-size: 2em;
        }
        .action-buttons {
            display: flex;
            gap: 10px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
            font-size: 0.9em;
            transition: background-color 0.3s ease, transform 0.2s ease;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            cursor: pointer;
            border: none;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }
        .btn-success {
            background-color: #28a745;
            color: white;
        }
        .btn-success:hover {
            background-color: #218838;
            transform: translateY(-2px);
        }
        .btn-warning {
            background-color: #ffc107;
            color: #333;
        }
        .btn-warning:hover {
            background-color: #e0a800;
            transform: translateY(-2px);
        }
        .btn-danger {
            background-color: #dc3545;
            color: white;
        }
        .btn-danger:hover {
            background-color: #c82333;
            transform: translateY(-2px);
        }
        .btn-outline {
            background-color: transparent;
            border: 1px solid #007bff;
            color: #007bff;
        }
        .btn-outline:hover {
            background-color: #007bff;
            color: white;
        }
        .message-container {
            margin-top: 20px;
            padding: 12px;
            border-radius: 8px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }
        .success-message {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        .table-container {
            background-color: #ffffff;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
            overflow: hidden;
            border: 1px solid #e0e0e0;
            height: 520px; /* Fixed height */
            display: flex;
            flex-direction: column;
        }
        
        .table-wrapper {
            overflow: hidden;
            flex-grow: 1;
            margin-bottom: 20px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        table thead {
            position: sticky;
            top: 0;
            background-color: #f8f9fa;
            z-index: 10;
        }
        
        table tbody {
            display: block;
            overflow: hidden;
            height: 450px; /* Fixed height for table body */
        }
        
        table tr {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        table tr:hover {
            background-color: #e9ecef;
        }
        
        .search-container {
            margin-bottom: 20px;
            display: flex;
            gap: 10px;
        }
        .search-container input {
            padding: 10px 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            flex-grow: 1;
            font-size: 1em;
        }
        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
            gap: 5px;
        }
        .pagination a, .pagination span {
            padding: 8px 15px;
            border-radius: 5px;
            text-decoration: none;
            color: #333;
            border: 1px solid #ddd;
            transition: background-color 0.3s ease;
        }
        .pagination a:hover {
            background-color: #f0f0f0;
        }
        .pagination .active {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        .pagination .disabled {
            color: #aaa;
            pointer-events: none;
        }
        .results-info {
            text-align: right;
            margin-bottom: 10px;
            color: #666;
            font-size: 0.9em;
        }
         .pagination-controls {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: auto;
            padding-top: 15px;
            border-top: 1px solid #eee;
        }
        
        .pagination-info {
            font-size: 0.9em;
            color: #666;
        }
        
        .page-numbers {
            display: flex;
            gap: 5px;
        }
        
        .page-btn {
            padding: 5px 10px;
            border: 1px solid #ddd;
            background: white;
            border-radius: 4px;
            cursor: pointer;
        }
        
        .page-btn.active {
            background: #007bff;
            color: white;
            border-color: #007bff;
        }
        
        .page-btn:hover:not(.active) {
            background: #f0f0f0;
        }

    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <nav>
            <ul>
                <li><a href="adminDashboard">Dashboard</a></li>
                <li><a href="adminProfile">Profile</a></li>
                <li><a href="adminStudentManagement">Manage Students</a></li>
                <li><a href="adminWardenManagement">Manage Wardens</a></li>
                <li><a href="adminBillManagement" class="active">Manage Bills</a></li>
                <li><a href="adminMaintenanceManagement">Manage Maintenance</a></li>
                <li><a href="adminAllocationManagement"> Manage Allocations</a></li>
            </ul>
        </nav>
        <a href="adminLogout" class="logout-btn">Logout</a>
    </div>

    <div class="main-content">
        <div class="header">
            <h1>Manage Bills</h1>
            <div class="action-buttons">
                <a href="adminBillManagement?action=add" class="btn btn-success">Add New Bill</a>
            </div>
        </div>

        <c:if test="${not empty requestScope.message}">
            <p class="message-container success-message">${requestScope.message}</p>
        </c:if>
        <c:if test="${not empty requestScope.errorMessage}">
            <p class="message-container error-message">${requestScope.errorMessage}</p>
        </c:if>

       <div class="table-container">
        <div class="search-container">
            <input type="text" id="searchInput" placeholder="Search bills by ID, type, or student..." value="${param.search}">
            <button class="btn btn-primary" onclick="searchBills()">Search</button>
            <c:if test="${not empty param.search}">
                <button class="btn btn-outline" onclick="clearSearch()">Clear</button>
            </c:if>
        </div>
        
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th style="width: 10%">Bill ID</th>
                        <th style="width: 15%">Type</th>
                        <th style="width: 12%">Amount</th>
                        <th style="width: 12%">Due Date</th>
                        <th style="width: 12%">Status</th>
                        <th style="width: 12%">Student ID</th>
                        <th style="width: 12%">Allocation ID</th>
                        <th style="width: 15%">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bill" items="${requestScope.billList}">
                        <tr>
                            <td>${bill.billID}</td>
                            <td>${bill.billType}</td>
                            <td><fmt:formatNumber value="${bill.amount}" type="currency" currencySymbol="RM " /></td>
                            <td><fmt:formatDate value="${bill.dueDate}" pattern="yyyy-MM-dd" /></td>
                            <td>${bill.status}</td>
                            <td>${bill.studentID != null ? bill.studentID : 'N/A'}</td>
                            <td>${bill.allocationID}</td>
                            <td>
                                <a href="adminBillManagement?action=edit&billID=${bill.billID}" class="btn btn-warning">Edit</a>
                                <a href="adminBillManagement?action=delete&billID=${bill.billID}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete bill ${bill.billID}?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty requestScope.billList}">
                        <tr>
                            <td colspan="8" style="text-align: center;">No bills found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
        
        <div class="pagination-controls">
            <div class="pagination-info">
                Showing ${requestScope.startItem}-${requestScope.endItem} of ${requestScope.totalItems}
            </div>
            <div class="page-numbers">
                <c:if test="${requestScope.currentPage > 1}">
                    <button class="page-btn" onclick="goToPage(${requestScope.currentPage - 1})">Previous</button>
                </c:if>
                
                <c:if test="${requestScope.currentPage > 3}">
                    <button class="page-btn" onclick="goToPage(1)">1</button>
                    <c:if test="${requestScope.currentPage > 4}">
                        <span>...</span>
                    </c:if>
                </c:if>
                
                <c:forEach begin="${requestScope.startPage}" end="${requestScope.endPage}" var="i">
                    <button class="page-btn ${i == requestScope.currentPage ? 'active' : ''}" onclick="goToPage(${i})">${i}</button>
                </c:forEach>
                
                <c:if test="${requestScope.currentPage < requestScope.totalPages - 2}">
                    <c:if test="${requestScope.currentPage < requestScope.totalPages - 3}">
                        <span>...</span>
                    </c:if>
                    <button class="page-btn" onclick="goToPage(${requestScope.totalPages})">${requestScope.totalPages}</button>
                </c:if>
                
                <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                    <button class="page-btn" onclick="goToPage(${requestScope.currentPage + 1})">Next</button>
                </c:if>
            </div>
        </div>
    </div>
    
    <script>
        function searchBills() {
            const searchTerm = document.getElementById('searchInput').value.trim();
            window.location.href = 'adminBillManagement?search=' + encodeURIComponent(searchTerm);
        }
        
        function clearSearch() {
            window.location.href = 'adminBillManagement';
        }
        
        function goToPage(page) {
            let url = 'adminBillManagement?page=' + page;
            const searchTerm = document.getElementById('searchInput').value.trim();
            if (searchTerm) {
                url += '&search=' + encodeURIComponent(searchTerm);
            }
            window.location.href = url;
        }
        
        document.getElementById('searchInput').addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                searchBills();
            }
        });
    </script>
</body>
</html>
    
   