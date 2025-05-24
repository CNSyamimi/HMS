<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Warden Dashboard</title>
    <style>
        body {
            display: flex;
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(to bottom right, #dcd6f7, #a6a6f7);
            font-family: 'Segoe UI', sans-serif;
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
        }
        .card {
            background: white;
            border-radius: 12px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .overview-stat {
            display: flex;
            justify-content: space-around;
            font-size: 16px;
        }
        .overview-stat div {
            text-align: center;
        }
        .stat-value {
            color: #4a3aff;
            font-weight: bold;
            font-size: 20px;
        }
        .feedback-item {
            border-top: 1px solid #eee;
            padding: 10px 0;
        }
        .feedback-item:first-child {
            border-top: none;
        }
        .feedback-name {
            font-weight: bold;
        }
        .feedback-room {
            float: right;
            font-size: 14px;
            color: #999;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
    <a href="wardenDashboard.jsp" class="active">Dashboard</a>
    <a href="wardenProfile.jsp">My Profile</a>
    <a href="blockDetails.jsp">Block</a>
    <a href="wardenStudentView.jsp">Student</a>
    <a href="wardenBillView.jsp">Manage Bills</a>
    <a href="WardenMaintenance.jsp">Manage Maintenance</a>
    <a href="login.jsp">Log Out</a>
</div>

<div class="main">
    <h1>Warden Dashboard</h1>
    <p><%= new java.text.SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new java.util.Date()) %></p>

    <div class="card">
        <h2>Overview</h2>
        <div class="overview-stat">
            <div>
                <div>Semester</div>
                <div class="stat-value">${semesterPeriod}</div>
            </div>
            <div>
                <div>Students</div>
                <div class="stat-value">${totalStudents}</div>
            </div>
            <div>
                <div>Available Rooms</div>
                <div class="stat-value">${availableRooms}</div>
            </div>
            <div>
                <div>Occupied Rooms</div>
                <div class="stat-value">${occupiedRooms}</div>
            </div>
        </div>
    </div>

    <div class="card">
        <h2>Total Maintenance Requests</h2>
        <div class="stat-value"><a href="maintenance.jsp" style="color:#4a3aff;">${totalMaintenance} Maintenance</a></div>
    </div>

    <div class="card">
        <h2>Room Status</h2>
        <p><strong>Occupied Rooms (${occupiedRooms}):</strong></p>
        <ul>
            <li>Clean: ${occupiedClean}</li>
            <li>Dirty: ${occupiedDirty}</li>
            <li>Inspected: ${occupiedInspected}</li>
        </ul>
        <p><strong>Available Rooms (${availableRooms}):</strong></p>
        <ul>
            <li>Clean: ${availableClean}</li>
            <li>Dirty: ${availableDirty}</li>
            <li>Inspected: ${availableInspected}</li>
        </ul>
    </div>

    <div class="card">
        <h2>Navigation</h2>
        <a href="myProfile.jsp">Go to My Profile</a><br/>
        <a href="block.jsp">Go to Block</a><br/>
        <a href="manageBills.jsp">Go to Manage Bills</a>
    </div>
</div>

</body>
</html>