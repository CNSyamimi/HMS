<%-- 
    Document   : billPayment
    Created on : May 24, 2025, 4:51:07 PM
    Author     : Ailurophile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Payment</title>
    </head>
    <body>
        <h2>Pay Your Bill</h2>
        <form method="post" action="StudentServlet">
            Bill ID: <input type="text" name="billId" /><br>
            Amount: RM <input type="text" name="amount" /><br>
            <input type="submit" value="Pay Now" />
        </form>
    </body>
</html>
