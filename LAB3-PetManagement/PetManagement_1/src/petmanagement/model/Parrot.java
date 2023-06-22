/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.model;

import java.util.Date;
import petmanagement.main.Category;
import petmanagement.main.ModelException;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class Parrot extends PetAbstractModel {

    public static final String ID_FORMAT = "Pxxx";
    private static final String ID_PATTERN = "P\\d{3}";
    public static final Category CATEGORY = Category.Parrot;

    public Parrot() {
    }

    public Parrot(String id, String name, String description, Date importDate, double price) throws ModelException {
        super(id, name, description, importDate, price);
        setCategory(CATEGORY);
    }

    @Override
    protected boolean validateId(String id) {
        return Util.validateStringPattern(id, Parrot.ID_PATTERN, true);
    }



    

}
