/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import petmanagement.databaseservice.OrderDatabaseService;
import petmanagement.entity.OrderEntity;
import petmanagement.main.ModelException;
import petmanagement.main.SortType;
import petmanagement.model.OrderLine;
import petmanagement.model.OrderModel;
import petmanagement.utils.Singleton;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class OrderService extends PetStoreAbstractService<YearMonth, OrderModel, OrderEntity> {

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
    protected boolean saveEntitytoDatabase(Class<OrderEntity> clazz) {
        List<OrderEntity> entityList = new ArrayList();
        List<OrderEntity> entityDemo;
        for (Map<String, OrderModel> subMap : this.modelMap.values()) {
            for (OrderModel model : subMap.values()) {
                entityDemo = toEntityList(model);
                if (entityDemo != null) {
                    entityList.addAll(entityDemo);
                }
            }
        }
        return this.dataManagementService.saveEntitys(entityList);

    }

    public List<OrderEntity> toEntityList(OrderModel model) {
        List<OrderEntity> entityList = new ArrayList<>();
        List<OrderLine> list = model.getOrderList();
        if (model != null) {
            for (OrderLine orderline : list) {
                OrderEntity entity = new OrderEntity();
                entity.setOrderID(model.getId());
                entity.setCustomer(model.getCustomer());
                entity.setOrderDate(model.getOrderDate());
                entity.setQuantity(orderline.getQuantity());
                entity.setPid(orderline.getPet().getId());
                entityList.add(entity);
            }

        }
        return entityList;
    }

    public List<OrderLine> filterByIdOrder(String OId, String PId, int quantity) {
        List<OrderModel> listOrder = Singleton.getInstance(OrderService.class).getModelList();
        List<OrderLine> listOrderLine = new ArrayList<>();
        for (OrderModel orderModel : listOrder) {
            if (orderModel.getId().equalsIgnoreCase(OId)) {
                listOrderLine.add(new OrderLine(Singleton.getInstance(PetService.class).filterById(PId), quantity));
            }
        }
        return listOrderLine;
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
                    model.setOrderList(filterByIdOrder(entity.getOrderID(), entity.getPid(), entity.getQuantity()));
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

    @Override
    protected OrderEntity toEntity(OrderModel model) {
        OrderEntity entity = null;
        if (model != null) {
            entity = new OrderEntity();
            entity.setOrderID(model.getId());
            entity.setCustomer(model.getCustomer());
            entity.setOrderDate(model.getOrderDate());
//            entity.setPid(model.getOrderDate());
//            entity.setOrderDate(model.getOrderDate());

        }
        return entity;
    }

    @Override
    protected boolean addModelToMap(OrderModel entity) {
        if (entity != null) {
            YearMonth ym = Util.toYearMonth(entity.getOrderDate());

        }
        return false;
    }

    private class CompareAscByDate implements Comparator<OrderModel> {

        @Override
        public int compare(OrderModel o1, OrderModel o2) {
            return o1.getOrderDate().compareTo(o2.getOrderDate());
        }
    }
    private class CompareDescByDate implements Comparator<OrderModel> {

        @Override
        public int compare(OrderModel o1, OrderModel o2) {
            return o2.getOrderDate().compareTo(o2.getOrderDate());
        }
    }

    public List<OrderModel> getSortedModelList(SortType type) {
        
        int idx = 0;
        for (SortType value : SortType.values()) {
            System.out.println(idx++ + ": " + value);
        }
        idx = Util.inputInteger("Select index", 0, SortType.values().length -1);
        type = SortType.values()[idx];
        
        
        
        switch (type) {
            case SORT_ASC_BY_DATE:
                return this.modelMap.values().stream().flatMap(e -> e.values().stream()).sorted(new CompareAscByDate()).toList();
            case SORT_DESC_BY_DATE:
                return this.modelMap.values().stream().flatMap(e -> e.values().stream()).sorted(new CompareDescByDate()).toList();
            default:
                throw new AssertionError();
        }
    }
}
