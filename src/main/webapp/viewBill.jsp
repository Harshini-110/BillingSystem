<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Bill</title>
</head>
<body>
    <h2>View Bill Record</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="viewRecord">
        Customer Name: <input type="text" name="customerName"><br><br>
        Bill Date (yyyy-mm-dd): <input type="text" name="billDate"><br><br>
        <input type="submit" value="View Bill">
    </form>
</body>
</html>
