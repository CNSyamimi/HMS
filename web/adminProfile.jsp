<%@page import="hms_model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4a3aff;
            --secondary-color: #1565c0;
            --light-bg: #f8f9fa;
            --dark-bg: #343a40;
        }
        
        body {
            display: flex;
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #f5f7fa 0%, #dcd6f7 100%);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .sidebar {
            width: 250px;
            background: white;
            padding: 20px;
            box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
            position: sticky;
            top: 0;
            height: 100vh;
            overflow-y: auto;
        }
        
        .sidebar h4 {
            margin-bottom: 20px;
            color: var(--primary-color);
            font-weight: 600;
        }
        
        .sidebar a {
            display: flex;
            align-items: center;
            padding: 12px 15px;
            margin: 5px 0;
            color: #495057;
            text-decoration: none;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        
        .sidebar a i {
            margin-right: 10px;
            width: 20px;
            text-align: center;
        }
        
        .sidebar a:hover {
            background-color: #e6e6ff;
            color: var(--primary-color);
        }
        
        .sidebar a.active {
            background-color: #e6e6ff;
            color: var(--primary-color);
            font-weight: 500;
        }
        
        .main {
            flex-grow: 1;
            padding: 30px;
            overflow-y: auto;
        }
        
        .profile-header {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 1px solid #eee;
        }
        
        .profile-img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        
        .profile-title {
            margin-left: 20px;
        }
        
        .profile-title h3 {
            margin-bottom: 5px;
            color: var(--dark-bg);
            font-weight: 600;
        }
        
        .profile-title p {
            color: #6c757d;
            margin-bottom: 0;
        }
        
        .profile-box {
            background: white;
            border-radius: 10px;
            padding: 25px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
            margin-bottom: 20px;
        }
        
        .profile-box h5 {
            color: var(--primary-color);
            margin-bottom: 20px;
            font-weight: 600;
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
        }
        
        .profile-info p {
            margin-bottom: 15px;
        }
        
        .profile-info strong {
            color: #495057;
            min-width: 120px;
            display: inline-block;
        }
        
        .update-btn {
            background-color: var(--primary-color);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: 500;
            transition: all 0.3s ease;
        }
        
        .update-btn:hover {
            background-color: var(--secondary-color);
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        
        .form-group label {
            font-weight: 500;
            color: #495057;
        }
        
        .form-control {
            border-radius: 5px;
            padding: 10px 15px;
            border: 1px solid #ced4da;
            transition: all 0.3s ease;
        }
        
        .form-control:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 0.2rem rgba(74, 58, 255, 0.25);
        }
        
        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
        }
        
        .btn-primary:hover {
            background-color: var(--secondary-color);
            border-color: var(--secondary-color);
        }
        
        .edit-form {
            display: none;
            animation: fadeIn 0.3s ease;
        }
        
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }
        
        .password-toggle {
            position: relative;
        }
        
        .password-toggle .toggle-icon {
            position: absolute;
            right: 10px;
            top: 10px;
            cursor: pointer;
            color: #6c757d;
        }
        
        .alert {
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 20px;
        }
        
        @media (max-width: 768px) {
            body {
                flex-direction: column;
            }
            
            .sidebar {
                width: 100%;
                height: auto;
                position: relative;
            }
            
            .main {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="text-center mb-4">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="UiTM Logo" style="width: 80px;">
            <h4 class="mt-3">Hostel Management</h4>
        </div>
        
        <a href="adminDashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
        <a href="adminProfile.jsp" class="active"><i class="fas fa-user"></i> My Profile</a>
        <a href="studentManagement.jsp"><i class="fas fa-users"></i> Student</a>
        <a href="wardenManagement.jsp"><i class="fas fa-user-shield"></i> Warden</a>
        <a href="allocationView.jsp"><i class="fas fa-bed"></i> Allocation</a>
        <a href="billManagement.jsp"><i class="fas fa-file-invoice-dollar"></i> Manage Bills</a>
        <a href="maintenanceManagement.jsp"><i class="fas fa-tools"></i> Maintenance</a>
        <a href="visitorManagement.jsp"><i class="fas fa-address-book"></i> Visitor</a>
        <a href="login.jsp"><i class="fas fa-sign-out-alt"></i> Log Out</a>
    </div>

    <div class="main">
        <%-- Display success/error messages --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger">
                <i class="fas fa-exclamation-circle mr-2"></i> <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        <% if (request.getAttribute("successMessage") != null) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle mr-2"></i> <%= request.getAttribute("successMessage") %>
            </div>
        <% } %>

        <% Admin admin = (Admin) request.getAttribute("admin"); %>
        
        <div class="profile-header">
            <img src="${pageContext.request.contextPath}/images/profile-avatar.png" alt="Profile Picture" class="profile-img">
            <div class="profile-title">
                <h3><%= admin != null ? admin.getAdminName() : "Admin Name" %></h3>
                <p><span class="badge badge-primary">ADMINISTRATOR</span></p>
            </div>
        </div>

        <%-- View Mode --%>
        <div id="viewMode">
            <div class="profile-box">
                <h5><i class="fas fa-id-card mr-2"></i>Profile Information</h5>
                <div class="profile-info">
                    <div class="row">
                        <div class="col-md-6">
                            <p><strong><i class="fas fa-user mr-2"></i>Name:</strong> 
                               <%= admin != null ? admin.getAdminName() : "" %></p>
                        </div>
                        <div class="col-md-6">
                            <p><strong><i class="fas fa-envelope mr-2"></i>Email:</strong> 
                               <%= admin != null ? admin.getAdminEmail() : "" %></p>
                        </div>
                    </div>
                    <% if (admin != null && admin.getAdminPho() != null && !admin.getAdminPho().isEmpty()) { %>
                    <div class="row">
                        <div class="col-md-6">
                            <p><strong><i class="fas fa-phone mr-2"></i>Phone:</strong> 
                               <%= admin.getAdminPho() %></p>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
            
            <button id="editBtn" class="btn update-btn">
                <i class="fas fa-edit mr-2"></i>Edit Profile
            </button>
        </div>

        <%-- Edit Mode (Hidden by default) --%>
        <div id="editMode" class="edit-form">
            <div class="profile-box">
                <h5><i class="fas fa-edit mr-2"></i>Edit Profile</h5>
                <form action="AdminProfileServlet" method="post">
                    <input type="hidden" name="action" value="update">
                    
                    <div class="form-group">
                        <label for="adminName"><i class="fas fa-user mr-2"></i>Name</label>
                        <input type="text" class="form-control" id="adminName" name="adminName" 
                               value="<%= admin != null ? admin.getAdminName() : "" %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="adminPho"><i class="fas fa-phone mr-2"></i>Phone Number</label>
                        <input type="tel" class="form-control" id="adminPho" name="adminPho" 
                               value="<%= admin != null && admin.getAdminPho() != null ? admin.getAdminPho() : "" %>">
                    </div>
                    
                    <div class="form-group">
                        <label for="adminEmail"><i class="fas fa-envelope mr-2"></i>Email</label>
                        <input type="email" class="form-control" id="adminEmail" name="adminEmail" 
                               value="<%= admin != null ? admin.getAdminEmail() : "" %>" required>
                    </div>
                    
                    <hr class="my-4">
                    
                    <h6 class="mb-3"><i class="fas fa-lock mr-2"></i>Change Password</h6>
                    <div class="form-group password-toggle">
                        <label for="currentPassword">Current Password</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword">
                        <i class="fas fa-eye toggle-icon" onclick="togglePassword('currentPassword')"></i>
                    </div>
                    
                    <div class="form-group password-toggle">
                        <label for="newPassword">New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword">
                        <i class="fas fa-eye toggle-icon" onclick="togglePassword('newPassword')"></i>
                    </div>
                    
                    <div class="form-group password-toggle">
                        <label for="confirmPassword">Confirm New Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                        <i class="fas fa-eye toggle-icon" onclick="togglePassword('confirmPassword')"></i>
                    </div>
                    
                    <div class="d-flex justify-content-between mt-4">
                        <button type="button" id="cancelBtn" class="btn btn-secondary">
                            <i class="fas fa-times mr-2"></i>Cancel
                        </button>
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save mr-2"></i>Save Changes
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // Toggle between view and edit modes
        document.getElementById('editBtn').addEventListener('click', function() {
            document.getElementById('viewMode').style.display = 'none';
            document.getElementById('editMode').style.display = 'block';
        });
        
        document.getElementById('cancelBtn').addEventListener('click', function() {
            document.getElementById('editMode').style.display = 'none';
            document.getElementById('viewMode').style.display = 'block';
        });
        
        // Password toggle visibility
        function togglePassword(fieldId) {
            const field = document.getElementById(fieldId);
            const icon = field.nextElementSibling;
            
            if (field.type === 'password') {
                field.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                field.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        }
        
        // Password confirmation validation
        document.querySelector('form').addEventListener('submit', function(e) {
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const currentPassword = document.getElementById('currentPassword').value;
            
            // Only validate if new password is entered
            if (newPassword || confirmPassword) {
                if (newPassword !== confirmPassword) {
                    e.preventDefault();
                    alert('New password and confirmation password do not match!');
                    return false;
                }
                
                if (!currentPassword) {
                    e.preventDefault();
                    alert('Please enter your current password to change your password.');
                    return false;
                }
            }
            
            return true;
        });
    </script>
</body>
</html>