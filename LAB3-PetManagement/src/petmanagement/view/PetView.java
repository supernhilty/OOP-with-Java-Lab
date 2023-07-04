/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.view;

import java.util.Date;
import petmanagement.main.Category;
import petmanagement.model.PetModel;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class PetView extends PetStoreAbstractView<PetModel> {

    public PetView() {
    }

    public String getInputID() {
        return Util.inputString("Please enter Pet ID with pattern " + PetModel.ID_FORMAT, true).trim();
    }

    public String getInputDescription() {
        return Util.inputString("Please enter description", true).trim();
    }

    public String getInputName() {
        return Util.inputString("Please enter name", true).trim();
    }

    public Date getInputImportDate() {
        return Util.inputDateAllowEmty("Please enter import date");
    }

    public double getInputPrice() {
        return Util.InputDouble("Please enter price", 0.00001, Double.MAX_VALUE);
    }

    //viet lai
    public Category getInputCategory() {
        System.out.println("cat|parrot|dog");
        do {
            try {
                return Category.getCategory(Util.inputString("Input category", true));
            } catch (Exception e) {
                System.out.println("Error input!");
            }
        } while (true);

    }

    @Override
    public String toString(PetModel model) {
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
            sb.append(Util.toString(model.getImportDate()));
            sb.append("\n\t\tPrice: ");
            sb.append(model.getPrice());
            sb.append("\n\t\tCategory: ");
            sb.append(model.getCategory());
        }
        return sb.toString();
    }

}
