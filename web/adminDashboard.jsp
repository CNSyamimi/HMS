<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newcss.css">
    <style>
        /* Additional dashboard-specific styles */
        .dashboard-content {
            padding: 20px;
            margin-left: 250px;
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
        .stats-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        .stat-card {
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- Include Header -->
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>
    
    <!-- Include Sidebar -->
    <%@ include file="/WEB-INF/views/includes/sidebar.jsp" %>
    
    <div class="dashboard-content">
        <div class="card">
            <h5>Overview</h5>
            <div class="stats-container mt-3">
                <div class="stat-card">
                    <div class="stat-value">1000</div>
                    <div>Students in college</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value">13</div>
                    <div>Unpaid bills</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value">23</div>
                    <div>Maintenance requests</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value">21</div>
                    <div>Available rooms</div>
                </div>
                <div class="stat-card">
                    <div class="stat-value">560</div>
                    <div>Occupied rooms</div>
                </div>
            </div>
        </div>

        <div class="card">
            <h5>Room status</h5>
            <div class="row mt-3">
                <div class="col-md-6">
                    <p><strong>Occupied rooms:</strong> 560</p>
                    <p>Clean: 90</p>
                    <p>Dirty: 4</p>
                    <p>Inspected: 60</p>
                </div>
                <div class="col-md-6">
                    <p><strong>Available rooms:</strong> 21</p>
                    <p>Clean: 17</p>
                    <p>Dirty: 2</p>
                    <p>Inspected: 3</p>
                </div>
            </div>
        </div>

        <div class="card">
            <h5>Recent Feedback</h5>
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
            <a href="${pageContext.request.contextPath}/feedback" class="btn btn-link">View all feedback</a>
        </div>
    </div>
</body>
</html>