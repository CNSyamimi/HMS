<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Allocation View</title>
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            background: linear-gradient(to bottom right, #dcd6f7, #a6a6f7);
            font-family: 'Segoe UI', sans-serif;
        }
        
        .sidebar {
            width: 250px;
            background: #fff;
            padding: 20px;
            border-right: 1px solid #ddd;
        }
        
        .sidebar img {
            width: 100px;
            margin-bottom: 20px;
        }
        
        .sidebar a {
            display: flex;
            align-items: center;
            padding: 12px 15px;
            color: #333;
            text-decoration: none;
            margin-bottom: 5px;
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
            font-weight: bold;
        }
        
        .main {
            flex-grow: 1;
            padding: 30px;
        }
        
        .card {
            background: white;
            border-radius: 12px;
            padding: 25px;
            margin-bottom: 25px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            border: none;
        }
        
        .card-header {
            background-color: var(--primary-color);
            color: white;
            border-radius: 10px 10px 0 0 !important;
            padding: 15px 25px;
            font-weight: 500;
            border: none;
        }
        
        .stat-card {
            text-align: center;
            padding: 20px;
            border-radius: 10px;
            background-color: #f8f9fa;
            margin-bottom: 15px;
        }
        
        .stat-value {
            font-size: 28px;
            font-weight: bold;
            color: var(--primary-color);
            margin-bottom: 5px;
        }
        
        .stat-label {
            font-size: 14px;
            color: #6c757d;
        }
        
        .table {
            border-collapse: separate;
            border-spacing: 0 10px;
        }
        
        .table th {
            border-top: none;
            font-weight: 500;
            color: #495057;
            background-color: #f8f9fa;
        }
        
        .table td {
            vertical-align: middle;
            border-top: 1px solid #f1f1f1;
        }
        
        .table tr {
            transition: all 0.3s ease;
        }
        
        .table tr:hover {
            background-color: #f8f9fa;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
        }
        
        .status-badge {
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 500;
        }
        
        .status-active {
            background-color: #e6f7ee;
            color: #28a745;
        }
        
        .status-inactive {
            background-color: #fdecea;
            color: #dc3545;
        }
        
        .room-badge {
            background-color: #e6e6ff;
            color: var(--primary-color);
            padding: 3px 8px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 500;
        }
        
        .student-name {
            font-weight: 500;
            color: var(--dark-bg);
        }
        
        .student-id {
            font-size: 12px;
            color: #6c757d;
        }
        
        .search-box {
            position: relative;
            margin-bottom: 20px;
        }
        
        .search-box input {
            padding-left: 35px;
            border-radius: 20px;
            border: 1px solid #ddd;
        }
        
        .search-box i {
            position: absolute;
            left: 12px;
            top: 10px;
            color: #6c757d;
        }
        
        @media (max-width: 768px) {
            body {
                flex-direction: column;
            }
            
            .sidebar {
                width: 100%;
                height: auto;
            }
            
            .main {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <img src="${pageContext.request.contextPath}/images/logo-uitm.png" alt="UiTM Logo">
        <a href="adminDashboard.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
        <a href="adminProfile.jsp"><i class="fas fa-user"></i> My Profile</a>
        <a href="studentManagement.jsp"><i class="fas fa-users"></i> Student</a>
        <a href="wardenManagement.jsp"><i class="fas fa-user-shield"></i> Warden</a>
        <a href="allocationView.jsp" class="active"><i class="fas fa-bed"></i> Allocation</a>
        <a href="billManagement.jsp"><i class="fas fa-file-invoice-dollar"></i> Manage Bills</a>
        <a href="maintenanceManagement.jsp"><i class="fas fa-tools"></i> Maintenance</a>
        <a href="visitorManagement.jsp"><i class="fas fa-address-book"></i> Visitor</a>
        <a href="login.jsp"><i class="fas fa-sign-out-alt"></i> Log Out</a>
    </div>

    <div class="main">
        <div class="card">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h4 class="mb-0"><i class="fas fa-bed mr-2"></i> Room Allocations</h4>
                <div class="search-box">
                    <i class="fas fa-search"></i>
                    <input type="text" class="form-control" placeholder="Search allocations..." id="searchInput">
                </div>
            </div>
            
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value">${allocations.size()}</div>
                        <div class="stat-label">Total Allocations</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value">
                            <c:set var="activeCount" value="0" />
                            <c:forEach var="view" items="${allocations}">
                                <c:if test="${view.allocation.status eq 'Active'}">
                                    <c:set var="activeCount" value="${activeCount + 1}" />
                                </c:if>
                            </c:forEach>
                            ${activeCount}
                        </div>
                        <div class="stat-label">Active Allocations</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value">
                            <c:set var="maleCount" value="0" />
                            <c:forEach var="view" items="${allocations}">
                                <c:if test="${view.student.gender eq 'M'}">
                                    <c:set var="maleCount" value="${maleCount + 1}" />
                                </c:if>
                            </c:forEach>
                            ${maleCount}
                        </div>
                        <div class="stat-label">Male Students</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value">
                            <c:set var="femaleCount" value="0" />
                            <c:forEach var="view" items="${allocations}">
                                <c:if test="${view.student.gender eq 'F'}">
                                    <c:set var="femaleCount" value="${femaleCount + 1}" />
                                </c:if>
                            </c:forEach>
                            ${femaleCount}
                        </div>
                        <div class="stat-label">Female Students</div>
                    </div>
                </div>
            </div>
            
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Student</th>
                            <th>Program</th>
                            <th>Room</th>
                            <th>Duration</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="view" items="${allocations}">
                            <tr>
                                <td>
                                    <div class="student-name">${view.student.sName}</div>
                                    <div class="student-id">ID: ${view.student.studentID}</div>
                                </td>
                                <td>
                                    ${view.student.program}<br>
                                    <small class="text-muted">Sem ${view.student.semester}</small>
                                </td>
                                <td>
                                    <span class="room-badge">${view.room.blockID}-${view.room.roomID}</span><br>
                                    <small class="text-muted">${view.room.curOcc}/${view.room.capacity} occupied</small>
                                </td>
                                <td>
                                    ${view.allocation.date_from} <br>
                                    <small class="text-muted">to ${view.allocation.date_to != null ? view.allocation.date_to : 'Present'}</small>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${view.allocation.status eq 'Active'}">
                                            <span class="status-badge status-active">
                                                <i class="fas fa-check-circle mr-1"></i> Active
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status-badge status-inactive">
                                                <i class="fas fa-times-circle mr-1"></i> Inactive
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <c:if test="${empty allocations}">
                <div class="text-center py-4">
                    <i class="fas fa-bed fa-3x text-muted mb-3"></i>
                    <h5 class="text-muted">No allocation records found</h5>
                    <p class="text-muted">There are currently no room allocations in the system</p>
                </div>
            </c:if>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(function() {
            // Search functionality
            $("#searchInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("table tbody tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
            
            // Highlight row on hover
            $("table tbody tr").hover(
                function() {
                    $(this).css('transform', 'translateY(-2px)');
                    $(this).css('box-shadow', '0 4px 8px rgba(0,0,0,0.05)');
                },
                function() {
                    $(this).css('transform', 'translateY(0)');
                    $(this).css('box-shadow', 'none');
                }
            );
        });
    </script>
</body>
</html>