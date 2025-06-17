<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4a3aff;
            --secondary-color: #1565c0;
        }
        
        body {
            background: linear-gradient(to bottom right, #f5f7fa, #e4e8f0);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .sidebar {
            width: 250px;
            background: white;
            box-shadow: 2px 0 10px rgba(0,0,0,0.1);
        }
        
        .sidebar a {
            display: flex;
            align-items: center;
            padding: 12px 15px;
            color: #333;
            text-decoration: none;
            border-radius: 5px;
            margin: 5px 10px;
            transition: all 0.3s ease;
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
        
        .sidebar a i {
            margin-right: 10px;
            width: 20px;
            text-align: center;
        }
        
        .profile-header {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
        }
        
        .profile-avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background-color: #e6e6ff;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 20px;
            color: var(--primary-color);
            font-size: 40px;
        }
        
        .profile-card {
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.05);
            border: none;
            margin-bottom: 20px;
        }
        
        .profile-card .card-header {
            background-color: var(--primary-color);
            color: white;
            border-radius: 10px 10px 0 0 !important;
            padding: 15px 20px;
            font-weight: 500;
        }
        
        .edit-form {
            display: none;
        }
        
        .btn-save {
            background-color: var(--primary-color);
            color: white;
        }
        
        .btn-save:hover {
            background-color: var(--secondary-color);
            color: white;
        }
        
        .form-control:disabled {
            background-color: #f8f9fa;
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
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 sidebar">
                <div class="text-center py-4">
                    <img src="${pageContext.request.contextPath}/images/logo-uitm.png" alt="Logo" class="img-fluid" style="max-width: 100px;">
                </div>
                <a href="StudentDashboardServlet">
                    <i class="fas fa-tachometer-alt"></i> Dashboard
                </a>
                <a href="StudentProfileServlet" class="active">
                    <i class="fas fa-user"></i> My Profile
                </a>
                <a href="StudentAllocationServlet">
                    <i class="fas fa-bed"></i> My Allocation
                </a>
                <a href="StudentBillsServlet">
                    <i class="fas fa-file-invoice-dollar"></i> My Bills
                </a>
                <a href="StudentMaintenanceServlet">
                    <i class="fas fa-tools"></i> Maintenance
                </a>
                <a href="StudentVisitorServlet">
                    <i class="fas fa-address-book"></i> Visitors
                </a>
                <a href="logout.jsp" class="text-danger mt-4">
                    <i class="fas fa-sign-out-alt"></i> Log Out
                </a>
            </div>
            
            <!-- Main Content -->
            <div class="col-md-10 py-4">
                <div class="container">
                    <!-- Profile Header -->
                    <div class="profile-header">
                        <div class="profile-avatar">
                            <i class="fas fa-user"></i>
                        </div>
                        <div>
                            <h2>My Profile</h2>
                            <p class="text-muted mb-0">
                                Manage your personal information and account settings
                            </p>
                        </div>
                    </div>
                    
                    <!-- Messages -->
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success alert-dismissible fade show">
                            ${successMessage}
                            <button type="button" class="close" data-dismiss="alert">
                                <span>&times;</span>
                            </button>
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger alert-dismissible fade show">
                            ${errorMessage}
                            <button type="button" class="close" data-dismiss="alert">
                                <span>&times;</span>
                            </button>
                        </div>
                    </c:if>
                    
                    <!-- Profile Card -->
                    <div class="card profile-card">
                        <div class="card-header">
                            <i class="fas fa-user-circle mr-2"></i> Personal Information
                        </div>
                        <div class="card-body">
                            <!-- View Mode -->
                            <div id="viewMode">
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Student ID</label>
                                        <p>${student.studentID}</p>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Name</label>
                                        <p>${student.sName}</p>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Program</label>
                                        <p>${student.program}</p>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Semester</label>
                                        <p>${student.semester}</p>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Gender</label>
                                        <p>${student.gender == 'M' ? 'Male' : 'Female'}</p>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Merit Score</label>
                                        <p>${student.merit}</p>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Phone Number</label>
                                        <p>${student.sPho}</p>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="font-weight-bold">Email</label>
                                        <p>${student.sEmail}</p>
                                    </div>
                                </div>
                                <button id="editBtn" class="btn btn-primary">
                                    <i class="fas fa-edit mr-2"></i> Edit Profile
                                </button>
                            </div>
                            
                            <!-- Edit Mode -->
                            <div id="editMode" class="edit-form">
                                <form method="post" action="StudentProfileServlet">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Student ID</label>
                                            <input type="text" class="form-control" value="${student.studentID}" disabled>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Name</label>
                                            <input type="text" name="name" class="form-control" value="${student.sName}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Program</label>
                                            <input type="text" class="form-control" value="${student.program}" disabled>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Semester</label>
                                            <input type="text" class="form-control" value="${student.semester}" disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Gender</label>
                                            <input type="text" class="form-control" value="${student.gender == 'M' ? 'Male' : 'Female'}" disabled>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Merit Score</label>
                                            <input type="text" class="form-control" value="${student.merit}" disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Phone Number</label>
                                            <input type="tel" name="phone" class="form-control" value="${student.sPho}" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="font-weight-bold">Email</label>
                                            <input type="email" name="email" class="form-control" value="${student.sEmail}" required>
                                        </div>
                                    </div>
                                    
                                    <hr class="my-4">
                                    
                                    <h5 class="mb-3"><i class="fas fa-lock mr-2"></i> Change Password</h5>
                                    <div class="row mb-3">
                                        <div class="col-md-4 password-toggle">
                                            <label>Current Password</label>
                                            <input type="password" name="currentPassword" class="form-control" id="currentPassword">
                                            <i class="fas fa-eye toggle-icon" onclick="togglePassword('currentPassword')"></i>
                                        </div>
                                        <div class="col-md-4 password-toggle">
                                            <label>New Password</label>
                                            <input type="password" name="newPassword" class="form-control" id="newPassword">
                                            <i class="fas fa-eye toggle-icon" onclick="togglePassword('newPassword')"></i>
                                        </div>
                                        <div class="col-md-4 password-toggle">
                                            <label>Confirm New Password</label>
                                            <input type="password" class="form-control" id="confirmPassword">
                                            <i class="fas fa-eye toggle-icon" onclick="togglePassword('confirmPassword')"></i>
                                        </div>
                                    </div>
                                    
                                    <div class="d-flex justify-content-end">
                                        <button type="button" id="cancelBtn" class="btn btn-outline-secondary mr-2">
                                            <i class="fas fa-times mr-2"></i> Cancel
                                        </button>
                                        <button type="submit" class="btn btn-save">
                                            <i class="fas fa-save mr-2"></i> Save Changes
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
       