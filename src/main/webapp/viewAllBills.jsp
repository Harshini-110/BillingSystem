<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>View All Bills</title>
</head>
<body>
    <h2>View All Bill Records</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="viewAllRecords">
        <input type="submit" value="View All">
    </form>
</body>
</html>
