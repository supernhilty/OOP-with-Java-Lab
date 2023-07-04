/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.model;

import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class OrderLine {
    private PetModel pet;
    private int quantity;

    public OrderLine(PetModel pet, int quantity) {
        this.pet = pet;
        this.quantity = quantity;
    }

    public OrderLine() {
    }


    public PetModel getPet() {
        return pet;
    }

    public void setPet(PetModel pet) {
        this.pet = pet;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
   
    
    
}
