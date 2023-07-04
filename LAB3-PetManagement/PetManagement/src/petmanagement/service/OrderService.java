/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.service;

import java.util.Date;
import java.util.List;
import petmanagement.databaseservice.OrderDatabaseService;
import petmanagement.entity.OrderEntity;
import petmanagement.main.ModelException;
import petmanagement.model.OrderLine;
import petmanagement.model.OrderModel;
import petmanagement.utils.Singleton;

/**
 *
 * @author leyen
 */
public class OrderService extends PetStoreAbstractService<OrderModel, OrderEntity> {

    private OrderService() {
        this.dataManagementService = Singleton.getInstance(OrderDatabaseService.class);
    }

    public OrderModel createModel(String orderID, Date orderDate, String customer, List<OrderLine> orderList) throws ModelException {
        if (orderID != null) {
            if (filterById(orderID) == null) {
                return new OrderModel(orderID, orderDate, customer, orderList);//{
//                    @Override
//                    protected boolean validateId(String id) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//                };
            } else {
                throw new ModelException(orderID + " already exists.");
            }
        }
        return null;
    }

    @Override
    public int loadModels() {
        return loadEntityFromDatabase(OrderEntity.class);
    }

    @Override
    public boolean saveModels() {
        return saveEntitytoDatabase(OrderEntity.class);
    }

   

    @Override
    protected OrderEntity toEntity(OrderModel model) {
        OrderEntity entity = null;
        if (model != null) {
            entity = new OrderEntity();
            entity.setOrderID(model.getId());
            entity.setCustomer(model.getCustomer());
            entity.setOrderDate(model.getOrderDate());
            entity.setOrderTotal(model.getTotal());
            entity.setPetCount(model.getPetCount());
        }
        return entity;
    }

    @Override
    protected OrderModel toModel(OrderEntity entity) {
        OrderModel model = null;
        if (entity != null) {
            try {
                model = new OrderModel();
                model.setOrderID(entity.getOrderID());
                model.setCustomer(entity.getCustomer());
                model.setOrderDate(entity.getOrderDate());
                try {
                     model.setOrderList(Singleton.getInstance(OrderService.class).filterById(model.getId()).getOrderList());
                } catch (Exception e) {
                }
            } catch (ModelException ex) {
                System.out.println(">>>>> Error: " + ex.getMessage());
//                Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return model;
    }

}
