<%-- 
    Document   : visitorForm
    Created on : May 24, 2025, 4:51:31 PM
    Author     : Ailurophile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visitor Registration</title>
    </head>
    <body>
        <h2>Visitor Form</h2>
        <form method="post" action="StudentServlet">
            Name: <input type="text" name="visitorName" /><br>
            Relationship: <input type="text" name="relationship" /><br>
            Visit Date: <input type="date" name="visitDate" /><br>
            <input type="hidden" name="action" value="submitVisitor" />
            <input type="submit" value="Submit Visitor Info" />
        </form>
    </body>
</html>