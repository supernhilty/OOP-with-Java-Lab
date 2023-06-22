/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.service;

import java.util.Date;
import java.util.List;
import petmanagement.databaseservice.PetDatabaseService;
import petmanagement.entity.PetStoreAbstractEntity;
import petmanagement.entity.PetStoreEntityInterface;
import petmanagement.main.ModelException;
import petmanagement.model.PetAbstractModel;
import petmanagement.utils.Singleton;

/**
 *
 * @author leyen
 */
public abstract class PetService extends PetStoreAbstractService<PetAbstractModel, PetStoreAbstractEntity>{

    private PetService() {
        this.dataManagementService = Singleton.getInstance(PetDatabaseService.class);
    }

 

 

    List<PetAbstractModel> filterByGroupId(String groupId) {
        if (groupId != null && !groupId.isBlank()) {
            return this.modelMap.values().stream().filter(obj -> groupId.equalsIgnoreCase(obj.getGroupId())).toList();
        }
        return null;
    }

    public PetAbstractModel createModel(String id, String name, String description, Date importDate, double price) throws ModelException {
        if (id != null && name != null) {
            if (filterById(id) == null) {
                return new PetAbstractModel(id, name, description, importDate, price) {
                    @Override
                    protected boolean validateId(String id) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
            } else {
                throw new ModelException(id + " already exists.");
            }
        }
        return null;
    }

    @Override
    public int loadModels() {
        return loadEntityFromDatabase(PetStoreAbstractEntity.class);
    }

    @Override
    public boolean saveModels() {
        return saveEntitytoDatabase(PetStoreAbstractEntity.class);
    }

    

    @Override
    protected PetStoreAbstractEntity toEntity(PetAbstractModel model) {
        PetStoreAbstractEntity entity = null;
        if (model != null) {
            entity = new PetStoreAbstractEntity();
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
    protected abstract PetAbstractModel toModel(PetStoreAbstractEntity entity);

    
}
