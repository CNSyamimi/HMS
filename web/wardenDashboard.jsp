<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Warden Dashboard</title>
    <link rel="stylesheet" type="text/css" href="warden.css">
</head>
<body>

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

    <div class="main-content">
        <h1 style="color: #3373ff;">DASHBOARD</h1>
        <p style="margin-bottom: 30px;"><%= new java.text.SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new java.util.Date()) %></p>

        <!-- Overview Section -->
        <div class="card">
            <h2>Overview</h2>
            <div style="display: flex; flex-wrap: wrap; gap: 30px;">
                <div>
                    <strong>Current Semester period</strong><br>
                    <span class="stat">20252</span>
                </div>
                <div>
                    <strong>Total Student allocated</strong><br>
                    <span class="stat">1000</span>
                </div>
                <div>
                    <strong>Total Available room</strong><br>
                    <span class="stat">21</span>
                </div>
                <div>
                    <strong>Total Occupied room</strong><br>
                    <span class="stat">560</span>
                </div>
            </div>
        </div>

        <!-- Maintenance & Room Status -->
        <div style="display: flex; gap: 20px; margin-top: 20px;">
            <div class="card" style="flex: 1;">
                <strong>Total Maintenance Request Assigned:</strong>
                <p style="margin-top: 10px;">
                    <a href="#" style="color: #3373ff; text-decoration: none;">12 Maintenance</a>
                </p>
            </div>

            <div class="card" style="flex: 2;">
                <strong>Room status</strong>
                <div style="display: flex; justify-content: space-between; margin-top: 10px;">
                    <div>
                        <p>Occupied rooms: <strong>560</strong></p>
                        <p>Clean: 90</p>
                        <p>Dirty: 4</p>
                        <p>Inspected: 60</p>
                    </div>
                    <div>
                        <p>Available rooms: <strong>21</strong></p>
                        <p>Clean: 17</p>
                        <p>Dirty: 2</p>
                        <p>Inspected: 3</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Quick Navigation Cards -->
        <div style="display: flex; justify-content: space-between; margin-top: 30px;">
            <div class="card" style="text-align: center; width: 30%;">
                <h3>My Profile</h3>
                <img src="images/profile-icon.png" alt="Profile Icon" width="80">
                <br><br>
                <button class="btn-primary">Go</button>
            </div>

            <div class="card" style="text-align: center; width: 30%;">
                <h3>Block</h3>
                <img src="images/building-icon.png" alt="Block Icon" width="80">
                <br><br>
                <button class="btn-primary">Go</button>
            </div>

            <div class="card" style="text-align: center; width: 30%;">
                <h3>Manage Bills</h3>
                <img src="images/bill-icon.png" alt="Bill Icon" width="80">
                <br><br>
                <button class="btn-primary">Go</button>
            </div>
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