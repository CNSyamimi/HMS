<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
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
        .info-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }
        .info-item {
            margin-bottom: 10px;
        }
        .info-label {
            font-weight: bold;
            color: #555;
        }
        .info-value {
            margin-top: 5px;
        }
        .status-badge {
            display: inline-block;
            padding: 3px 8px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
        }
        .status-active {
            background-color: #e6f7d6;
            color: #3a7a12;
        }
        .status-pending {
            background-color: #fff3cd;
            color: #856404;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
    <a href="studentDashboard.jsp" class="active">Dashboard</a>
    <a href="studentProfile.jsp">My Profile</a>
    <a href="studentRoom.jsp">My Room</a>
    <a href="studentBills.jsp">My Bills</a>
    <a href="studentMaintenance.jsp">Maintenance Requests</a>
    <a href="studentVisitors.jsp">Visitor Log</a>
    <a href="login.jsp">Log Out</a>
</div>

<div class="main">
    <h1>Student Dashboard</h1>
    <p><%= new java.text.SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new java.util.Date()) %></p>

    <div class="card">
        <h2>Overview</h2>
        <div class="overview-stat">
            <div>
                <div>Semester Period</div>
                <div class="stat-value">${semesterPeriod}</div>
            </div>
            <div>
                <div>Status</div>
                <div class="stat-value">
                    <span class="status-badge status-active">${status}</span>
                </div>
            </div>
            <div>
                <div>Allocation</div>
                <div class="stat-value">${allocation}</div>
            </div>
        </div>
    </div>

    <div class="card">
        <h2>Room Details</h2>
        <div class="info-grid">
            <div class="info-item">
                <div class="info-label">Room Number</div>
                <div class="info-value">${roomNumber}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Room Type</div>
                <div class="info-value">${roomType}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Roommates</div>
                <div class="info-value">${roommateCount} (${currentRoommates}/${roomCapacity})</div>
            </div>
            <div class="info-item">
                <div class="info-label">Block</div>
                <div class="info-value">${blockName}</div>
            </div>
        </div>
    </div>

    <div class="card">
        <h2>Student Status</h2>
        <div class="info-grid">
            <div class="info-item">
                <div class="info-label">Student Status</div>
                <div class="info-value">${studentStatus}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Resident</div>
                <div class="info-value">${residentStatus}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Pending Requests</div>
                <div class="info-value">${pendingRequests}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Visitor Visits</div>
                <div class="info-value">${visitorVisits}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Paid Bills</div>
                <div class="info-value">${paidBills}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Unpaid Bills</div>
                <div class="info-value">${unpaidBills}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Maintenance Requests</div>
                <div class="info-value">${maintenanceRequests}</div>
            </div>
        </div>
    </div>

    <div class="card">
        <h2>Block Information</h2>
        <div class="info-grid">
            <div class="info-item">
                <div class="info-label">Block Name</div>
                <div class="info-value">${blockName}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Rooms Allocated</div>
                <div class="info-value">${roomsAllocated}/${totalRooms}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Rooms Available</div>
                <div class="info-value">${roomsAvailable}/${totalRooms}</div>
            </div>
            <div class="info-item">
                <div class="info-label">Warden</div>
                <div class="info-value">${wardenName}</div>
            </div>
        </div>
    </div>
</div>

</body>
</html>