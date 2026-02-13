<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.wipro.billing.bean.BillBean"%>
<!DOCTYPE html>
<html>
<head>
    <title>Bill Details</title>
</head>
<body>
<%
    String msg = (String) request.getAttribute("message");
    if (msg != null) {
%>
    <h3><%= msg %></h3>
<%
    } else {
        BillBean bean = (BillBean) request.getAttribute("bean");
%>
    <h2>Bill Details</h2>
    <table border="1" cellpadding="5">
        <tr><th>Bill ID</th><td><%= bean.getBillId() %></td></tr>
        <tr><th>Customer</th><td><%= bean.getCustomerName() %></td></tr>
        <tr><th>Product</th><td><%= bean.getProductName() %></td></tr>
        <tr><th>Date</th><td><%= bean.getBillDate() %></td></tr>
        <tr><th>Quantity</th><td><%= bean.getQuantity() %></td></tr>
        <tr><th>Price</th><td><%= bean.getPrice() %></td></tr>
        <tr><th>Total</th><td><%= bean.getTotalAmount() %></td></tr>
        <tr><th>Remarks</th><td><%= bean.getRemarks() %></td></tr>
    </table>
<%
    }
%>
</body>
</html>
