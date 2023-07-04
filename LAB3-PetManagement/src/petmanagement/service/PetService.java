/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.service;

import java.util.Date;
import petmanagement.databaseservice.PetDatabaseService;
import petmanagement.entity.PetEntity;
import petmanagement.main.Category;
import petmanagement.main.ModelException;

import petmanagement.model.PetModel;
import petmanagement.utils.Singleton;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class PetService extends PetStoreAbstractService<PetModel, PetEntity> {

    private PetService() {
        this.dataManagementService = Singleton.getInstance(PetDatabaseService.class);
    }

    public PetModel createModel(String id, String name, String description, Date importDate, double price, Category category) throws ModelException {
        if (id != null && name != null) {
            if (filterById(id) == null && Util.validateStringPattern(id, PetModel.ID_PATTERN, true)) {
                return new PetModel(id, name, description, importDate, price, category);
            } else {
                if(id==""){
                    throw new ModelException("ID is blank!");
                }
                throw new ModelException(id + " already exists.");
            }
        }
        return null;
    }

    @Override
    public int loadModels() {
        return loadEntityFromDatabase(PetEntity.class);
    }

    @Override
    public boolean saveModels() {
        return saveEntitytoDatabase(PetEntity.class);
    }

    @Override
    protected PetEntity toEntity(PetModel model) {
        PetEntity entity = null;
        if (model != null) {
            entity = new PetEntity();
            entity.setId(model.getId());
            entity.setName(model.getName());
            entity.setDescription(model.getDescription());
            entity.setCategory(model.getCategory());
            entity.setImportDate(model.getImportDate());
            entity.setPrice(model.getPrice());
        }
        return entity;
    }

    @Override
    protected PetModel toModel(PetEntity entity) {
        PetModel model = null;
        if (entity != null) {
            try {
                model = new PetModel();
                model.setId(entity.getId());
                model.setName(entity.getName());
                model.setDescription(entity.getDescription());
                model.setCategory(entity.getCategory());
                model.setImportDate(entity.getImportDate());
                model.setPrice(entity.getPrice());
            } catch (ModelException ex) {
                System.out.println(">>>>> Error: " + ex.getMessage());
//                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return model;
    }

//    @Override
//    public void refresh() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
