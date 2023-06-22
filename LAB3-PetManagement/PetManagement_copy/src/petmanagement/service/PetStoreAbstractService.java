package petmanagement.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import petmanagement.databaseservice.PetStoreDatabaseServiceInterface;
import petmanagement.entity.PetStoreEntityInterface;
import petmanagement.main.ModelException;
import petmanagement.model.OrderModel;
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
            
            ModelClass update =  modelMap.put(object.getId(), object);
            saveModels();
            return update;
        }
        return null;
    }

    @Override
    public ModelClass delete(ModelClass object) {
        try {
            ModelClass delete = modelMap.remove(object.getId());
            saveModels();
            return delete;
        } catch (Exception e) {
        }
        return null;
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

// public void sort() throws ModelException {
////        if (sortCol.equalsIgnoreCase("date")) {
////            Comparator<OrderModel> comparator = (OrderModel obj1, OrderModel obj2) -> obj1.getOrderDate().compareTo(obj2.getOrderDate());
////
////        } else if (sortCol.equalsIgnoreCase("customer")) {
////            Comparator<OrderModel> comparator = (OrderModel obj1, OrderModel obj2) -> obj1.getCustomer().compareTo(obj2.getCustomer());
////
////        } else{  //(sortCol.equalsIgnoreCase("id")) {
////            Comparator<OrderModel> comparator = (OrderModel obj1, OrderModel obj2) -> obj1.getId().compareTo(obj2.getId());}
////
//////        } else {
//////            throw new ModelException("Wrong input column!");
//////        }
////       
////        // Create a list from elements of HashMap
//       List<Map.Entry<String, ModelClass> > list =
//               new LinkedList<Map.Entry<String, OrderModel> >(this.modelMap.entrySet());
//        Collections.sort(list, new Comparator<OrderModel>() {
//            public int compare(OrderModel o, OrderModel o2) {
//                return (o).getCustomer().compareTo(o2.getCustomer());
//            }
//        });
//    }
// 
//        // Sort the list
//        Collections.sort(list, new Comparator<Map.Entry<String, ModelClass> >() {
//            public int compare(Map.Entry<String, ModelClass> o1,
//                               Map.Entry<String, ModelClass> o2)
//            {
//                return comparator;
//            }
//        });
//         
//        // put data from sorted list to hashmap
//        HashMap<String, ModelClass> temp = new LinkedHashMap<String, ModelClass>();
//        for (Map.Entry<String, ModelClass> aa : list) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
//        return temp;
//        }
 //   }
    
    protected abstract EntityClass toEntity(ModelClass model);

    protected abstract ModelClass toModel(EntityClass entity);
}
