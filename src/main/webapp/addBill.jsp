<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Bill</title>
</head>
<body>
    <h2>Add Bill Record</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="newRecord">
        Customer Name: <input type="text" name="customerName"><br><br>
        Product Name: <input type="text" name="productName"><br><br>
        Bill Date (yyyy-mm-dd): <input type="text" name="billDate"><br><br>
        Quantity: <input type="text" name="quantity"><br><br>
        Price: <input type="text" name="price"><br><br>
        Remarks: <input type="text" name="remarks"><br><br>
        <input type="submit" value="Add Bill">
    </form>
</body>
</html>
