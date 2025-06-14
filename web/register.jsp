<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hostel Management System - Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/newcss.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
</head>
<body class="register-page">
    <div class="register-container">
        <div class="register-header">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Hostel Logo" class="logo">
            <h1>Student Registration</h1>
            <p>Fill in your details to create an account</p>
        </div>
        
        <form action="${pageContext.request.contextPath}/register" method="post" class="register-form">
            <div class="form-row">
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" required 
                           pattern="[A-Za-z ]{3,50}" title="Only letters and spaces (3-50 characters)"
                           placeholder="Enter your full name">
                    <i class="fas fa-user"></i>
                </div>
                
                <div class="form-group">
                    <label for="matricNumber">Matric Number</label>
                    <input type="text" id="matricNumber" name="matricNumber" required
                           pattern="\d{10}" title="10-digit matric number"
                           placeholder="e.g. 2023123456">
                    <i class="fas fa-id-card"></i>
                </div>
            </div>
            
            <div class="form-group">
                <label for="email">University Email</label>
                <input type="email" id="email" name="email" required
                       pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" 
                       title="Enter a valid email address"
                       placeholder="e.g. abc123@university.edu">
                <i class="fas fa-envelope"></i>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required
                           minlength="8" title="Minimum 8 characters"
                           placeholder="Create password (min 8 chars)">
                    <i class="fas fa-lock"></i>
                </div>
                
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required
                           placeholder="Confirm password">
                    <i class="fas fa-lock"></i>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="program">Program</label>
                    <select id="program" name="program" required>
                        <option value="">-- Select Program --</option>
                        <option value="Computer Science">Computer Science</option>
                        <option value="Information Technology">Information Technology</option>
                        <option value="Data Science">Data Science</option>
                        <option value="Software Engineering">Software Engineering</option>
                    </select>
                    <i class="fas fa-graduation-cap"></i>
                </div>
                
                <div class="form-group">
                    <label for="semester">Current Semester</label>
                    <select id="semester" name="semester" required>
                        <option value="">-- Select Semester --</option>
                        <% for(int i=1; i<=8; i++) { %>
                            <option value="<%=i%>">Semester <%=i%></option>
                        <% } %>
                    </select>
                    <i class="fas fa-calendar-alt"></i>
                </div>
            </div>
            
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="tel" id="phone" name="phone" required
                       pattern="[0-9]{10,15}" title="10-15 digit phone number"
                       placeholder="e.g. 0123456789">
                <i class="fas fa-phone"></i>
            </div>
            
            <div class="form-group terms">
                <input type="checkbox" id="terms" name="terms" required>
                <label for="terms">I agree to the <a href="#">Terms and Conditions</a></label>
            </div>
            
            <button type="submit" class="btn-register">Register</button>
            
            <div class="login-link">
                Already have an account? <a href="${pageContext.request.contextPath}/login.jsp">Login here</a>
            </div>
            
            <c:if test="${not empty error}">
                <div class="alert alert-error">
                    <i class="fas fa-exclamation-circle"></i> ${error}
                </div>
            </c:if>
            
            <c:if test="${not empty success}">
                <div class="alert alert-success">
                    <i class="fas fa-check-circle"></i> ${success}
                </div>
            </c:if>
        </form>
    </div>
    
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>