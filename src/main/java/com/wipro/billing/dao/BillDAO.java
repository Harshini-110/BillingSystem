package com.wipro.billing.dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.billing.bean.BillBean;
import com.wipro.billing.util.DBUtil;

public class BillDAO {

    public String createRecord(BillBean bean) {
        String status = "FAIL";
        String sql = "INSERT INTO BILL_TABLE "
                   + "(BILLID, CUSTOMERNAME, PRODUCTNAME, BILL_DATE, "
                   + " QUANTITY, PRICE, TOTALAMOUNT, REMARKS) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bean.getBillId());
            ps.setString(2, bean.getCustomerName());
            ps.setString(3, bean.getProductName());
            java.sql.Date sqlDate = new java.sql.Date(bean.getBillDate().getTime());
            ps.setDate(4, sqlDate);
            ps.setInt(5, bean.getQuantity());
            ps.setDouble(6, bean.getPrice());
            ps.setDouble(7, bean.getTotalAmount());
            ps.setString(8, bean.getRemarks());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = bean.getBillId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public BillBean fetchRecord(String customerName, Date billDate) {
        BillBean bean = null;
        String sql = "SELECT * FROM BILL_TABLE "
                + "WHERE UPPER(CUSTOMERNAME) = UPPER(?) "
                + "AND TRUNC(BILL_DATE) = TRUNC(?)";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

        	ps.setString(1, customerName);
        	ps.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(billDate));

            java.sql.Date sqlDate = new java.sql.Date(billDate.getTime());
            ps.setDate(2, sqlDate);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bean = new BillBean();
                    bean.setBillId(rs.getString("BILLID"));
                    bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                    bean.setProductName(rs.getString("PRODUCTNAME"));
                    bean.setBillDate(rs.getDate("BILL_DATE"));
                    bean.setQuantity(rs.getInt("QUANTITY"));
                    bean.setPrice(rs.getDouble("PRICE"));
                    bean.setTotalAmount(rs.getDouble("TOTALAMOUNT"));
                    bean.setRemarks(rs.getString("REMARKS"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public String generateBillID(String customerName, Date billDate) {
        String id = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            DateFormat f = new SimpleDateFormat("yyyyMMdd");
            String datePart = f.format(billDate);

            String namePart = customerName.trim().toUpperCase();
            if (namePart.length() >= 2) {
                namePart = namePart.substring(0, 2);
            } else if (namePart.length() == 1) {
                namePart = namePart + "X";
            } else {
                namePart = "XX";
            }

            con = DBUtil.getDBConnection();
            ps = con.prepareStatement("SELECT BILL_SEQ.NEXTVAL FROM DUAL");
            rs = ps.executeQuery();
            int seq = 0;
            if (rs.next()) {
                seq = rs.getInt(1);
            }
            String seqPart = (seq < 10) ? "0" + seq : String.valueOf(seq);

            id = datePart + namePart + seqPart;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return id;
    }

    public boolean recordExists(String customerName, Date billDate) {
        boolean exists = false;
        String sql = "SELECT 1 FROM BILL_TABLE "
                   + "WHERE CUSTOMERNAME = ? AND BILL_DATE = ?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customerName);
            java.sql.Date sqlDate = new java.sql.Date(billDate.getTime());
            ps.setDate(2, sqlDate);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public List<BillBean> fetchAllRecords() {
        List<BillBean> list = new ArrayList<>();
        String sql = "SELECT * FROM BILL_TB "
                + "WHERE UPPER(TRIM(CUSTOMERNAME)) = UPPER(TRIM(?)) "
                + "AND TRUNC(BILL_DATE) = TO_DATE(?, 'YYYY-MM-DD')";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BillBean bean = new BillBean();
                bean.setBillId(rs.getString("BILLID"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setProductName(rs.getString("PRODUCTNAME"));
                bean.setBillDate(rs.getDate("BILL_DATE"));
                bean.setQuantity(rs.getInt("QUANTITY"));
                bean.setPrice(rs.getDouble("PRICE"));
                bean.setTotalAmount(rs.getDouble("TOTALAMOUNT"));
                bean.setRemarks(rs.getString("REMARKS"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
