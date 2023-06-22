/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.databaseservice;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import petmanagement.entity.OrderEntity;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class OrderDatabaseService extends PetStoreAbstractDatabaseService<OrderEntity>{
    private static final int ORDER_ENTITY_ATTRIBUTE_COUNT = 5;

    private OrderDatabaseService() {
        prepareDataFile(getEntityFilePath(OrderEntity.class));
    }

    @Override
    public List<OrderEntity> loadEntitys(Class<OrderEntity> clazz) {
        return loadEntitysFromFile(getEntityFilePath(clazz));
    }

    @Override
    public boolean saveEntitys(List<OrderEntity> entityLis) {
        if (entityLis != null && !entityLis.isEmpty()) {
            return saveEntitysToFile(getEntityFilePath(entityLis.get(0).getClass()), entityLis);
        }
        return false;
    }

    @Override
    protected String toString(OrderEntity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append(entity.getOrderID());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(Util.toString(entity.getOrderDate()));
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getCustomer());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getPetCount());
        sb.append(PetStoreAbstractDatabaseService.SEPARATOR);
        sb.append(entity.getOrderTotal());
        return sb.toString();
    }

    @Override
    protected OrderEntity toEntity(String entityString) {
        OrderEntity orderEntity = null;
        if (entityString != null) {
            String[] attributes = entityString.split(PetStoreAbstractDatabaseService.SEPARATOR, -1);
            if (attributes.length >= ORDER_ENTITY_ATTRIBUTE_COUNT) {
                orderEntity = new OrderEntity();
                orderEntity.setOrderID(attributes[0]);
                try {
                    orderEntity.setOrderDate(Util.toDate(attributes[1]));
                } catch (ParseException ex) {
                    Logger.getLogger(OrderDatabaseService.class.getName()).log(Level.SEVERE, null, ex);
                }
                orderEntity.setCustomer(attributes[2]);
                orderEntity.setPetCount(Integer.parseInt(attributes[3]));
                orderEntity.setOrderTotal(Double.parseDouble(attributes[4]));
            }
        }
        return orderEntity;
    }
}
