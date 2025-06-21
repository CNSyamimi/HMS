<%-- 
    Document   : addStudent
    Created on : 21 Jun 2025, 5:45:08 am
    Author     : USER
--%>

<%-- src/main/webapp/addStudent.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student - Admin Panel</title>
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
        .form-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
            max-width: 600px;
            margin: 30px auto;
            border: 1px solid #e0e0e0;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #455a64;
        }
        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="email"],
        .form-group input[type="number"],
        .form-group select {
            width: calc(100% - 22px);
            padding: 12px;
            border: 1px solid #b0bec5;
            border-radius: 8px;
            font-size: 1em;
            transition: border-color 0.3s ease;
        }
        .form-group input:focus,
        .form-group select:focus {
            border-color: #007bff;
            outline: none;
        }
        .message {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 8px;
            font-weight: bold;
            text-align: center;
        }
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1.1em;
            width: 100%;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        button:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }
        .back-link {
            display: block;
            margin-top: 20px;
            text-align: center;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .back-link:hover {
            text-decoration: underline;
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
                <li><a href="adminStudentManagement" class="active">Manage Students</a></li>
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
            <h1>Add New Student</h1>
        </div>

        <c:if test="${not empty requestScope.errorMessage}">
            <p class="message error-message">${requestScope.errorMessage}</p>
        </c:if>

        <div class="form-container">
            <form action="adminStudentManagement" method="post">
                <input type="hidden" name="action" value="addUpdate">
                <input type="hidden" name="studentID" value="0"> <%-- Indicate new student --%>

                <div class="form-group">
                    <label for="studentID">Student ID:</label>
                    <input type="text" id="studentID" name="studentID" required>
                </div>
                <div class="form-group">
                    <label for="sName">Name:</label>
                    <input type="text" id="sName" name="sName" required>
                </div>
                <div class="form-group">
                    <label for="sPass">Password:</label>
                    <input type="password" id="sPass" name="sPass" required>
                </div>
                <div class="form-group">
                    <label for="program">Program:</label>
                    <input type="text" id="program" name="program" required>
                </div>
                <div class="form-group">
                    <label for="semester">Semester:</label>
                    <input type="number" id="semester" name="semester" min="1" required>
                </div>
                <div class="form-group">
                    <label for="sPho">Phone Number:</label>
                    <input type="text" id="sPho" name="sPho" required>
                </div>
                <div class="form-group">
                    <label for="sEmail">Email:</label>
                    <input type="email" id="sEmail" name="sEmail" required>
                </div>
                <div class="form-group">
                    <label for="adminID">Admin ID (Managing):</label>
                    <%-- This could be pre-filled with the logged-in admin's ID from session --%>
                    <input type="text" id="adminID" name="adminID" value="${sessionScope.currentAdmin.adminID}" readonly>
                </div>
                <div class="form-group">
                    <label for="merit">Merit:</label>
                    <input type="number" id="merit" name="merit" step="0.01" value="0.0" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender" required>
                        <option value="">Select Gender</option>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </div>
                <button type="submit">Add Student</button>
            </form>
            <a href="adminStudentManagement" class="back-link">Back to Student List</a>
        </div>
    </div>
</body>
</html>