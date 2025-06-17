<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pending Maintenance Requests</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        /* Same styles as overview page */
        :root {
            --primary-color: #4a3aff;
            --pending-color: #ffc107;
        }
        
        .request-card {
            border-left: 4px solid var(--pending-color);
            margin-bottom: 15px;
            transition: all 0.3s ease;
        }
        
        .request-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        
        .action-buttons .btn {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar (same as overview) -->
            <div class="col-md-2 sidebar bg-white">
                <!-- Same sidebar content as overview.jsp -->
            </div>
            
            <!-- Main Content -->
            <div class="col-md-10 py-4">
                <div class="container">
                    <h2 class="mb-4"><i class="fas fa-tools mr-2"></i> Pending Maintenance Requests</h2>
                    
                    <!-- Navigation Tabs -->
                    <div class="maintenance-nav mb-4">
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <a href="MaintenanceOverviewServlet" class="btn btn-outline-primary">
                                <i class="fas fa-chart-pie mr-2"></i>Overview
                            </a>
                            <a href="MaintenanceListRequestServlet" class="btn btn-outline-primary">
                                <i class="fas fa-list mr-2"></i>All Requests
                            </a>
                            <a href="MaintenancePendingRequestServlet" class="btn btn-outline-primary active">
                                <i class="fas fa-clock mr-2"></i>Pending Requests
                            </a>
                        </div>
                    </div>
                    
                    <!-- Pending Requests -->
                    <div class="row">
                        <c:forEach var="request" items="${pendingRequests}">
                            <div class="col-md-6">
                                <div class="card request-card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between mb-3">
                                            <h5 class="card-title mb-0">${request.issue_type}</h5>
                                            <span class="badge badge-warning">Pending</span>
                                        </div>
                                        <p class="card-text">${request.description}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div>
                                                <small class="text-muted">
                                                    <i class="fas fa-user mr-1"></i>${request.studentName} (${request.studentID})<br>
                                                    <i class="fas fa-bed mr-1"></i>${request.blockID}-${request.roomID}<br>
                                                    <i class="fas fa-calendar-alt mr-1"></i>
                                                    <fmt:formatDate value="${request.request_date}" pattern="dd MMM yyyy"/>
                                                </small>
                                            </div>
                                            <div class="action-buttons">
                                                <form method="post" action="MaintenancePendingRequestServlet" style="display: inline;">
                                                    <input type="hidden" name="requestId" value="${request.requestID}">
                                                    <input type="hidden" name="action" value="approve">
                                                    <button type="submit" class="btn btn-success btn-sm">
                                                        <i class="fas fa-check mr-1"></i> Approve
                                                    </button>
                                                </form>
                                                <form method="post" action="MaintenancePendingRequestServlet" style="display: inline;">
                                                    <input type="hidden" name="requestId" value="${request.requestID}">
                                                    <input type="hidden" name="action" value="reject">
                                                    <button type="submit" class="btn btn-danger btn-sm">
                                                        <i class="fas fa-times mr-1"></i> Reject
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    
                    <c:if test="${empty pendingRequests}">
                        <div class="text-center py-4">
                            <i class="fas fa-clock fa-3x text-muted mb-3"></i>
                            <h5 class="text-muted">No pending maintenance requests</h5>
                            <p class="text-muted">All maintenance requests have been processed</p>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>