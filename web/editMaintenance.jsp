<%-- 
    Document   : editMaintenance
    Created on : 21 Jun 2025, 2:22:03 pm
    Author     : USER
--%>

<%-- src/main/webapp/editMaintenance.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Maintenance Request - Admin Panel</title>
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
        .form-group input[type="date"],
        .form-group select,
        .form-group textarea {
            width: calc(100% - 22px);
            padding: 12px;
            border: 1px solid #b0bec5;
            border-radius: 8px;
            font-size: 1em;
            transition: border-color 0.3s ease;
        }
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            border-color: #007bff;
            outline: none;
        }
        textarea {
            resize: vertical;
            min-height: 80px;
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
                <li><a href="adminStudentManagement">Manage Students</a></li>
                <li><a href="adminWardenManagement">Manage Wardens</a></li>
                <li><a href="adminBillManagement">Manage Bills</a></li>
                <li><a href="adminMaintenanceManagement" class="active">Manage Maintenance</a></li>
                <li><a href="adminAllocationManagement" >Manage Allocations</a></li>
            </ul>
        </nav>
        <a href="adminLogout" class="logout-btn">Logout</a>
    </div>

    <div class="main-content">
        <div class="header">
            <h1>Edit Maintenance Request</h1>
        </div>

        <c:if test="${not empty requestScope.errorMessage}">
            <p class="message error-message">${requestScope.errorMessage}</p>
        </c:if>

        <div class="form-container">
            <c:if test="${not empty requestScope.maintenance}">
                <form action="adminMaintenanceManagement" method="post">
                    <input type="hidden" name="action" value="updateStatus">
                    <input type="hidden" name="requestID" value="${requestScope.maintenance.requestID}">

                    <div class="form-group">
                        <label for="requestID">Request ID:</label>
                        <input type="text" id="requestID" name="requestID" value="${requestScope.maintenance.requestID}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="request_date">Request Date:</label>
                        <fmt:formatDate value="${requestScope.maintenance.request_date}" pattern="yyyy-MM-dd" var="formattedRequestDate" />
                        <input type="date" id="request_date" name="request_date" value="${formattedRequestDate}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="issueType">Issue Type:</label>
                        <input type="text" id="issueType" name="issueType" value="${requestScope.maintenance.issue_type}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" readonly>${requestScope.maintenance.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="studentID">Student ID:</label>
                        <input type="text" id="studentID" name="studentID" value="${requestScope.maintenance.studentID != null ? requestScope.maintenance.studentID : 'N/A'}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="roomID">Room ID:</label>
                        <input type="text" id="roomID" name="roomID" value="${requestScope.maintenance.roomID}" readonly>
                    </div>
                     <div class="form-group">
                        <label for="blockID">Block ID:</label>
                        <input type="text" id="blockID" name="blockID" value="${requestScope.maintenance.blockID}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="wardenID">Assigned Warden ID:</label>
                        <input type="text" id="wardenID" name="wardenID" value="${requestScope.maintenance.wardenID != null ? requestScope.maintenance.wardenID : ''}">
                    </div>
                    <div class="form-group">
                        <label for="status">Status:</label>
                        <select id="status" name="status" required>
                            <option value="Pending" ${requestScope.maintenance.status == 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="In Progress" ${requestScope.maintenance.status == 'In Progress' ? 'selected' : ''}>In Progress</option>
                            <option value="Completed" ${requestScope.maintenance.status == 'Completed' ? 'selected' : ''}>Completed</option>
                            <option value="Rejected" ${requestScope.maintenance.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="resolutionDate">Resolution Date (Optional):</label>
                        <fmt:formatDate value="${requestScope.maintenance.resolution_date}" pattern="yyyy-MM-dd" var="formattedResolutionDate" />
                        <input type="date" id="resolutionDate" name="resolutionDate" value="${formattedResolutionDate}">
                    </div>
                    <button type="submit">Update Request</button>
                </form>
            </c:if>
            <c:if test="${empty requestScope.maintenance}">
                <p>Maintenance request not found or invalid ID provided.</p>
            </c:if>
            <a href="adminMaintenanceManagement" class="back-link">Back to Maintenance Requests</a>
        </div>
    </div>
</body>
</html>