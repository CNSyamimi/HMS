<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
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
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            text-align: left;
            padding: 12px;
            border-bottom: 1px solid #eee;
        }
        th {
            background-color: #f4f4ff;
        }
        .action-links a {
            margin-right: 10px;
            text-decoration: none;
            font-weight: bold;
        }
        .delete {
            color: red;
        }
        .update {
            color: #007bff;
        }
    </style>
</head>
<body>

    <!-- Sidebar -->
    <div class="sidebar">
        <img src="logo-uitm.png" alt="UiTM Logo" style="width: 100px;">
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="adminProfile.jsp">My Profile</a>
        <a href="studentManagement.jsp" class="active">Student</a>
        <a href="wardenManagement.jsp">Warden</a>
        <a href="allocationView.jsp">Allocation</a>
        <a href="billManagement.jsp">Manage Bills</a>
        <a href="maintenanceManagement.jsp">Manage Maintenance</a>
        <a href="visitorManagement.jsp">Visitor Details</a>
        <a href="login.jsp">Log Out</a>
    </div>

    <!-- Main content -->
    <div class="main">
        <div class="card">
            <h2>Student Room Allocation</h2>
            <table>
                <thead>
                    <tr>
                        <th>Matric No</th>
                        <th>Student Name</th>
                        <th>Programme Code</th>
                        <th>Room No</th>
                        <th>College Block</th>
                        <th>Status</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>2023298214</td>
                        <td>Mohd Aiman Bin Faiz</td>
                        <td>CDCS264</td>
                        <td>A-230</td>
                        <td>A</td>
                        <td>ACTIVE</td>
                        <td class="action-links">
                            <a href="deleteStudent.jsp?id=2023298214" class="delete">DELETE</a>
                            <a href="updateStudent.jsp?id=2023298214" class="update">UPDATE</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2024298762</td>
                        <td>Siti Aliya Binti Ahmad</td>
                        <td>CDCS264</td>
                        <td>B-138</td>
                        <td>B</td>
                        <td>ACTIVE</td>
                        <td class="action-links">
                            <a href="deleteStudent.jsp?id=2024298762" class="delete">DELETE</a>
                            <a href="updateStudent.jsp?id=2024298762" class="update">UPDATE</a>
                        </td>
                    </tr>
                    <tr>
                        <td>2023206547</td>
                        <td>Ahmad Abu Bin Yusof</td>
                        <td>CDCS230</td>
                        <td>A-230</td>
                        <td>A</td>
                        <td>ACTIVE</td>
                        <td class="action-links">
                            <a href="deleteStudent.jsp?id=2023206547" class="delete">DELETE</a>
                            <a href="updateStudent.jsp?id=2023206547" class="update">UPDATE</a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <h2>Visitor Details</h2>
            <table>
                <thead>
                    <tr>
                        <th>Visitor Name</th>
                        <th>Phone No</th>
                        <th>Student Name</th>
                        <th>Room No</th>
                        <th>Visit Date</th>
                        <th>Check-In</th>
                        <th>Check-Out</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Ali Bin Zainal</td>
                        <td>012-3456789</td>
                        <td>Mohd Aiman Bin Faiz</td>
                        <td>A-230</td>
                        <td>2025-05-20</td>
                        <td>14:00</td>
                        <td>16:30</td>
                    </tr>
                    <tr>
                        <td>Farah Binti Rahim</td>
                        <td>011-1234567</td>
                        <td>Siti Aliya Binti Ahmad</td>
                        <td>B-138</td>
                        <td>2025-05-21</td>
                        <td>10:00</td>
                        <td>11:45</td>
                    </tr>
                    <tr>
                        <td>John Lim</td>
                        <td>019-9876543</td>
                        <td>Ahmad Abu Bin Yusof</td>
                        <td>A-230</td>
                        <td>2025-05-22</td>
                        <td>15:30</td>
                        <td>17:00</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>