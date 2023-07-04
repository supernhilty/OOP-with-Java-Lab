package ordermanagement.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ordermanagement.main.OrderManagement;
import ordermanagement.model.Order;
import ordermanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class OrderList extends ObjectList<Order> {

    private static OrderList instance = new OrderList("Order.dat");

    private OrderList(String filePath) {
        super(filePath);
    }

    private OrderList() {

    }

    public static OrderList getInstance() {
        return instance;
    }

    public Order getOrderById(String id) {
        if (id != null && !id.isBlank()) {
            for (Order order : this) {
                if (id.equalsIgnoreCase(order.getId())) {
                    return order;
                }
            }
        }
        return null;
    }

    public boolean isOrderIdExists(String id) {
        if (id != null && !id.isBlank()) {
            Order obj = new Order();
            obj.setOrderID(id);
            return this.contains(obj);
        }
        return false;
    }

    public void showAscByName() {
        if (this.isEmpty()) {
            System.out.println("The order is empty. Nothing to print!");
            return;
        }
        Comparator nameBalance = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                CustomerList customerList = CustomerList.getInstance();
                return customerList.filter(o1.getCustomerID()).getName().compareToIgnoreCase(customerList.filter(o2.getCustomerID()).getName());
            }
        };
        Collections.sort(this, nameBalance);
        System.out.println("------------------------------------");
        System.out.println("Orders List in ascending order of Customer name");
        this.show();
    }

    public void showPending() {

        if (this.isEmpty()) {
            System.out.println("The order is empty. Nothing to print!");
            return;
        }

        List<Order> orderPendingList = new ArrayList<>();
        for (Order order : this) {
            if (!order.getStatus()) {
                orderPendingList.add(order);
            }
        }
        for (Order order : orderPendingList) {
            order.output();
        }
    }

    public Order addOrder() {
        Order order = new Order();
        order.input();
        this.add(order);
        System.out.println("Successfully!");
        return order;
    }

    public Order updateOrder(String id) {
        if (filter(id) != null) {
            Order order = filter(id);
            order.output();
            System.out.println("Press ENTER if you want to keep old value!");
            order.update();
            System.out.println("Successfully!");
            System.out.println("After updated:");
            order.output();
            return order;
        } else {
            System.out.println("No result!");
        }

        return null;
    }

    private Order deleteOrder(String id) {

        Order order = filter(id);
        if (order != null) {
            Order rs = order;
            this.remove(order);
            return rs;
        }
        return null;
    }

    public void deleteOrder() {
        String id = Util.inputString("Input id Order want to delete");
        if (deleteOrder(id) != null) {
            System.out.println("Deleted!");
        } else {
            System.out.println("Id Order has not existed!");
        }
    }

    public void saveTofile() {
        this.save();
        System.out.println("Successfully!");
    }

    @Override
    public Order filter(String id) {
        List<Order> list = OrderManagement.getInstance().getOrderList();
        if (!id.isBlank() && Util.validateString(id, Order.ORDER_ID_PATTERN, true)) {
            for (Order order : list) {
                if (id.equalsIgnoreCase(order.getId())) {
                    return order;
                }
            }
        }
        return null;
    }

    @Override
    protected Order parseString(String stringObject) {
        Order obj = new Order();
        obj.parseString(stringObject);

        Order existsOrder = getOrderById(obj.getId()) ;
        if (existsOrder != null) {
            existsOrder.setProductList(obj.getProductList());
            return null;
        }
                
        return obj;
    }

}
