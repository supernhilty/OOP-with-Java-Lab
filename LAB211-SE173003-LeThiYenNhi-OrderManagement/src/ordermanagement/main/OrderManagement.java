package ordermanagement.main;

import ordermanagement.list.OrderList;
import ordermanagement.list.ProductList;
import ordermanagement.list.CustomerList;
import ordermanagement.services.UserManagement;
import ordermanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class OrderManagement {

    private static final OrderManagement instance = new OrderManagement();

    private final CustomerList customerList;
    private final OrderList orderList;
    private final ProductList productList;

    public static OrderManagement getInstance() {
        return instance;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public ProductList getProductList() {
        return productList;
    }

    public OrderManagement() {
        this.customerList = CustomerList.getInstance();
        this.orderList = OrderList.getInstance();
        this.productList = ProductList.getInstance();
    }

    private void loadData() {
        this.customerList.load();
        this.productList.load();
        this.orderList.load();
    }

    private void showAllProduct() {
        productList.show();
    }

    private void showAllOrderAscByName() {
        orderList.showAscByName();
    }

    private void showAllOrderPending() {
        orderList.showPending();
    }

    private void addNewOrder() {
        if (UserManagement.getInstance().getCurrentUser().checkRole(UserRole.ADMIN)) {
            System.out.println("Add new Order");
            orderList.addOrder();
            orderList.save();
        }
    }

    private void updateOrder() {
        orderList.updateOrder(Util.inputString("Input Order ID you want to update"));
        orderList.save();
    }

    private void deleteOrder() {
        orderList.deleteOrder();
        orderList.save();
    }

    private void saveOrder() {
        orderList.saveTofile();
    }

    private void showAllCustomer() {
        customerList.showAll();
    }

    private void addNewCustomer() {
        customerList.addCustomer();
        customerList.save();
    }

    private void searchCustomer() {
        customerList.showFilter(Util.inputString("Input Customer ID"));
    }

    private void updateCustomer() {
        customerList.updateCustomer(Util.inputString("Input Customer ID you want to update"));
        customerList.save();
    }

    private void saveCustomer() {
        customerList.saveToFile();
    }

    private void run() {
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case PRODUCT_SHOW_ALL -> {
                    showAllProduct();
                }

                case ORDER_SHOW_ALL_ASC_BY_NAME -> {
                    showAllOrderAscByName();
                }
                case ORDER_SHOW_PENDING -> {
                    showAllOrderPending();
                }
                case ORDER_ADD_NEW -> {
                    addNewOrder();
                }
                case ORDER_UPDATE -> {
                    updateOrder();
                }
                case ORDER_DELETE -> {
                    deleteOrder();
                }
                case ORDER_SAVE_TO_FILE -> {
                    saveOrder();
                }

                case CUSTOMER_SHOW_ALL -> {
                    showAllCustomer();
                }
                case CUSTOMER_FILTER_BY_ID -> {
                    searchCustomer();
                }
                case CUSTOMER_ADD_NEW -> {
                    addNewCustomer();
                }
                case CUSTOMER_UPDATE -> {
                    updateCustomer();
                }
                case CUSTOMER_SAVE_TO_FILE -> {
                    saveCustomer();
                }

                case EXIT -> {
                    System.out.println("Exited!");
                }
                default -> {
                    System.out.println("???");
                }
            }
        } while (userChoice != MenuItem.EXIT);
    }

    private void start() {
        System.out.println("Order management");
        if (UserManagement.getInstance().login()) {
            UserManagement.getInstance().getCurrentUser().output();
            loadData();
            run();
        } else {
            System.out.println("Login failed!");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new OrderManagement().start();
    }

}
