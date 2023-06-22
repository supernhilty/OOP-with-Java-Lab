/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.model;

import java.util.Date;
import petmanagement.main.Category;
import petmanagement.main.ModelException;

/**
 *
 * @author leyen
 */
public abstract class PetAbstractModel implements Comparable<PetAbstractModel>, PetStoreModelInterface {

    protected String id;
    protected String name;
    protected String description;
    protected Date importDate;
    protected double price;
    protected Category category;

    public PetAbstractModel() {
    }

    public PetAbstractModel(String id, String name, String description, Date importDate, double price) throws ModelException {
        setId(id);
        setName(name);
        setDescription(description);
        setImportDate(importDate);
        setPrice(price);
        //setCategory(category);
    }

    public String getId() {
        return id;
    }

    public final void setId(String id) throws ModelException {
        if (!validateId(id)) {
            throw new ModelException(id + " does not match the pattern.");
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
        if (description == null) {
            throw new ModelException("Import date is blank.");
        }

        this.importDate = importDate;
    }

    public double getPrice() {
        return price;
    }

    public final void setPrice(double price) throws ModelException {
        if ((Double) price == null && price < 0) {
            throw new ModelException("Error price input!");
        }
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

    public boolean isValid() {
        return validateId(this.id);
    }

    protected abstract boolean validateId(String id);    

    @Override
    public int compareTo(PetAbstractModel that) {
        return this.id.compareToIgnoreCase(that.id);
    }

}
