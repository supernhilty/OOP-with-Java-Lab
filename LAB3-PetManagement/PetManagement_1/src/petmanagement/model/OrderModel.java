/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.model;

import java.util.Date;
import java.util.List;
import petmanagement.main.ModelException;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class OrderModel {

    public static String CUSTOMER_NAME_PATTERN = "[a-zA-Z]{5-30}";
    private static String ORDER_ID_FORMAT = "XXXX";
    public static String ORDER_ID_PATTERN = "O\\d{3}";

    private String orderID;
    private Date orderDate;
    private String customer;
    private int petCount;
    private List<OrderLine> orderList;

    public OrderModel() {
    }

    public OrderModel(String orderID, Date orderDate, String customer, int petCount, List<OrderLine> orderList) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customer = customer;
        this.petCount = petCount;
        this.orderList = orderList;
    }

    private boolean validateId(String id) {
        return Util.validateStringPattern(id, ORDER_ID_PATTERN, true);
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) throws ModelException {
        if (!validateId(orderID)) {
            throw new ModelException(orderID + " does not match the pattern.");
        }
        this.orderID = orderID.trim().toUpperCase();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        try {
            this.orderDate = orderDate;
        } catch (Exception e) {
        }
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) throws ModelException {
        if (!Util.validateStringPattern(customer, CUSTOMER_NAME_PATTERN, true)) {
            throw new ModelException(customer + " does not match the pattern.");
        }
        this.customer = customer;
    }

    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int petCount) throws ModelException {
        if (petCount < 0) {
            throw new ModelException("Quantity must be greater than 0");
        }
        this.petCount = petCount;
    }

    public List<OrderLine> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderLine> orderList) {
        if (orderList != null) {
            this.orderList.addAll(orderList);
        }

    }

}
