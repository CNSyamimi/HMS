<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Maintenance Requests</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        /* Same styles as overview page */
        :root {
            --primary-color: #4a3aff;
            --pending-color: #ffc107;
            --inprogress-color: #17a2b8;
            --completed-color: #28a745;
            --rejected-color: #dc3545;
        }
        
        .request-table th {
            background-color: #f8f9fa;
        }
        
        .request-table tr {
            transition: all 0.3s ease;
        }
        
        .request-table tr:hover {
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
        
        .search-box {
            position: relative;
        }
        
        .search-box .form-control {
            padding-left: 35px;
        }
        
        .search-box i {
            position: absolute;
            left: 12px;
            top: 10px;
            color: #6c757d;
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
                    <h2 class="mb-4"><i class="fas fa-tools mr-2"></i> Maintenance Requests</h2>
                    
                    <!-- Navigation Tabs -->
                    <div class="maintenance-nav mb-4">
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <a href="MaintenanceOverviewServlet" class="btn btn-outline-primary">
                                <i class="fas fa-chart-pie mr-2"></i>Overview
                            </a>
                            <a href="MaintenanceListRequestServlet" class="btn btn-outline-primary active">
                                <i class="fas fa-list mr-2"></i>All Requests
                            </a>
                            <a href="MaintenancePendingRequestServlet" class="btn btn-outline-primary">
                                <i class="fas fa-clock mr-2"></i>Pending Requests
                            </a>
                        </div>
                    </div>
                    
                    <!-- Search and Filter -->
                    <div class="row mb-4">
                        <div class="col-md-8">
                            <div class="search-box">
                                <i class="fas fa-search"></i>
                                <input type="text" class="form-control" placeholder="Search requests..." id="searchInput">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <select class="form-control" id="statusFilter">
                                <option value="">All Statuses</option>
                                <option value="Pending">Pending</option>
                                <option value="In Progress">In Progress</option>
                                <option value="Completed">Completed</option>
                                <option value="Rejected">Rejected</option>
                            </select>
                        </div>
                    </div>
                    
                    <!-- Requests Table -->
                    <div class="card">
                        <div class="card-body p-0">
                            <div class="table-responsive">
                                <table class="table table-hover request-table">
                                    <thead>
                                        <tr>
                                            <th>Request ID</th>
                                            <th>Issue Type</th>
                                            <th>Student</th>
                                            <th>Room</th>
                                            <th>Request Date</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="request" items="${allRequests}">
                                            <tr>
                                                <td>${request.requestID}</td>
                                                <td>${request.issue_type}</td>
                                                <td>
                                                    ${request.studentName}<br>
                                                    <small class="text-muted">ID: ${request.studentID}</small>
                                                </td>
                                                <td>${request.blockID}-${request.roomID}</td>
                                                <td><fmt:formatDate value="${request.request_date}" pattern="dd MMM yyyy"/></td>
                                                <td>
                                                    <span class="status-badge 
                                                        <c:choose>
                                                            <c:when test="${request.status eq 'Pending'}">status-pending</c:when>
                                                            <c:when test="${request.status eq 'In Progress'}">status-inprogress</c:when>
                                                            <c:when test="${request.status eq 'Completed'}">status-completed</c:when>
                                                            <c:otherwise>status-rejected</c:otherwise>
                                                        </c:choose>">
                                                        ${request.status}
                                                    </span>
                                                </td>
                                                <td>
                                                    <button class="btn btn-sm btn-outline-primary view-details" 
                                                            data-id="${request.requestID}">
                                                        <i class="fas fa-eye"></i> View
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                            <c:if test="${empty allRequests}">
                                <div class="text-center py-4">
                                    <i class="fas fa-tools fa-3x text-muted mb-3"></i>
                                    <h5 class="text-muted">No maintenance requests found</h5>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- View Details Modal -->
    <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Maintenance Request Details</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="requestDetails">
                    <!-- Details will be loaded here via AJAX -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            // Search functionality
            $("#searchInput").on("keyup", function() {
                const value = $(this).val().toLowerCase();
                $(".request-table tbody tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
            
            // Status filter
            $("#statusFilter").change(function() {
                const status = $(this).val();
                if (status === "") {
                    $(".request-table tbody tr").show();
                } else {
                    $(".request-table tbody tr").each(function() {
                        const rowStatus = $(this).find("td:eq(5)").text().trim();
                        $(this).toggle(rowStatus === status);
                    });
                }
            });
            
            // View details button
            $(".view-details").click(function() {
                const requestId = $(this).data("id");
                $("#requestDetails").load("MaintenanceDetailsServlet?requestId=" + requestId, function() {
                    $("#detailsModal").modal("show");
                });
            });
        });
    </script>
</body>
</html>