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
                    <li><a href="dashboard.jsp">Dashboard</a></li>
                    <li><a href="profile.jsp">My Profile</a></li>
                    <li><a href="allocation.jsp">Allocation</a></li>
                    <li><a href="student.jsp">Student</a></li>
                    <li><a href="warden.jsp">Warden</a></li>
                    <li><a href="billManagement.jsp">Manage Bills</a></li>
                    <li><a href="maintenance.jsp">Manage Maintenance</a></li>
                    <li><a href="logout.jsp">Log Out</a></li>
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