<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Bills</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4a3aff;
            --secondary-color: #1565c0;
            --light-bg: #f8f9fa;
            --dark-bg: #343a40;
            --paid-color: #28a745;
            --unpaid-color: #dc3545;
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
        
        .student-header {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 20px;
            border-bottom: 1px solid #eee;
        }
        
        .student-avatar {
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
        
        .student-info h4 {
            margin-bottom: 5px;
            color: var(--dark-bg);
        }
        
        .student-meta {
            font-size: 14px;
            color: #6c757d;
        }
        
        .stat-card {
            text-align: center;
            padding: 20px;
            border-radius: 10px;
            background-color: #f8f9fa;
            margin-bottom: 15px;
            transition: all 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        
        .stat-value {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        
        .stat-paid {
            color: var(--paid-color);
        }
        
        .stat-unpaid {
            color: var(--unpaid-color);
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
        
        .status-paid {
            background-color: #e6f7ee;
            color: var(--paid-color);
        }
        
        .status-unpaid {
            background-color: #fdecea;
            color: var(--unpaid-color);
        }
        
        .amount-paid {
            color: var(--paid-color);
            font-weight: 500;
        }
        
        .amount-unpaid {
            color: var(--unpaid-color);
            font-weight: 500;
        }
        
        .due-date {
            font-weight: 500;
        }
        
        .due-date.overdue {
            color: var(--unpaid-color);
        }
        
        .bill-type {
            background-color: #e6e6ff;
            color: var(--primary-color);
            padding: 3px 8px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 500;
        }
        
        .back-btn {
            background-color: #6c757d;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            font-weight: 500;
            transition: all 0.3s ease;
        }
        
        .back-btn:hover {
            background-color: #5a6268;
            color: white;
            text-decoration: none;
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
            
            .student-header {
                flex-direction: column;
                text-align: center;
            }
            
            .student-avatar {
                margin-right: 0;
                margin-bottom: 15px;
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
        <a href="allocationView.jsp"><i class="fas fa-bed"></i> Allocation</a>
        <a href="billManagement.jsp"><i class="fas fa-file-invoice-dollar"></i> Manage Bills</a>
        <a href="maintenanceManagement.jsp"><i class="fas fa-tools"></i> Maintenance</a>
        <a href="visitorManagement.jsp"><i class="fas fa-address-book"></i> Visitor</a>
        <a href="login.jsp"><i class="fas fa-sign-out-alt"></i> Log Out</a>
    </div>

    <div class="main">
        <div class="card">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h4 class="mb-0"><i class="fas fa-file-invoice-dollar mr-2"></i> Student Bills</h4>
                <a href="billManagement.jsp" class="back-btn">
                    <i class="fas fa-arrow-left mr-2"></i>Back to All Bills
                </a>
            </div>
            
            <div class="student-header">
                <div class="student-avatar">
                    <i class="fas fa-user"></i>
                </div>
                <div class="student-info">
                    <h4>${student.sName}</h4>
                    <div class="student-meta">
                        <span>ID: ${student.studentID}</span> | 
                        <span>${student.program}</span> | 
                        <span>Semester ${student.semester}</span>
                    </div>
                    <div class="student-meta mt-1">
                        <i class="fas fa-${student.gender == 'M' ? 'mars' : 'venus'} mr-1"></i>
                        <span>${student.gender == 'M' ? 'Male' : 'Female'}</span> | 
                        <i class="fas fa-phone mr-1"></i>
                        <span>${student.sPho}</span> | 
                        <i class="fas fa-envelope mr-1"></i>
                        <span>${student.sEmail}</span>
                    </div>
                </div>
            </div>
            
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value">${bills.size()}</div>
                        <div class="stat-label">Total Bills</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value stat-paid">${paidCount}</div>
                        <div class="stat-label">Paid Bills</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value stat-unpaid">${unpaidCount}</div>
                        <div class="stat-label">Unpaid Bills</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="stat-card">
                        <div class="stat-value stat-unpaid">
                            <fmt:formatNumber value="${totalUnpaid}" type="currency" currencySymbol="RM"/>
                        </div>
                        <div class="stat-label">Total Unpaid</div>
                    </div>
                </div>
            </div>
            
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Bill</th>
                            <th>Amount</th>
                            <th>Due Date</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="bill" items="${bills}">
                            <tr>
                                <td>
                                    <span class="bill-type">${bill.billType}</span><br>
                                    <small class="text-muted">ID: ${bill.billID}</small>
                                </td>
                                <td class="${bill.status eq 'Paid' ? 'amount-paid' : 'amount-unpaid'}">
                                    <fmt:formatNumber value="${bill.amount}" type="currency" currencySymbol="RM"/>
                                </td>
                                <td>
                                    <span class="due-date ${bill.status eq 'Unpaid' && bill.dueDate.time lt today.time ? 'overdue' : ''}">
                                        <fmt:formatDate value="${bill.dueDate}" pattern="dd MMM yyyy"/>
                                    </span>
                                    <c:if test="${bill.status eq 'Unpaid' && bill.dueDate.time lt today.time}">
                                        <br><small class="text-danger">Overdue</small>
                                    </c:if>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${bill.status eq 'Paid'}">
                                            <span class="status-badge status-paid">
                                                <i class="fas fa-check-circle mr-1"></i> Paid
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status-badge status-unpaid">
                                                <i class="fas fa-times-circle mr-1"></i> Unpaid
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <button class="btn btn-sm btn-outline-primary mr-2">
                                        <i class="fas fa-eye"></i> View
                                    </button>
                                    <button class="btn btn-sm btn-outline-success">
                                        <i class="fas fa-receipt"></i> Receipt
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <c:if test="${empty bills}">
                <div class="text-center py-4">
                    <i class="fas fa-file-invoice-dollar fa-3x text-muted mb-3"></i>
                    <h5 class="text-muted">No bills found for this student</h5>
                    <p class="text-muted">This student currently has no bills in the system</p>
                </div>
            </c:if>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        // Set today's date for overdue comparison
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        
        $(document).ready(function() {
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
            
            // Mark overdue bills
            $(".due-date").each(function() {
                const dueDateText = $(this).text().trim();
                const dueDate = new Date(dueDateText);
                dueDate.setHours(0, 0, 0, 0);
                
                if ($(this).hasClass("overdue") || (dueDate < today && !$(this).parent().find(".status-paid").length)) {
                    $(this).addClass("overdue");
                    $(this).append('<br><small class="text-danger">Overdue</small>');
                }
            });
        });
    </script>
</body>
</html>