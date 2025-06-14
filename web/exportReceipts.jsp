<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Export Receipts</title>
    <link rel="stylesheet" href="styles.css">
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
            <h1>Export Receipts</h1>
            <p>View and Export Student Bill</p>

            <!-- Bill Details Form -->
            <div class="bill-details-form">
                <form action="exportReceipt" method="post">
                    <div class="form-group">
                        <label for="studentId">Student ID</label>
                        <input type="text" id="studentId" name="studentId" value="${student.id}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="billType">Bill Type</label>
                        <input type="text" id="billType" name="billType" value="${student.billType}" readonly>
                    </div>

                    <button type="submit" class="btn export-btn">Export Receipt</button>
                </form>
            </div>

            <!-- Back Button -->
            <div class="back-btn">
                <a href="billManagement.jsp" class="btn back-btn">Back</a>
            </div>
        </div>
    </div>

    <script src="script.js"></script> <!-- Link to your JavaScript file for additional functionalities -->
</body>
</html>