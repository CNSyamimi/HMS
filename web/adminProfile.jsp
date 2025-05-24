<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(to bottom right, #dcd6f7, #a6a6f7);
        }
        .sidebar {
            width: 250px;
            background: #fff;
            padding: 20px;
            border-right: 1px solid #ddd;
        }
        .sidebar h4 {
            margin-bottom: 20px;
        }
        .sidebar a {
            display: block;
            padding: 10px;
            margin: 5px 0;
            color: #333;
            text-decoration: none;
        }
        .sidebar a.active {
            background-color: #e6e6ff;
            font-weight: bold;
        }
        .main {
            flex-grow: 1;
            padding: 40px;
        }
        .profile-box {
            border: 2px solid #4a3aff;
            padding: 20px;
            border-radius: 15px;
            max-width: 400px;
            background-color: white;
        }
        .profile-img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            margin-right: 20px;
        }
        .update-btn {
            background-color: #1565c0;
            color: white;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="adminProfile.jsp" class="active">My Profile</a>
        <a href="studentManagement.jsp">Student</a>
        <a href="wardenManagement.jsp">Warden</a>
        <a href="allocationView.jsp">Allocation</a>
        <a href="billManagement.jsp">Manage Bills</a>
        <a href="maintenanceManagement.jsp">Manage Maintenance</a>
        <a href="visitorManagement.jsp">Visitor Details</a>
        <a href="login.jsp">Log Out</a>
    </div>

    <div class="main">
        <div class="d-flex align-items-center mb-4">
            <img src="profile-avatar.png" alt="Profile Picture" class="profile-img">
            <div>
                <h4>Muhammad Haqeem bin Mushim</h4>
                <p>ADMIN</p>
            </div>
        </div>

        <div class="profile-box mb-3">
            <p><strong>Email:</strong> Haq1716@gmail.com</p>
            <p><strong>Phone No:</strong> 019 2271 8427</p>
        </div>

        <button class="btn update-btn">Update Profile</button>
    </div>
</body>
</html>