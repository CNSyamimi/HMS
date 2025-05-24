<%-- 
    Document   : wardenMaintenance
    Created on : May 24, 2025, 5:11:01 PM
    Author     : Ailurophile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maintenance Requests</title>
    </head>
    <body>
        <h2>Maintenance Management</h2>
        <table border="1">
            <tr><th>Request ID</th><th>Room</th><th>Issue</th><th>Status</th></tr>
            <tr><td>1</td><td>101</td><td>Broken fan</td><td>Pending</td></tr>
        </table>
        <form method="post" action="WardenServlet">
            Update Request ID: <input type="text" name="requestId" />
            Status: <select name="status">
                <option>Pending</option>
                <option>Completed</option>
            </select>
            <input type="hidden" name="action" value="updateMaintenance" />
            <input type="submit" value="Update" />
        </form>
    </body>
</html>