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
public final class PetModel implements Comparable<PetModel>, PetStoreModelInterface {

    public static final String CATEGORY_FORMAT = "cat|dog|parrot";
    public static final String ID_FORMAT = "PXXX";
    public static final String ID_PATTERN = "P\\d{3}";

    protected String id;
    protected String name;
    protected String description;
    protected Date importDate;
    protected double price;
    protected Category category;

    public PetModel() {
    }

    public PetModel(String id) {
        this.id = id;
    }

    public PetModel(String id, String name, String description, Date importDate, double price, Category category) throws ModelException {
        setId(id);
        setName(name);
        setDescription(description);
        setImportDate(importDate);
        setPrice(price);
        setCategory(category);
    }

    public String getId() {
        return id;
    }

    public final void setId(String id) throws ModelException {
        if (!validateId(id)) {
            throw new ModelException(id + " does not match the pattern " + this.ID_FORMAT + ".");
        }
        this.id = id.trim().toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) throws ModelException {
        if (description != null && !description.isBlank()) {
            this.description = description;
        } else {
            this.description = "";
        }

    }

    public Date getImportDate() {
        return importDate;
    }

    public final void setImportDate(Date importDate) throws ModelException {

        this.importDate = importDate;
    }

    public double getPrice() {
        return price;
    }

    public final void setPrice(double price) throws ModelException {
        if (price < 0) {
            throw new ModelException("Error price input!");
        }
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected boolean validateId(String id) {
        return Util.validateStringPattern(id, this.ID_PATTERN, true);
    }

    @Override
    public int compareTo(PetModel that) {
        return this.id.compareToIgnoreCase(that.id);
    }

  
}
