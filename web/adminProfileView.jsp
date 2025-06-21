<%-- RENAMED TO adminProfileView.jsp, providing it here for clarity --%>
<%-- This JSP is for viewing the admin's profile (read-only) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Profile - Hostel Management</title>
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
        }
        .sidebar { /* Replicated for consistency */
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
        .profile-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
            max-width: 700px;
            margin: 30px auto;
            border: 1px solid #e0e0e0;
        }
        .profile-detail {
            margin-bottom: 15px;
        }
        .profile-detail label {
            font-weight: bold;
            color: #455a64;
            display: inline-block;
            width: 120px; /* Align labels */
        }
        .profile-detail span {
            color: #555;
        }
        .action-button-container {
            margin-top: 30px;
            text-align: center;
        }
        .btn {
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1.1em;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }
        .message {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 8px;
            font-weight: bold;
            text-align: center;
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
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <nav>
            <ul>
                <li><a href="adminDashboard">Dashboard</a></li>
                <li><a href="adminProfile" class="active">Profile</a></li>
                <li><a href="adminStudentManagement">Manage Students</a></li>
                <li><a href="adminWardenManagement">Manage Wardens</a></li>
                <li><a href="adminBillManagement">Manage Bills</a></li>
                <li><a href="adminMaintenanceManagement">Manage Maintenance</a></li>
                <li><a href="adminAllocationManagement" >Manage Allocations</a></li>
            </ul>
        </nav>
        <a href="adminLogout" class="logout-btn">Logout</a>
    </div>

    <div class="main-content">
        <div class="header">
            <h1>Admin Profile</h1>
        </div>

        <div class="profile-container">
            <c:if test="${not empty requestScope.message}">
                <p class="message success-message">${requestScope.message}</p>
            </c:if>
            <c:if test="${not empty requestScope.errorMessage}">
                <p class="message error-message">${requestScope.errorMessage}</p>
            </c:if>

            <c:if test="${not empty sessionScope.currentAdmin}">
                <div class="profile-detail">
                    <label>Admin ID:</label>
                    <span>${sessionScope.currentAdmin.adminID}</span>
                </div>
                <div class="profile-detail">
                    <label>Name:</label>
                    <span>${sessionScope.currentAdmin.adminName}</span>
                </div>
                <div class="profile-detail">
                    <label>Phone Number:</label>
                    <span>${sessionScope.currentAdmin.adminPho}</span>
                </div>
                <div class="profile-detail">
                    <label>Email:</label>
                    <span>${sessionScope.currentAdmin.adminEmail}</span>
                </div>

                <div class="action-button-container">
                    <a href="adminProfile?action=edit" class="btn">Update Profile</a>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.currentAdmin}">
                <p class="error-message">Admin data not found. Please log in.</p>
            </c:if>
        </div>
    </div>
</body>
</html>