package petmanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import petmanagement.databaseservice.PetStoreDatabaseServiceInterface;
import petmanagement.entity.PetStoreEntityInterface;
import petmanagement.model.PetStoreModelInterface;


/**
 * Abstract class PetStoreAbstractService.
 *
 * @author hasu
 * @param <ModelClass>
 * @param <EntityClass>
 */
public abstract class PetStoreAbstractService<
        ModelClass extends PetStoreModelInterface, EntityClass extends PetStoreEntityInterface>
        implements PetStoreServiceInterface<ModelClass> {

    protected final Map<String, ModelClass> modelMap;
    protected PetStoreDatabaseServiceInterface dataManagementService;

    protected PetStoreAbstractService() {
        this.modelMap = new HashMap();
    }

    @Override
    public boolean add(ModelClass object) {
        if (object != null && !this.modelMap.containsKey(object.getId())) {
            this.modelMap.put(object.getId(), object);
            return saveModels();
        }
        return false;
    }

    @Override
    public ModelClass update(ModelClass object) {
        if (object != null && modelMap.containsKey(object.getId())) {
            return modelMap.put(object.getId(), object);
        }
        return null;
    }

    @Override
    public ModelClass delete(ModelClass object) {
        return modelMap.remove(object.getId());
    }

    @Override
    public List<ModelClass> getModelList() {
        return this.modelMap.values().stream().toList();
    }

    @Override
    public ModelClass filterById(String id) {
        if (id != null && !id.isBlank()) {
            return this.modelMap.get(id.toUpperCase());
        }
        return null;
    }

    @Override
    public List<ModelClass> filterByName(String name) {
        if (name != null && !name.isBlank()) {
            return this.modelMap.values().stream().filter(obj -> name.equalsIgnoreCase(obj.getName())).toList();
        }
        return null;
    }

    protected int loadEntityFromDatabase(Class<EntityClass> clazz) {
        List<EntityClass> entityList = this.dataManagementService.loadEntitys(clazz);
        this.modelMap.clear();
        ModelClass model;
        for (EntityClass entity : entityList) {
            model = toModel(entity);
            if (model != null) {
                this.modelMap.put(model.getId(), model);
            }
        }
        return this.modelMap.size();
    }

    protected boolean saveEntitytoDatabase(Class<EntityClass> clazz) {
        List<EntityClass> entityList = new ArrayList();
        EntityClass entity;
        for (ModelClass model : this.modelMap.values()) {
            entity = toEntity(model);
            if (entity != null) {
                entityList.add(entity);
            }
        }
        return this.dataManagementService.saveEntitys(entityList);

    }

    protected abstract EntityClass toEntity(ModelClass model);

    protected abstract ModelClass toModel(EntityClass entity);
}
