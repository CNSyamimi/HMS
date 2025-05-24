<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
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
        .profile-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        .profile-title {
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }
        .student-badge {
            background-color: #4a3aff;
            color: white;
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: bold;
        }
        .profile-section {
            margin-bottom: 30px;
        }
        .profile-section-title {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 15px;
            color: #4a3aff;
            border-bottom: 2px solid #e6e6ff;
            padding-bottom: 5px;
        }
        .profile-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }
        .profile-item {
            margin-bottom: 15px;
        }
        .profile-label {
            font-weight: bold;
            color: #555;
            margin-bottom: 5px;
        }
        .profile-value {
            font-size: 16px;
        }
        .status-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 15px;
            margin-top: 20px;
        }
        .status-item {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 8px;
        }
        .status-label {
            font-size: 14px;
            color: #666;
            margin-bottom: 5px;
        }
        .status-value {
            font-weight: bold;
            font-size: 18px;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
    <a href="studentDashboard.jsp">Dashboard</a>
    <a href="studentProfile.jsp" class="active">My Profile</a>
    <a href="studentRoom.jsp">My Room</a>
    <a href="studentBills.jsp">My Bills</a>
    <a href="studentMaintenance.jsp">Maintenance Requests</a>
    <a href="studentVisitors.jsp">Visitor Log</a>
    <a href="login.jsp">Log Out</a>
</div>

<div class="main">
    <div class="card">
        <div class="profile-header">
            <div class="profile-title">My Profile</div>
            <div class="student-badge">STUDENT</div>
        </div>

        <div class="profile-section">
            <div class="profile-grid">
                <div class="profile-item">
                    <div class="profile-label">Name</div>
                    <div class="profile-value">${student.name}</div>
                </div>
                <div class="profile-item">
                    <div class="profile-label">Programme Code</div>
                    <div class="profile-value">${student.programmeCode}</div>
                </div>
                <div class="profile-item">
                    <div class="profile-label">Programme</div>
                    <div class="profile-value">${student.programme}</div>
                </div>
                <div class="profile-item">
                    <div class="profile-label">Matric ID</div>
                    <div class="profile-value">${student.matricId}</div>
                </div>
                <div class="profile-item">
                    <div class="profile-label">Semester</div>
                    <div class="profile-value">${student.semester}</div>
                </div>
                <div class="profile-item">
                    <div class="profile-label">Room Number</div>
                    <div class="profile-value">${student.roomNumber}</div>
                </div>
            </div>
        </div>

        <div class="profile-section">
            <div class="profile-section-title">Student Details</div>
            <div class="status-grid">
                <div class="status-item">
                    <div class="status-label">Student Status</div>
                    <div class="status-value">${student.status}</div>
                </div>
                <div class="status-item">
                    <div class="status-label">Resident</div>
                    <div class="status-value">${student.residentStatus}</div>
                </div>
                <div class="status-item">
                    <div class="status-label">Pending Requests</div>
                    <div class="status-value">${student.pendingRequests}</div>
                </div>
                <div class="status-item">
                    <div class="status-label">Visitor Visits</div>
                    <div class="status-value">${student.visitorVisits}</div>
                </div>
                <div class="status-item">
                    <div class="status-label">Paid Bills</div>
                    <div class="status-value">${student.paidBills}</div>
                </div>
                <div class="status-item">
                    <div class="status-label">Unpaid Bills</div>
                    <div class="status-value">${student.unpaidBills}</div>
                </div>
                <div class="status-item">
                    <div class="status-label">Maintenance Requests</div>
                    <div class="status-value">${student.maintenanceRequests}</div>
                </div>
                <div class="status-item">
                    <div class="status-label">Room Number</div>
                    <div class="status-value">${student.roomNumber}</div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>