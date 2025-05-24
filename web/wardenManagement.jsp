<%-- 
    Document   : wardenManagement
    Created on : May 24, 2025, 4:48:16 PM
    Author     : Ailurophile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, hms_model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Warden Management</title>
    </head>
    <body>
        <h2>Warden Management Panel</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <%-- Assume wardens list in request scope --%>
            <%
                List<User> wardens = (List<User>) request.getAttribute("wardens");
                for (User w : wardens) {
            %>
                <tr>
                    <td><%= w.getUserId() %></td>
                    <td><%= w.getName() %></td>
                    <td><%= w.getEmail() %></td>
                    <td><a href="editWarden.jsp?id=<%= w.getUserId() %>">Edit</a></td>
                </tr>
            <% } %>
        </table>
    </body>
</html>