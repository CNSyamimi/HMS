<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Maintenance Management | Hostel Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/newcss.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="${pageContext.request.contextPath}/js/maintenance.js"></script>
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    <jsp:include page="../common/sidebar.jsp" />
    
    <div class="main-content">
        <div class="page-header">
            <h1>Maintenance Management</h1>
            <div class="header-actions">
                <button class="btn btn-primary" onclick="showAddMaintenanceModal()">
                    <i class="fas fa-plus"></i> Add New Request
                </button>
            </div>
        </div>
        
        <div class="content-card">
            <div class="filter-section">
                <form id="filterForm" class="filter-form">
                    <div class="form-group">
                        <label for="statusFilter">Status:</label>
                        <select id="statusFilter" name="status" class="form-control">
                            <option value="">All Status</option>
                            <option value="Pending">Pending</option>
                            <option value="In Progress">In Progress</option>
                            <option value="Completed">Completed</option>
                            <option value="Rejected">Rejected</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="blockFilter">Hostel Block:</label>
                        <select id="blockFilter" name="block" class="form-control">
                            <option value="">All Blocks</option>
                            <c:forEach items="${hostelBlocks}" var="block">
                                <option value="${block.blockId}">${block.blockName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dateFrom">From:</label>
                        <input type="date" id="dateFrom" name="dateFrom" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="dateTo">To:</label>
                        <input type="date" id="dateTo" name="dateTo" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-filter">
                        <i class="fas fa-filter"></i> Filter
                    </button>
                    <button type="reset" class="btn btn-reset">
                        <i class="fas fa-redo"></i> Reset
                    </button>
                </form>
            </div>
            
            <div class="table-responsive">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Request ID</th>
                            <th>Student</th>
                            <th>Room</th>
                            <th>Issue Type</th>
                            <th>Request Date</th>
                            <th>Status</th>
                            <th>Priority</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${maintenanceRequests}" var="request">
                            <tr>
                                <td>${request.requestId}</td>
                                <td>${request.student.name} (${request.student.matricNumber})</td>
                                <td>${request.room.roomNumber}</td>
                                <td>${request.issueType}</td>
                                <td>${request.requestDate}</td>
                                <td>
                                    <span class="status-badge ${request.status.toLowerCase().replace(' ', '-')}">
                                        ${request.status}
                                    </span>
                                </td>
                                <td>
                                    <span class="priority-badge ${request.priority.toLowerCase()}">
                                        ${request.priority}
                                    </span>
                                </td>
                                <td class="action-buttons">
                                    <button class="btn btn-view" onclick="viewMaintenanceDetails(${request.requestId})">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                    <button class="btn btn-edit" onclick="editMaintenanceRequest(${request.requestId})">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-delete" onclick="confirmDeleteRequest(${request.requestId})">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}" class="page-link">&laquo; Previous</a>
                </c:if>
                
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <a href="?page=${i}" class="page-link active">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${i}" class="page-link">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                
                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}" class="page-link">Next &raquo;</a>
                </c:if>
            </div>
        </div>
    </div>
    
    <!-- Maintenance Details Modal -->
    <div id="maintenanceModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2 id="modalTitle">Maintenance Request Details</h2>
            <div id="modalContent">
                <!-- Content will be loaded via AJAX -->
            </div>
        </div>
    </div>
    
    <!-- Add Maintenance Modal -->
    <div id="addMaintenanceModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Add New Maintenance Request</h2>
            <form id="addMaintenanceForm" action="${pageContext.request.contextPath}/maintenance?action=add" method="post">
                <div class="form-group">
                    <label for="studentSelect">Student:</label>
                    <select id="studentSelect" name="studentId" class="form-control" required>
                        <option value="">Select Student</option>
                        <c:forEach items="${students}" var="student">
                            <option value="${student.studentId}">${student.name} (${student.matricNumber})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="roomSelect">Room:</label>
                    <select id="roomSelect" name="roomId" class="form-control" required>
                        <option value="">Select Room</option>
                        <c:forEach items="${rooms}" var="room">
                            <option value="${room.roomId}">${room.roomNumber} (${room.block.blockName})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="issueType">Issue Type:</label>
                    <select id="issueType" name="issueType" class="form-control" required>
                        <option value="">Select Issue Type</option>
                        <option value="Electrical">Electrical</option>
                        <option value="Plumbing">Plumbing</option>
                        <option value="Furniture">Furniture</option>
                        <option value="Cleaning">Cleaning</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" class="form-control" rows="4" required></textarea>
                </div>
                <div class="form-group">
                    <label for="priority">Priority:</label>
                    <select id="priority" name="priority" class="form-control" required>
                        <option value="Low">Low</option>
                        <option value="Medium" selected>Medium</option>
                        <option value="High">High</option>
                        <option value="Emergency">Emergency</option>
                    </select>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Submit Request</button>
                    <button type="button" class="btn btn-secondary" onclick="closeModal()">Cancel</button>
                </div>
            </form>
        </div>
    </div>
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>