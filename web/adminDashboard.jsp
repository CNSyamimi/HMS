<%-- src/main/webapp/adminDashboard.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Hostel Management</title>
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f2f5; /* Light grey background */
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
        }
        .sidebar {
            width: 250px;
            background-color: #2c3e50; /* Dark blue-grey */
            color: white;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
            min-height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
        }
        .sidebar h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #ecf0f1; /* Lighter grey */
            font-size: 1.5em;
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
        }
        .sidebar ul li {
            margin-bottom: 15px;
        }
        .sidebar ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            display: block;
            border-radius: 8px; /* Rounded corners */
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .sidebar ul li a:hover {
            background-color: #34495e; /* Slightly lighter dark blue-grey */
            transform: translateX(5px);
        }
        .sidebar ul li a.active {
            background-color: #007bff;
            font-weight: bold;
        }
        .main-content {
            margin-left: 270px; /* Space for sidebar */
            padding: 30px;
            flex-grow: 1;
            width: calc(100% - 270px);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            background-color: #ffffff;
            padding: 20px 30px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
        }
        .header h1 {
            margin: 0;
            color: #2c3e50;
            font-size: 2em;
        }
        .user-info {
            font-weight: bold;
            color: #555;
        }
        .user-info span {
            color: #007bff;
        }
        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 25px;
            margin-top: 30px;
        }
        .card {
            background-color: #ffffff;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: 1px solid #e0e0e0;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.12);
        }
        .card h3 {
            color: #34495e;
            margin-top: 0;
            font-size: 1.4em;
        }
        .card p {
            color: #666;
            line-height: 1.6;
        }
        .card .btn {
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
            margin-top: 15px;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .card .btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }
        .logout-btn {
            display: block;
            width: calc(100% - 30px); /* Adjust for padding */
            background-color: #e74c3c; /* Red for logout */
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1em;
            text-align: center;
            text-decoration: none;
            margin: 30px 15px 0;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .logout-btn:hover {
            background-color: #c0392b;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <nav>
            <ul>
                <li><a href="adminDashboard" class="active">Dashboard</a></li>
                <li><a href="adminProfile" class="">Profile</a></li> <%-- Link to the view profile page --%>
                <li><a href="adminStudentManagement">Manage Students</a></li>
                <li><a href="adminWardenManagement">Manage Wardens</a></li>
                <li><a href="adminBillManagement">Manage Bills</a></li>
                <li><a href="adminMaintenanceManagement">Manage Maintenance</a></li>
                <li><a href="adminAllocationManagement" >Manage Allocations</a></li>
            </ul>
        </nav>
        <a href="adminLogout" class="logout-btn">Logout</a>
    </div>

    <div class="main-content">
        <div class="header">
            <h1>Dashboard</h1>
            <div class="user-info">
                Welcome, <span>${sessionScope.currentAdmin.adminName}</span>!
            </div>
        </div>

        <div class="card-container">
            <div class="card">
                <h3>Student Management</h3>
                <p>Add, view, edit, and delete student records.</p>
                <a href="adminStudentManagement" class="btn">Go to Students</a>
            </div>
            <div class="card">
                <h3>Warden Management</h3>
                <p>Handle warden accounts and their block assignments.</p>
                <a href="adminWardenManagement" class="btn">Go to Wardens</a>
            </div>
            <div class="card">
                <h3>Bill Management</h3>
                <p>Oversee student bills, payments, and dues.</p>
                <a href="adminBillManagement" class="btn">Go to Bills</a>
            </div>
            <div class="card">
                <h3>Maintenance Requests</h3>
                <p>Track and manage maintenance requests from students.</p>
                <a href="adminMaintenanceManagement" class="btn">Go to Maintenance</a>
            </div>
            <div class="card">
                <h3>Your Profile</h3>
                <p>View and update your administrator profile.</p>
                <a href="adminProfile" class="btn">Go to Profile</a>
            </div>
        </div>
    </div>
</body>
</html>