<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bills Management</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <div class="container">
        <!-- Sidebar Navigation -->
        <div class="sidebar">
            <div class="logo">
                <img src="images/logo.png" alt="UITM Logo">
            </div>
            <nav>
                <ul>
        <a href="adminDashboard.jsp" class="active">Dashboard</a>
        <a href="adminProfile.jsp">My Profile</a>
        <a href="studentManagement.jsp">Student</a>
        <a href="wardenManagement.jsp">Warden</a>
        <a href="allocationView.jsp">Allocation</a>
        <a href="billManagement.jsp">Manage Bills</a>
        <a href="maintenanceManagement.jsp">Manage Maintenance</a>
        <a href="login.jsp">Log Out</a>
                </ul>
            </nav>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <h1>Bills Management</h1>
            <p>List of Students Bill</p>

            <!-- Search Bar -->
            <div class="search-bar">
                <input type="text" id="search" placeholder="Search by Student Name or Matric No">
            </div>

            <!-- Bills Table -->
            <table class="bills-table">
                <thead>
                    <tr>
                        <th>Room</th>
                        <th>Student Name</th>
                        <th>Matric No</th>
                        <th>Phone No</th>
                        <th>Email</th>
                        <th>Bill Type</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bill" items="${billList}">
                        <tr>
                            <td>${bill.room}</td>
                            <td>${bill.studentName}</td>
                            <td>${bill.matricNo}</td>
                            <td>${bill.phoneNo}</td>
                            <td>${bill.email}</td>
                            <td>${bill.billType}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${bill.status == 'Paid'}">
                                        <span class="status paid">Paid</span>
                                    </c:when>
                                    <c:when test="${bill.status == 'Unpaid'}">
                                        <span class="status unpaid">Unpaid</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status">-</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Receipt Export Button -->
            <div class="export-section">
                <a href="exportReceipts.jsp" class="btn export-btn">Receipt Export</a>
            </div>
        </div>
    </div>

    <script src="script.js"></script> <!-- Link to your JavaScript file for search and interactivity -->
</body>
</html>