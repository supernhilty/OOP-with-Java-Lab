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
    private int quantity;
    private String pid;

    public OrderEntity() {
    }

    public OrderEntity(String orderID, Date orderDate, String customer, int quantity, String pid) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customer = customer;
        this.quantity = quantity;
        this.pid = pid;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    
    
}
