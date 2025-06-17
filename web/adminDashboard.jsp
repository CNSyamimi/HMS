<%@page import="hms_controller.AdminDashboardServlet"%>
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
        <img src="${pageContext.request.contextPath}/images/logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
        <a href="adminDashboard.jsp" class="active">Dashboard</a>
        <a href="adminProfile.jsp">My Profile</a>
        <a href="studentManagement.jsp">Student</a>
        <a href="wardenManagement.jsp">Warden</a>
        <a href="allocationView.jsp">Allocation</a>
        <a href="billManagement.jsp">Manage Bills</a>
        <a href="maintenanceManagement.jsp">Manage Maintenance</a>
        <a href="visitorManagement.jsp">Visitor Details</a>
        <a href="login.jsp">Log Out</a>
    </div>

    <div class="main">
        <div class="card">   
            <h5>Overview</h5>
            <div class="overview-stat mt-3">
                <div>
                    <div class="stat-value"><%= AdminDashboardServlet.getTotalStudents() %></div>
                    <div>student in college</div>
                </div>
                <div>
                    <div class="stat-value"><%= AdminDashboardServlet.getTotalWardens() %></div>
                    <div>Wardens</div>
                </div>
                <div>
                    <div class="stat-value"><%= AdminDashboardServlet.getTotalMaintenanceRequests() %></div>
                    <div>Maintenance request</div>
                </div>
                <div>
                    <div class="stat-value"><%= AdminDashboardServlet.getTotalRooms() %></div>
                    <div>Total rooms</div>
                </div>
                <div>
                    <div class="stat-value"><%= AdminDashboardServlet.getTotalAllocatedRooms() %></div>
                    <div>Occupied room</div>
                </div>
            </div>
        </div>

        <div class="card">
            <h5>Room status</h5>
            <div class="row mt-3">
                <div class="col-md-6">
                    <p><strong>Occupied rooms:</strong> <%= AdminDashboardServlet.getTotalAllocatedRooms() %></p>
                    <!-- These would need additional methods in the servlet -->
                    <p>Clean: 90</p>
                    <p>Dirty: 4</p>
                    <p>Inspected: 60</p>
                </div>
                <div class="col-md-6">
                    <%
                        int totalRooms = AdminDashboardServlet.getTotalRooms();
                        int allocatedRooms = AdminDashboardServlet.getTotalAllocatedRooms();
                        int availableRooms = totalRooms - allocatedRooms;
                    %>
                    <p><strong>Available rooms:</strong> <%= availableRooms %></p>
                    <!-- These would need additional methods in the servlet -->
                    <p>Clean: 17</p>
                    <p>Dirty: 2</p>
                    <p>Inspected: 3</p>
                </div>
            </div>
        </div>
        <div class="card">
            <h5>Student feedback</h5>
            <div class="feedback-item">
                <div>
                    <span class="feedback-name">Christian</span>
                    <span class="feedback-room">A101</span>
                </div>
                <div>Facilities are not enough for amount paid.</div>
            </div>
            <div class="feedback-item">
                <div>
                    <span class="feedback-name">Alexander</span>
                    <span class="feedback-room">A301</span>
                </div>
                <div>Room cleaning could be better.</div>
            </div>
        </div>
    </div>
</body>
</html>