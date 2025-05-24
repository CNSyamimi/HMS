<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<header class="main-header">
    <div class="header-left">
        <button class="sidebar-toggle">
            <i class="fas fa-bars"></i>
        </button>
        <a href="${pageContext.request.contextPath}/dashboard" class="logo">
            <img src="${pageContext.request.contextPath}/images/logo-sm.png" alt="Logo">
            <span class="logo-text">HostelMS</span>
        </a>
    </div>
    
    <div class="header-right">
        <div class="search-box">
            <input type="text" placeholder="Search...">
            <button><i class="fas fa-search"></i></button>
        </div>
        
        <div class="notifications">
            <div class="notification-icon">
                <i class="fas fa-bell"></i>
                <span class="badge">3</span>
            </div>
            <div class="notification-dropdown">
                <div class="notification-item">
                    <i class="fas fa-exclamation-circle text-warning"></i>
                    <div>
                        <p>New maintenance request received</p>
                        <small>10 minutes ago</small>
                    </div>
                </div>
                <div class="notification-item">
                    <i class="fas fa-check-circle text-success"></i>
                    <div>
                        <p>Your bill payment was successful</p>
                        <small>1 hour ago</small>
                    </div>
                </div>
                <div class="notification-item">
                    <i class="fas fa-user-clock text-info"></i>
                    <div>
                        <p>New visitor registration pending approval</p>
                        <small>3 hours ago</small>
                    </div>
                </div>
                <a href="#" class="see-all">See all notifications</a>
            </div>
        </div>
        
        <div class="user-profile">
            <div class="profile-info">
                <img src="${pageContext.request.contextPath}/uploads/profile/${user.profileImage}" alt="Profile" class="profile-pic">
                <div class="profile-details">
                    <span class="user-name">${user.name}</span>
                    <span class="user-role">
                        <c:choose>
                            <c:when test="${user.role == 'admin'}">Administrator</c:when>
                            <c:when test="${user.role == 'warden'}">Warden</c:when>
                            <c:when test="${user.role == 'student'}">Student</c:when>
                        </c:choose>
                    </span>
                </div>
                <i class="fas fa-chevron-down"></i>
            </div>
            <div class="profile-dropdown">
                <a href="${pageContext.request.contextPath}/profile"><i class="fas fa-user"></i> My Profile</a>
                <a href="${pageContext.request.contextPath}/settings"><i class="fas fa-cog"></i> Settings</a>
                <div class="dropdown-divider"></div>
                <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </div>
        </div>
    </div>
</header>