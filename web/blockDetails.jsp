<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Block Details</title>
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
        h2 {
            margin-top: 0;
            color: #4a3aff;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        th, td {
            padding: 12px 14px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        th {
            background-color: #f5f5ff;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
    <a href="wardenDashboard.jsp">Dashboard</a>
    <a href="wardenProfile.jsp">My Profile</a>
    <a href="blockDetails.jsp" class="active">Block</a>
    <a href="wardenStudentView.jsp">Student</a>
    <a href="wardenBillView.jsp">Manage Bills</a>
    <a href="WardenMaintenance.jsp">Manage Maintenance</a>
    <a href="login.jsp">Log Out</a>
</div>

<div class="main">
    <div class="card">
        <h2>List of Block Residents: Block B</h2>
        <table>
            <tr>
                <th>Room No</th>
                <th>Student Name</th>
                <th>Matric No</th>
                <th>Phone No</th>
                <th>Email</th>
            </tr>
            <tr>
                <td>B-138</td>
                <td>Siti Aliya Binti Ahmad</td>
                <td>2024298762</td>
                <td>014 6783 9921</td>
                <td>AliyaAhmad@gmail.com</td>
            </tr>
            <tr>
                <td>B-104</td>
                <td>Zulaikha binti Mahmud</td>
                <td>2024346878</td>
                <td>017 4794 9279</td>
                <td>Zula11@gmail.com</td>
            </tr>
            <tr>
                <td>B-127</td>
                <td>Aliya Azhira binti Azam</td>
                <td>2025979362</td>
                <td>011 3884 2806</td>
                <td>Azhira28@gmail.com</td>
            </tr>
            <tr>
                <td>B-134</td>
                <td>Siti Sarah Binti Mohd Fuad</td>
                <td>2023298214</td>
                <td>011 5719 2871</td>
                <td>SarahFuad@gmail.com</td>
            </tr>
        </table>
    </div>

    <div class="card">
        <h2>Resident’s Visitor Details: Block B</h2>
        <table>
            <tr>
                <th>Matric No</th>
                <th>Student Name</th>
                <th>Room No</th>
                <th>Visitor Name</th>
                <th>Purpose</th>
                <th>Visit Date</th>
            </tr>
            <tr>
                <td>2023298218</td>
                <td>Aminah Binti Faizal</td>
                <td>B-230</td>
                <td>Faizal Bin Muhammad</td>
                <td>Give food</td>
                <td>25/04/2025</td>
            </tr>
            <tr>
                <td>2024298762</td>
                <td>Siti Aliya Binti Ahmad</td>
                <td>B-138</td>
                <td>Ahmad Bin Jofli</td>
                <td>Visit</td>
                <td>29/04/2025</td>
            </tr>
            <tr>
                <td>2023206540</td>
                <td>Farhana Binti Yusoff</td>
                <td>B-230</td>
                <td>Yusoff Bin Hakim</td>
                <td>Outing</td>
                <td>08/05/2025</td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>