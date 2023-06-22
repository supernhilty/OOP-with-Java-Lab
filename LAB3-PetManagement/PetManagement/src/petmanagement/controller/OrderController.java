/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petmanagement.controller;

import java.util.Date;
import java.util.List;
import petmanagement.main.ModelException;
import petmanagement.model.OrderLine;
import petmanagement.model.OrderModel;
import petmanagement.service.OrderService;
import petmanagement.utils.Singleton;
import petmanagement.view.OrderView;

/**
 *
 * @author leyen
 */
public class OrderController extends PetStoreAbstractController<OrderService, OrderView, OrderModel> {

    private OrderController() {
        this.service = Singleton.getInstance(OrderService.class);
        this.view = Singleton.getInstance(OrderView.class);
    }

    @Override
    public OrderModel createNewModel() {
        String id = this.view.getInputID();
        String customer = this.view.getInputCustomer();
        Date orderDate = this.view.getInputOrderDate();
        List<OrderLine> orderList = this.view.getInputPetOrder();
        try {
            return this.service.createModel(id, orderDate, customer, orderList);
        } catch (ModelException ex) {
            System.out.println(">>>>> Error: " + ex.getMessage());
//            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public void sort(String sortCol) throws ModelException {
//        if (sortCol.equalsIgnoreCase("date")) {
//            Comparator<OrderModel> comparator = (OrderModel obj1, OrderModel obj2) -> obj1.getOrderDate().compareTo(obj2.getOrderDate());
//
//        } else if (sortCol.equalsIgnoreCase("customer")) {
//            Comparator<OrderModel> comparator = (OrderModel obj1, OrderModel obj2) -> obj1.getCustomer().compareTo(obj2.getCustomer());
//
//        } else if (sortCol.equalsIgnoreCase("id")) {
//            Comparator<OrderModel> comparator = (OrderModel obj1, OrderModel obj2) -> obj1.getId().compareTo(obj2.getId());
//
//        } else {
//            throw new ModelException("Wrong input column!");
//        }
//
//        LinkedHashMap<Integer, OrderModel> sortedMap = this.service.modelMap.entrySet().stream()
//                .sorted(Map.Entry.<Integer, ModelClass>comparingByValue(comparator))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
////printing the sorted hashmap 
//        Set set = sortedMap.entrySet();
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry me2 = (Map.Entry) iterator.next();
//            System.out.print(me2.getKey() + ": ");
//            System.out.println(this.modelMap.get(me2.getKey()).firstName + " " + this.modelMap.get(me2.getKey()).lastName);
//        }

//        if (object != null && !this.modelMap.containsKey(object.getId())) {
//            this.modelMap.put(object.getId(), object);
//            return saveModels();
//        }
//        return false;
 //   }

    public void sortOrder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
