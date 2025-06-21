<%-- 
    Document   : adminAllocationList
    Created on : 21 Jun 2025, 5:07:39 pm
    Author     : USER
--%>

<%-- src/main/webapp/adminAllocationList.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Allocations - Admin Panel</title>
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
            overflow-x: auto;
            border: 1px solid #e0e0e0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table th, table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        table th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #555;
            text-transform: uppercase;
            font-size: 0.9em;
        }
        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        table tr:hover {
            background-color: #e9ecef;
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
                <li><a href="adminBillManagement">Manage Bills</a></li>
                <li><a href="adminMaintenanceManagement">Manage Maintenance</a></li>
                <li><a href="adminAllocationManagement" class="active">Manage Allocations</a></li>
            </ul>
        </nav>
        <a href="adminLogout" class="logout-btn">Logout</a>
    </div>

    <div class="main-content">
        <div class="header">
            <h1>Manage Allocations</h1>
            <div class="action-buttons">
                <a href="adminAllocationManagement?action=add" class="btn btn-success">Add New Allocation</a>
            </div>
        </div>

        <c:if test="${not empty requestScope.message}">
            <p class="message-container success-message">${requestScope.message}</p>
        </c:if>
        <c:if test="${not empty requestScope.errorMessage}">
            <p class="message-container error-message">${requestScope.errorMessage}</p>
        </c:if>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Allocation ID</th>
                        <th>Date From</th>
                        <th>Date To</th>
                        <th>Status</th>
                        <th>Student ID</th>
                        <th>Room ID</th>
                        <th>Block ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="allocation" items="${requestScope.allocationList}">
                        <tr>
                            <td>${allocation.allocationID}</td>
                            <td><fmt:formatDate value="${allocation.date_from}" pattern="yyyy-MM-dd" /></td>
                            <td><fmt:formatDate value="${allocation.date_to}" pattern="yyyy-MM-dd" /></td>
                            <td>${allocation.status}</td>
                            <td>${allocation.studentID != null ? allocation.studentID : 'N/A'}</td>
                            <td>${allocation.roomID}</td>
                            <td>${allocation.blockID}</td>
                            <td>
                                <a href="adminAllocationManagement?action=edit&allocationID=${allocation.allocationID}" class="btn btn-warning">Edit</a>
                                <a href="adminAllocationManagement?action=delete&allocationID=${allocation.allocationID}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete allocation ${allocation.allocationID}?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty requestScope.allocationList}">
                        <tr>
                            <td colspan="8">No allocations found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>