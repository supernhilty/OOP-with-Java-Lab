/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.controller;

import java.util.Date;
import petmanagement.entity.PetEntity;
import petmanagement.main.Category;
import petmanagement.main.ModelException;
import petmanagement.model.PetModel;
import petmanagement.service.PetService;
import petmanagement.utils.Singleton;
import petmanagement.view.PetView;

/**
 *
 * @author leyen
 */
public class PetController extends PetStoreAbstractController<PetService, PetView, PetModel> {

    private PetController() {
        this.service = Singleton.getInstance(PetService.class);
        this.view = Singleton.getInstance(PetView.class);
    }

    @Override
    public PetModel createNewModel() {
        String id = this.view.getInputID();
        String name = this.view.getInputName();
        String description = this.view.getInputDescription();
        Date inportDate = this.view.getInputImportDate();
        double price = this.view.getInputPrice();
        Category category = this.view.getInputCategory();
        try {
            return this.service.createModel(id, name, description, inportDate, price, category);
        } catch (ModelException ex) {
            System.out.println("Error: " + ex.getMessage());
//            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePet(String id) throws ModelException {
        PetModel updateModel = Singleton.getInstance(PetService.class).filterById(id);
        if (updateModel != null) {
            String name = this.view.getInputName();
            String description = this.view.getInputDescription();
            Date inportDate = this.view.getInputImportDate();
            double price = this.view.getInputPrice();
            Category category = this.view.getInputCategory();
            if (!name.isBlank()) {
                updateModel.setName(name);
            }
            if (!description.isBlank()) {
                updateModel.setDescription(description);
            }
            if (inportDate != null) {
                updateModel.setImportDate(inportDate);
            }
            if ((Double) price != null) {
                updateModel.setPrice(price);
            }
            if (category != null) {
                updateModel.setCategory(category);
            }
            PetModel update = this.service.update(updateModel);
            this.service.toString();
            System.out.println("Sucessfully!");
        } else {
            System.out.println("Fail!");
        }

    }

    public void deletePet(String id) {
        PetModel delete = this.service.delete(Singleton.getInstance(PetService.class).filterById(id));
        if(delete!=null){
            System.out.println("Sucessfully!");
        }else{
            System.out.println("Fail!");
        }
        
    }
        
    public void filterByPetId(String id){
        System.out.println(this.view.toString(filterById(id)));;
       
    }

}
