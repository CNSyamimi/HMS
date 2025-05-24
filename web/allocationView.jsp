<%-- 
    Document   : allocationView
    Created on : May 24, 2025, 4:48:53 PM
    Author     : Ailurophile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room Allocation</title>
    </head>
    <body>
        <h2>Room Allocation View</h2>
        <form method="post" action="AllocationServlet">
            Student ID: <input type="text" name="studentId" /><br>
            Room ID: <input type="text" name="roomId" /><br>
            <input type="submit" value="Allocate Room" />
        </form>
    </body>
</html>