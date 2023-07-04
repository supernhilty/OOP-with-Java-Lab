/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.entity;

import java.util.Date;

/**
 *
 * @author leyen
 */
public class OrderEntity implements PetStoreEntityInterface{

    private String orderID;
    private Date orderDate;
    private String customer;
    private int petCount;
    private double orderTotal;

    public OrderEntity() {
    }

    public OrderEntity(String orderID, Date orderDate, String customer, int petCount, double orderTotal) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customer = customer;
        this.petCount = petCount;
        this.orderTotal = orderTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int petCount) {
        this.petCount = petCount;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
    
    
}
