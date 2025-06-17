<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - Warden</title>
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
        <h1 style="color: #3373ff;">My Profile</h1>

        <!-- Warden Profile Section -->
        <div style="display: flex; align-items: center; gap: 30px;">
            <img src="images/Warden.png" alt="Warden" style="border-radius: 50%; border: 4px solid red; width: 120px; height: 120px; object-fit: cover;">
            <div>
                <h2>WARDEN</h2>
                <h3>Ahmad Ali Bin Mustafa</h3>
                <p>ID: W2035667</p>
            </div>
            <div style="margin-left: auto;">
                <button class="btn-primary">Update Profile</button>
            </div>
        </div>

        <!-- Contact Information Card -->
        <div class="card" style="margin-top: 30px;">
            <p><strong>Email:</strong> Ahmad9@gmail.com</p>
            <p><strong>Phone No:</strong> 012 4594 3324</p>
            <p><strong>Assigned Block:</strong> Block A</p>
        </div>

        <!-- Stats Section -->
        <div style="display: flex; gap: 40px; margin-top: 30px;">
            <div class="card" style="text-align: center; width: 180px;">
                <h3>Number of Rooms</h3>
                <p style="font-size: 28px; font-weight: bold; color: red;">584</p>
            </div>

            <div class="card" style="text-align: center; width: 180px;">
                <h3>Number of Student</h3>
                <p style="font-size: 28px; font-weight: bold; color: red;">1000</p>
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