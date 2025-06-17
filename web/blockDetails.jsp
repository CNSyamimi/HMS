<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Block Details - Block B Residents</title>
    <link rel="stylesheet" type="text/css" href="warden.css">
</head>
<body>
    <!-- Sidebar Navigation -->
<div class="sidebar">
    <img src="images/UiTM-logo.png" alt="UiTM Logo">

    <ul>
        <li><a href="wardenDashboard.jsp">Dashboard</a></li>
        <li><a href="wardenProfile.jsp">My Profile</a></li>
        <li class="accordion-toggle">Block</li>
        <ul class="accordion-content">
            <li><a href="blockDetails.jsp">› List Block Residents</a></li>
            <li><a href="wardenVisitorView.jsp">› Visitor Details</a></li>
        </ul>
        <li><a href="wardenBillView.jsp">Manage Bills</a></li>
        <li class="accordion-toggle">Manage Maintenance</li>
        <ul class="accordion-content">
            <li><a href="wardenMaintenance.jsp">› List Maintenance</a></li>
            <li><a href="updateMaintenance.jsp">› Update Maintenance</a></li>
        </ul>
        <li><a href="login.jsp">Log Out</a></li>
    </ul>
</div>

    <!-- Main Content -->
    <div class="main-content">
        <h1 style="color: #5a5cd6;">Block Details</h1>
        <h2 style="margin-top: 10px;">List of Block Residents: Block B</h2>

        <!-- Residents Table -->
        <div class="card" style="margin-top: 20px; overflow-x: auto;">
            <table>
                <thead>
                    <tr>
                        <th>Room No</th>
                        <th>Student Name</th>
                        <th>Matric No</th>
                        <th>Phone No</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
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
                        <td>Azhera28@gmail.com</td>
                    </tr>
                    <tr>
                        <td>B-134</td>
                        <td>Siti Sarah Binti Mohd Fuad</td>
                        <td>2023298214</td>
                        <td>011 5719 2871</td>
                        <td>SarahFuad@gmail.com</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Accordion Script -->
<script>
    document.querySelectorAll('.accordion-toggle').forEach(toggle => {
        toggle.addEventListener('click', () => {
            toggle.classList.toggle('active');
            const content = toggle.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    });
</script>
</body>
</html>