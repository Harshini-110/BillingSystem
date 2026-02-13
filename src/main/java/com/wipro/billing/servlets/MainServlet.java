package com.wipro.billing.servlets;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.billing.bean.BillBean;
import com.wipro.billing.service.Administrator;
import javax.servlet.annotation.WebServlet;

@WebServlet("/MainServlet")


public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Administrator admin = new Administrator();

    public String addRecord(HttpServletRequest request) {
        String customerName = request.getParameter("customerName");
        String productName = request.getParameter("productName");
        String billDateStr = request.getParameter("billDate");
        String quantityStr = request.getParameter("quantity");
        String priceStr = request.getParameter("price");
        String remarks = request.getParameter("remarks");

        BillBean bean = new BillBean();
        try {
            bean.setCustomerName(customerName);
            bean.setProductName(productName);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date billDate = sdf.parse(billDateStr);
            bean.setBillDate(billDate);

            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            bean.setQuantity(quantity);
            bean.setPrice(price);
            bean.setRemarks(remarks);
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
            return "FAIL";
        }

        return admin.addRecord(bean);
    }

    public BillBean viewRecord(HttpServletRequest request) {
        String customerName = request.getParameter("customerName");
        String billDateStr = request.getParameter("billDate");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date billDate = sdf.parse(billDateStr);
            return admin.viewRecord(customerName, billDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BillBean> viewAllRecords(HttpServletRequest req) {
        return admin.viewAllRecords();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String operation = req.getParameter("operation");

        if ("newRecord".equals(operation)) {
            String status = addRecord(req);
            if ("FAIL".equals(status) || status == null
                    || status.startsWith("INVALID")
                    || "ALREADY EXISTS".equals(status)) {
                resp.sendRedirect("error.html");
            } else {
                resp.sendRedirect("success.html");
            }

        } else if ("viewRecord".equals(operation)) {
            BillBean bean = viewRecord(req);
            if (bean == null) {
                req.setAttribute("message",
                        "No matching records exists! Please try again!");
            } else {
                req.setAttribute("bean", bean);
            }
            RequestDispatcher rd = req.getRequestDispatcher("displayBill.jsp");
            rd.forward(req, resp);

        } else if ("viewAllRecords".equals(operation)) {
            List<BillBean> list = viewAllRecords(req);
            if (list == null || list.isEmpty()) {
                req.setAttribute("message", "No records available!");
            } else {
                req.setAttribute("list", list);
            }
            RequestDispatcher rd = req.getRequestDispatcher("displayAllBills.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
