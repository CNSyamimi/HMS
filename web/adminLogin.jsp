<%-- 
    Document   : adminLogin
    Created on : 21 Jun 2025, 2:27:02 am
    Author     : USER
--%>

<%-- src/main/webapp/adminLogin.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login - Hostel Management</title>
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #e0f2f7; /* Light blue background */
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            color: #333;
        }
        .login-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px; /* Rounded corners */
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
            border: 1px solid #cce7ee;
        }
        h2 {
            color: #263238; /* Darker blue-grey */
            margin-bottom: 30px;
            font-size: 1.8em;
        }
        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #455a64;
        }
        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: calc(100% - 22px); /* Account for padding and border */
            padding: 12px;
            border: 1px solid #b0bec5; /* Light grey border */
            border-radius: 8px; /* Rounded corners */
            font-size: 1em;
            transition: border-color 0.3s ease;
        }
        .form-group input[type="text"]:focus,
        .form-group input[type="password"]:focus {
            border-color: #007bff; /* Blue on focus */
            outline: none;
        }
        .error-message {
            color: #d32f2f; /* Red for errors */
            margin-bottom: 15px;
            font-size: 0.9em;
        }
        .success-message {
            color: #388e3c; /* Green for success */
            margin-bottom: 15px;
            font-size: 0.9em;
        }
        button {
            background-color: #007bff; /* Primary blue button */
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 8px; /* Rounded corners */
            cursor: pointer;
            font-size: 1.1em;
            width: 100%;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        button:hover {
            background-color: #0056b3; /* Darker blue on hover */
            transform: translateY(-2px);
        }
        button:active {
            transform: translateY(0);
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Admin Login</h2>
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
            <p class="error-message"><%= errorMessage %></p>
        <%
            }
        %>
        <form action="adminLogin" method="post">
            <div class="form-group">
                <label for="adminID">Admin ID:</label>
                <input type="text" id="adminID" name="adminID" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>