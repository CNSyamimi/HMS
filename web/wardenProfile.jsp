<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    <style>
        body {
            display: flex;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to bottom right, #dcd6f7, #a6a6f7);
            min-height: 100vh;
        }
        .sidebar {
            width: 250px;
            background: #fff;
            padding: 20px;
            border-right: 1px solid #ddd;
        }
        .sidebar a {
            display: block;
            padding: 10px;
            color: #333;
            text-decoration: none;
            margin-bottom: 5px;
        }
        .sidebar a.active {
            background-color: #e6e6ff;
            font-weight: bold;
        }
        .main {
            flex-grow: 1;
            padding: 40px;
            color: #333;
        }
        .profile-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .profile-info {
            display: flex;
            align-items: center;
        }
        .profile-pic {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            border: 5px solid #dcd6f7;
            margin-right: 20px;
            object-fit: cover;
        }
        .info-card {
            background: white;
            border-radius: 20px;
            padding: 20px;
            margin: 20px 0;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            width: fit-content;
        }
        .button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
        }
        .stats {
            display: flex;
            gap: 40px;
            margin-top: 40px;
        }
        .stat-box {
            background: white;
            border-radius: 16px;
            padding: 20px;
            text-align: center;
            width: 150px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .stat-value {
            font-size: 28px;
            font-weight: bold;
            color: red;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
    <a href="wardenDashboard.jsp">Dashboard</a>
    <a href="wardenProfile.jsp" class="active">My Profile</a>
    <a href="blockDetails.jsp">Block</a>
    <a href="wardenStudentView.jsp">Student</a>
    <a href="wardenBillView.jsp">Manage Bills</a>
    <a href="WardenMaintenance.jsp">Manage Maintenance</a>
    <a href="login.jsp">Log Out</a>
</div>

<div class="main">
    <div class="profile-header">
        <div class="profile-info">
            <img src="images/warden-profile.png" alt="Warden Profile" class="profile-pic" />
            <div>
                <h2>WARDEN</h2>
                <h3>Ahmad Ali Bin Mustafa</h3>
                <p>W2035667</p>
            </div>
        </div>
        <button class="button">Update Profile</button>
    </div>

    <div class="info-card">
        <p><strong>Email:</strong> Ahmad9@gmail.com</p>
        <p><strong>Phone No:</strong> 012 4594 3324</p>
        <p><strong>Assigned Block:</strong> Block A</p>
    </div>

    <div class="stats">
        <div class="stat-box">
            <p>Number of Rooms</p>
            <div class="stat-value">584</div>
        </div>
        <div class="stat-box">
            <p>Number of Students</p>
            <div class="stat-value">1000</div>
        </div>
    </div>
</div>

</body>
</html>