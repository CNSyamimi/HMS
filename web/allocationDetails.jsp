<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Allocation Details</title>
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
            padding: 30px;
            margin-bottom: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }
        .section {
            margin-bottom: 30px;
        }
        .section-title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 15px;
            color: #4a3aff;
            border-bottom: 2px solid #e6e6ff;
            padding-bottom: 5px;
        }
        .overview-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }
        .overview-item {
            margin-bottom: 15px;
        }
        .overview-label {
            font-weight: bold;
            color: #555;
            margin-bottom: 5px;
        }
        .overview-value {
            font-size: 16px;
        }
        .status-badge {
            display: inline-block;
            padding: 5px 10px;
            border-radius: 20px;
            font-weight: bold;
            background-color: #e6f7d6;
            color: #3a7a12;
        }
        .diagram-box {
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
        }
        .diagram-title {
            font-weight: bold;
            margin-bottom: 10px;
            color: #4a3aff;
        }
        .date-display {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .date-value {
            font-size: 18px;
            font-weight: bold;
            margin-right: 15px;
        }
        .days-remaining {
            font-size: 24px;
            font-weight: bold;
            color: #4a3aff;
            text-align: center;
            margin: 20px 0;
        }
        .progress-bar {
            height: 8px;
            background-color: #e6e6ff;
            border-radius: 4px;
            margin-top: 5px;
            overflow: hidden;
        }
        .progress-fill {
            height: 100%;
            background-color: #4a3aff;
            width: 75%; /* Example value - should be dynamic */
        }
    </style>
</head>
<body>

<div class="sidebar">
    <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
    <a href="studentDashboard.jsp">Dashboard</a>
    <a href="studentProfile.jsp">My Profile</a>
    <a href="allocationDetails.jsp" class="active">Allocation</a>
    <a href="studentRoom.jsp">Room</a>
    <a href="studentBills.jsp">Manage Bills</a>
    <a href="studentVisitors.jsp">Visitor</a>
    <a href="studentMaintenance.jsp">Manage Maintenance</a>
    <a href="login.jsp">Log Out</a>
</div>

<div class="main">
    <div class="header">
        <div class="title">Allocation Details</div>
    </div>

    <div class="card">
        <div class="section">
            <div class="section-title">Overview</div>
            <div class="overview-grid">
                <div class="overview-item">
                    <div class="overview-label">Current Semester</div>
                    <div class="overview-value">${allocation.semesterPeriod}</div>
                </div>
                <div class="overview-item">
                    <div class="overview-label">Status</div>
                    <div class="overview-value"><span class="status-badge">${allocation.status}</span></div>
                </div>
                <div class="overview-item">
                    <div class="overview-label">Room Number</div>
                    <div class="overview-value">${allocation.roomNumber}</div>
                </div>
                <div class="overview-item">
                    <div class="overview-label">Roommates</div>
                    <div class="overview-value">${allocation.roommateCount} (${allocation.currentRoommates}/${allocation.roomCapacity})</div>
                    <div class="progress-bar">
                        <div class="progress-fill"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="section">
            <div class="section-title">Block Information</div>
            <div class="diagram-box">
                <div class="diagram-title">Block ${allocation.blockName}</div>
                <div class="overview-value">${allocation.roomsAllocated}/${allocation.totalRooms} rooms allocated</div>
                <div class="overview-value">${allocation.roomsAvailable}/${allocation.totalRooms} rooms available</div>
                <div class="overview-value">SRK (Warden): ${allocation.wardenName}</div>
            </div>
        </div>

        <div class="section">
            <div class="section-title">Residence Period</div>
            <div class="diagram-box">
                <div class="date-display">
                    <div class="overview-label">Check In Date:</div>
                    <div class="date-value">${allocation.checkInDate}</div>
                </div>
                <div class="date-display">
                    <div class="overview-label">Check Out Date:</div>
                    <div class="date-value">${allocation.checkOutDate}</div>
                </div>
                <div class="days-remaining">
                    ${allocation.daysRemaining} Days Remaining
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>