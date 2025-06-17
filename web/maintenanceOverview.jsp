<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Overview</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4a3aff;
            --secondary-color: #1565c0;
            --pending-color: #ffc107;
            --inprogress-color: #17a2b8;
            --completed-color: #28a745;
            --rejected-color: #dc3545;
        }
        
        body {
            background: linear-gradient(to bottom right, #f5f7fa, #e4e8f0);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .maintenance-nav .btn {
            border-radius: 20px;
            margin-right: 5px;
        }
        
        .stat-card {
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.05);
            transition: all 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.1);
        }
        
        .stat-value {
            font-size: 2.5rem;
            font-weight: 600;
        }
        
        .total-requests { background-color: #f8f9fa; border-left: 4px solid var(--primary-color); }
        .pending-requests { background-color: #fff8e6; border-left: 4px solid var(--pending-color); }
        .inprogress-requests { background-color: #e6f7fb; border-left: 4px solid var(--inprogress-color); }
        .completed-requests { background-color: #e6f7ec; border-left: 4px solid var(--completed-color); }
        
        .recent-requests {
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.05);
        }
        
        .request-item {
            border-left: 4px solid;
            transition: all 0.3s ease;
        }
        
        .request-item:hover {
            background-color: #f8f9fa;
        }
        
        .status-badge {
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 500;
        }
        
        .status-pending { background-color: #fff3cd; color: #856404; }
        .status-inprogress { background-color: #d1ecf1; color: #0c5460; }
        .status-completed { background-color: #d4edda; color: #155724; }
        .status-rejected { background-color: #f8d7da; color: #721c24; }
        
        .issue-type {
            font-weight: 500;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 sidebar bg-white">
                <div class="text-center py-4">
                    <img src="${pageContext.request.contextPath}/images/logo-uitm.png" alt="Logo" class="img-fluid" style="max-width: 100px;">
                </div>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="adminDashboard.jsp"><i class="fas fa-tachometer-alt mr-2"></i> Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="adminProfile.jsp"><i class="fas fa-user mr-2"></i> My Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="studentManagement.jsp"><i class="fas fa-users mr-2"></i> Student</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="wardenManagement.jsp"><i class="fas fa-user-shield mr-2"></i> Warden</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="allocationView.jsp"><i class="fas fa-bed mr-2"></i> Allocation</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="billManagement.jsp"><i class="fas fa-file-invoice-dollar mr-2"></i> Bills</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#"><i class="fas fa-tools mr-2"></i> Maintenance</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="visitorManagement.jsp"><i class="fas fa-address-book mr-2"></i> Visitor</a>
                    </li>
                    <li class="nav-item mt-4">
                        <a class="nav-link text-danger" href="login.jsp"><i class="fas fa-sign-out-alt mr-2"></i> Log Out</a>
                    </li>
                </ul>
            </div>
            
            <!-- Main Content -->
            <div class="col-md-10 py-4">
                <div class="container">
                    <h2 class="mb-4"><i class="fas fa-tools mr-2"></i> Maintenance Management</h2>
                    
                    <!-- Navigation Tabs -->
                    <div class="maintenance-nav mb-4">
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <a href="MaintenanceOverviewServlet" class="btn btn-outline-primary active">
                                <i class="fas fa-chart-pie mr-2"></i>Overview
                            </a>
                            <a href="MaintenanceListRequestServlet" class="btn btn-outline-primary">
                                <i class="fas fa-list mr-2"></i>All Requests
                            </a>
                            <a href="MaintenancePendingRequestServlet" class="btn btn-outline-primary">
                                <i class="fas fa-clock mr-2"></i>Pending Requests
                            </a>
                        </div>
                    </div>
                    
                    <!-- Statistics Cards -->
                    <div class="row mb-4">
                        <div class="col-md-3">
                            <div class="stat-card total-requests">
                                <div class="stat-value text-primary">${totalRequests}</div>
                                <div class="text-muted">Total Requests</div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="stat-card pending-requests">
                                <div class="stat-value text-warning">${pendingRequests}</div>
                                <div class="text-muted">Pending</div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="stat-card inprogress-requests">
                                <div class="stat-value text-info">${inProgressRequests}</div>
                                <div class="text-muted">In Progress</div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="stat-card completed-requests">
                                <div class="stat-value text-success">${completedRequests}</div>
                                <div class="text-muted">Completed</div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Recent Requests -->
                    <div class="card recent-requests">
                        <div class="card-header bg-white">
                            <h5 class="mb-0"><i class="fas fa-history mr-2"></i>Recent Requests</h5>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${not empty recentRequests}">
                                    <div class="list-group">
                                        <c:forEach var="request" items="${recentRequests}">
                                            <div class="list-group-item request-item mb-2" 
                                                 style="border-left-color: 
                                                 <c:choose>
                                                     <c:when test="${request.status eq 'Pending'}">var(--pending-color)</c:when>
                                                     <c:when test="${request.status eq 'In Progress'}">var(--inprogress-color)</c:when>
                                                     <c:when test="${request.status eq 'Completed'}">var(--completed-color)</c:when>
                                                     <c:otherwise>var(--rejected-color)</c:otherwise>
                                                 </c:choose>">
                                                <div class="d-flex justify-content-between">
                                                    <div>
                                                        <span class="issue-type">${request.issue_type}</span>
                                                        <span class="badge 
                                                            <c:choose>
                                                                <c:when test="${request.status eq 'Pending'}">status-pending</c:when>
                                                                <c:when test="${request.status eq 'In Progress'}">status-inprogress</c:when>
                                                                <c:when test="${request.status eq 'Completed'}">status-completed</c:when>
                                                                <c:otherwise>status-rejected</c:otherwise>
                                                            </c:choose>">
                                                            ${request.status}
                                                        </span>
                                                    </div>
                                                    <small class="text-muted">
                                                        <fmt:formatDate value="${request.request_date}" pattern="dd MMM yyyy"/>
                                                    </small>
                                                </div>
                                                <p class="mb-1 mt-2">${request.description}</p>
                                                <small class="text-muted">
                                                    Room: ${request.roomID} | 
                                                    Student: ${request.studentName} (${request.studentID})
                                                </small>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="text-center py-4">
                                        <i class="fas fa-tools fa-3x text-muted mb-3"></i>
                                        <h5 class="text-muted">No recent maintenance requests</h5>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>