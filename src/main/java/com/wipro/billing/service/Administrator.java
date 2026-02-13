package com.wipro.billing.service;

import java.util.Date;
import java.util.List;

import com.wipro.billing.bean.BillBean;
import com.wipro.billing.dao.BillDAO;
import com.wipro.billing.util.InvalidInputException;

public class Administrator {

    private BillDAO dao = new BillDAO();

    public String addRecord(BillBean bean) {
        try {
            if (bean == null || bean.getCustomerName() == null
                    || bean.getBillDate() == null) {
                throw new InvalidInputException();
            }

            String customerName = bean.getCustomerName().trim();
            if (customerName.length() < 2) {
                return "INVALID CUSTOMER NAME";
            }

            if (bean.getQuantity() < 1 || bean.getPrice() <= 0) {
                return "INVALID BILL DETAILS";
            }

            if (dao.recordExists(customerName, bean.getBillDate())) {
                return "ALREADY EXISTS";
            }

            String billId = dao.generateBillID(customerName, bean.getBillDate());
            bean.setBillId(billId);

            double total = bean.getQuantity() * bean.getPrice();
            bean.setTotalAmount(total);

            String status = dao.createRecord(bean);
            if (!"FAIL".equals(status)) {
                return status;
            } else {
                return "FAIL";
            }
        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    public BillBean viewRecord(String customerName, Date billDate) {
        if (customerName == null || billDate == null) {
            return null;
        }
        return dao.fetchRecord(customerName.trim(), billDate);
    }

    public List<BillBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
