<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar">
    <div class="sidebar-profile">
        <img src="${pageContext.request.contextPath}/uploads/profile/${user.profileImage}" alt="Profile" class="profile-image">
        <div class="profile-text">
            <h4>${user.name}</h4>
            <p>
                <c:choose>
                    <c:when test="${user.role == 'admin'}">
                        <i class="fas fa-user-shield"></i> Administrator
                    </c:when>
                    <c:when test="${user.role == 'warden'}">
                        <i class="fas fa-user-tie"></i> Warden
                    </c:when>
                    <c:when test="${user.role == 'student'}">
                        <i class="fas fa-user-graduate"></i> Student
                    </c:when>
                </c:choose>
            </p>
        </div>
    </div>
    
    <div class="sidebar-menu">
        <ul class="menu-list">
            <li class="menu-header">MAIN NAVIGATION</li>
            <li class="menu-item ${pageContext.request.requestURI.endsWith('dashboard.jsp') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/dashboard">
                    <i class="fas fa-tachometer-alt"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            
            <c:if test="${user.role == 'admin' || user.role == 'warden'}">
                <li class="menu-header">MANAGEMENT</li>
                <li class="menu-item ${pageContext.request.requestURI.contains('student') ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/student?action=list">
                        <i class="fas fa-users"></i>
                        <span>Student Management</span>
                    </a>
                </li>
            </c:if>
            
            <c:if test="${user.role == 'admin'}">
                <li class="menu-item ${pageContext.request.requestURI.contains('warden') ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/warden?action=list">
                        <i class="fas fa-user-tie"></i>
                        <span>Warden Management</span>
                    </a>
                </li>
            </c:if>
            
            <li class="menu-item ${pageContext.request.requestURI.contains('room') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/room?action=list">
                    <i class="fas fa-bed"></i>
                    <span>Room Management</span>
                </a>
            </li>
            
            <li class="menu-item ${pageContext.request.requestURI.contains('allocation') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/allocation?action=list">
                    <i class="fas fa-clipboard-list"></i>
                    <span>Room Allocation</span>
                </a>
            </li>
            
            <li class="menu-item ${pageContext.request.requestURI.contains('visitor') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/visitor?action=list">
                    <i class="fas fa-user-friends"></i>
                    <span>Visitor Management</span>
                </a>
            </li>
            
            <li class="menu-item ${pageContext.request.requestURI.contains('maintenance') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/maintenance?action=list">
                    <i class="fas fa-tools"></i>
                    <span>Maintenance</span>
                    <span class="badge">${pendingMaintenanceCount}</span>
                </a>
            </li>
            
            <li class="menu-item ${pageContext.request.requestURI.contains('bill') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/bill?action=list">
                    <i class="fas fa-file-invoice-dollar"></i>
                    <span>Billing</span>
                </a>
            </li>
            
            <c:if test="${user.role == 'admin' || user.role == 'warden'}">
                <li class="menu-item ${pageContext.request.requestURI.contains('report') ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/report">
                        <i class="fas fa-chart-bar"></i>
                        <span>Reports</span>
                    </a>
                </li>
            </c:if>
            
            <li class="menu-header">PERSONAL</li>
            <li class="menu-item ${pageContext.request.requestURI.contains('profile') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/profile">
                    <i class="fas fa-user"></i>
                    <span>My Profile</span>
                </a>
            </li>
            
            <li class="menu-item ${pageContext.request.requestURI.contains('settings') ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/settings">
                    <i class="fas fa-cog"></i>
                    <span>Settings</span>
                </a>
            </li>
            
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/logout">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Logout</span>
                </a>
            </li>
        </ul>
    </div>
</div>