<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4a3aff;
            --secondary-color: #1565c0;
            --pending-color: #ffc107;
            --completed-color: #28a745;
            --rejected-color: #dc3545;
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
            margin-bottom: 20px;
        }
        
        .profile-avatar {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background-color: #e6e6ff;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 20px;
            color: var(--primary-color);
            font-size: 30px;
        }
        
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.05);
            border: none;
            margin-bottom: 20px;
        }
        
        .card-header {
            background-color: var(--primary-color);
            color: white;
            border-radius: 10px 10px 0 0 !important;
            padding: 15px 20px;
            font-weight: 500;
        }
        
        .stat-card {
            text-align: center;
            padding: 15px;
            border-radius: 8px;
            background-color: #f8f9fa;
            margin-bottom: 15px;
            transition: all 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        
        .stat-value {
            font-size: 24px;
            font-weight: 600;
        }
        
        .status-badge {
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 500;
        }
        
        .status-pending { background-color: #fff3cd; color: #856404; }
        .status-completed { background-color: #d4edda; color: #155724; }
        .status-rejected { background-color: #f8d7da; color: #721c24; }
        
        .room-badge {
            background-color: #e6e6ff;
            color: var(--primary-color);
            padding: 3px 8px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 500;
        }
        
        .request-item {
            border-left: 4px solid var(--pending-color);
            margin-bottom: 10px;
            transition: all 0.3s ease;
        }
        
        .request-item:hover {
            background-color: #f8f9fa;
        }
        
        .btn-maintenance {
            background-color: var(--primary-color);
            color: white;
            font-weight: 500;
        }
        
        .btn-maintenance:hover {
            background-color: var(--secondary-color);
            color: white;
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
                <a href="StudentDashboardServlet" class="active">
                    <i class="fas fa-tachometer-alt"></i> Dashboard
                </a>
                <a href="StudentProfileServlet">
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
                    <!-- Student Profile Header -->
                    <div class="profile-header">
                        <div class="profile-avatar">
                            <i class="fas fa-user"></i>
                        </div>
                        <div>
                            <h3>${student.sName}</h3>
                            <p class="text-muted mb-1">
                                ${student.program} | Semester ${student.semester}
                            </p>
                            <p class="text-muted mb-0">
                                <i class="fas fa-id-card mr-1"></i> ${student.studentID} | 
                                <i class="fas fa-${student.gender == 'M' ? 'mars' : 'venus'} mr-1"></i> 
                                ${student.gender == 'M' ? 'Male' : 'Female'}
                            </p>
                        </div>
                    </div>
                    
                    <!-- Dashboard Stats -->
                    <div class="row mb-4">
                        <div class="col-md-3">
                            <div class="stat-card">
                                <div class="stat-value text-primary">
                                    <c:choose>
                                        <c:when test="${not empty allocation}">Allocated</c:when>
                                        <c:otherwise>Not Allocated</c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="text-muted">Room Status</div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="stat-card">
                                <div class="stat-value text-danger">${pendingBillsCount}</div>
                                <div class="text-muted">Pending Bills</div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="stat-card">
                                <div class="stat-value">
                                    <fmt:formatNumber value="${student.merit}" maxFractionDigits="2"/>
                                </div>
                                <div class="text-muted">Merit Score</div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="stat-card">
                                <div class="stat-value">${maintenanceRequests.size()}</div>
                                <div class="text-muted">Maintenance Requests</div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Current Allocation -->
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header">
                                    <i class="fas fa-bed mr-2"></i> Current Allocation
                                </div>
                                <div class="card-body">
                                    <c:choose>
                                        <c:when test="${not empty allocation}">
                                            <p>
                                                <span class="room-badge">${allocation.blockID}-${allocation.roomID}</span>
                                            </p>
                                            <p>
                                                <strong>From:</strong> 
                                                <fmt:formatDate value="${allocation.date_from}" pattern="dd MMM yyyy"/>
                                            </p>
                                            <c:if test="${not empty allocation.date_to}">
                                                <p>
                                                    <strong>To:</strong> 
                                                    <fmt:formatDate value="${allocation.date_to}" pattern="dd MMM yyyy"/>
                                                </p>
                                            </c:if>
                                            <p class="mb-0">
                                                <strong>Status:</strong> ${allocation.status}
                                            </p>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="text-center py-3">
                                                <i class="fas fa-bed fa-2x text-muted mb-3"></i>
                                                <h5 class="text-muted">No current allocation</h5>
                                                <p class="text-muted">You haven't been allocated to any room yet</p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            
                            <!-- Recent Bills -->
                            <div class="card">
                                <div class="card-header">
                                    <i class="fas fa-file-invoice-dollar mr-2"></i> Recent Bills
                                </div>
                                <div class="card-body">
                                    <c:choose>
                                        <c:when test="${not empty bills}">
                                            <div class="list-group">
                                                <c:forEach var="bill" items="${bills}" begin="0" end="2">
                                                    <div class="list-group-item mb-2">
                                                        <div class="d-flex justify-content-between">
                                                            <div>
                                                                <strong>${bill.billType}</strong><br>
                                                                <small class="text-muted">
                                                                    <fmt:formatDate value="${bill.dueDate}" pattern="dd MMM yyyy"/>
                                                                </small>
                                                            </div>
                                                            <div class="text-right">
                                                                <fmt:formatNumber value="${bill.amount}" type="currency" currencySymbol="RM"/><br>
                                                                <span class="badge ${bill.status eq 'Paid' ? 'badge-success' : 'badge-danger'}">
                                                                    ${bill.status}
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <a href="StudentBillsServlet" class="btn btn-sm btn-outline-primary mt-2">
                                                View All Bills <i class="fas fa-arrow-right ml-1"></i>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="text-center py-3">
                                                <i class="fas fa-file-invoice-dollar fa-2x text-muted mb-3"></i>
                                                <h5 class="text-muted">No bills found</h5>
                                                <p class="text-muted">You don't have any bills yet</p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Maintenance Requests -->
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <span><i class="fas fa-tools mr-2"></i> Recent Maintenance Requests</span>
                                    <a href="StudentMaintenanceServlet" class="btn btn-maintenance btn-sm">
                                        <i class="fas fa-plus mr-1"></i> New Request
                                    </a>
                                </div>
                                <div class="card-body">
                                    <c:choose>
                                        <c:when test="${not empty maintenanceRequests}">
                                            <div class="list-group">
                                                <c:forEach var="request" items="${maintenanceRequests}">
                                                    <div class="list-group-item request-item mb-2">
                                                        <div class="d-flex justify-content-between">
                                                            <div>
                                                                <strong>${request.issue_type}</strong><br>
                                                                <small class="text-muted">
                                                                    <fmt:formatDate value="${request.request_date}" pattern="dd MMM yyyy"/>
                                                                </small>
                                                            </div>
                                                            <div>
                                                                <span class="status-badge 
                                                                    <c:choose>
                                                                        <c:when test="${request.status eq 'Pending'}">status-pending</c:when>
                                                                        <c:when test="${request.status eq 'Completed'}">status-completed</c:when>
                                                                        <c:otherwise>status-rejected</c:otherwise>
                                                                    </c:choose>">
                                                                    ${request.status}
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <p class="mb-0 mt-2">${request.description}</p>
                                                        <small class="text-muted">
                                                            Room: ${request.blockID}-${request.roomID}
                                                        </small>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <a href="StudentMaintenanceServlet" class="btn btn-sm btn-outline-primary mt-2">
                                                View All Requests <i class="fas fa-arrow-right ml-1"></i>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="text-center py-3">
                                                <i class="fas fa-tools fa-2x text-muted mb-3"></i>
                                                <h5 class="text-muted">No maintenance requests</h5>
                                                <p class="text-muted">You haven't submitted any maintenance requests yet</p>
                                                <a href="StudentMaintenanceServlet" class="btn btn-maintenance">
                                                    <i class="fas fa-plus mr-1"></i> Create Request
                                                </a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
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
</body>
</html>