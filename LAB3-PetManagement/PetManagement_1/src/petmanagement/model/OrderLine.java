/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.model;

/**
 *
 * @author leyen
 */
class OrderLine {
    private String pId;
    private int quantity;

    public OrderLine(String pId, int quantity) {
        this.pId = pId;
        this.quantity = quantity;
    }

    public String getpId() {
        return pId;
    }

    public int getQuantity() {
        return quantity;
    }
}
