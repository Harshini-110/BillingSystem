<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,com.wipro.billing.bean.BillBean"%>
<!DOCTYPE html>
<html>
<head>
    <title>All Bills</title>
</head>
<body>
<%
    String msg = (String) request.getAttribute("message");
    if (msg != null) {
%>
    <h3><%= msg %></h3>
<%
    } else {
        List<BillBean> list = (List<BillBean>) request.getAttribute("list");
%>
    <h2>All Bill Records</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Bill ID</th>
            <th>Customer</th>
            <th>Product</th>
            <th>Date</th>
            <th>Qty</th>
            <th>Price</th>
            <th>Total</th>
            <th>Remarks</th>
        </tr>
<%
        for (BillBean b : list) {
%>
        <tr>
            <td><%= b.getBillId() %></td>
            <td><%= b.getCustomerName() %></td>
            <td><%= b.getProductName() %></td>
            <td><%= b.getBillDate() %></td>
            <td><%= b.getQuantity() %></td>
            <td><%= b.getPrice() %></td>
            <td><%= b.getTotalAmount() %></td>
            <td><%= b.getRemarks() %></td>
        </tr>
<%
        }
%>
    </table>
<%
    }
%>
</body>
</html>
