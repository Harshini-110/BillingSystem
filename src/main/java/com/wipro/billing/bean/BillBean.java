package com.wipro.billing.bean;

import java.util.Date;

public class BillBean {
    private String billId;
    private String customerName;
    private String productName;
    private Date billDate;
    private int quantity;
    private double price;
    private double totalAmount;
    private String remarks;

    public String getBillId() {
        return billId;
    }
    public void setBillId(String billId) {
        this.billId = billId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Date getBillDate() {
        return billDate;
    }
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
