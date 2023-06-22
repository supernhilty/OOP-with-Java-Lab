/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.view;

import java.util.Date;
import petmanagement.model.PetAbstractModel;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public abstract class PetView extends PetStoreAbstractView<PetAbstractModel>{
   
    public abstract String getInputID();
    public String getInputDescription(){
        return Util.inputString("Please enter description", true).trim();       
    }
    public String getInputName(){
        return Util.inputString("Please enter name", true).trim();       
    }
    public Date getInputImportDate(){
        return Util.inputDate("Please enter import date");
    }
    public double getInputPrice(){
        return Util.InputDouble("Please enter price", 0.00001, Double.MAX_VALUE);
    }
   
    @Override
    public String toString(PetAbstractModel model) {
        StringBuilder sb = new StringBuilder();
        if (model != null) {            
            sb.append("\n\t\tId: ");
            sb.append(model.getId());        
            sb.append("\n\t\tName: ");
            sb.append(model.getName());        
            sb.append("\n\t\tDescription: ");
            if (model.getDescription() != null) {
                sb.append(model.getDescription());
            }
            sb.append("\n\t\tImport Date: ");
            sb.append(model.getImportDate().toString());
            sb.append("\n\t\tPrice: ");
            sb.append(model.getPrice());
        }
        return sb.toString();
    }
    
}
