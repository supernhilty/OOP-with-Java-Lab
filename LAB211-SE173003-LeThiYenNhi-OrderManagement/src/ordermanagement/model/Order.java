package ordermanagement.model;

import static java.lang.Double.NaN;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ordermanagement.list.CustomerList;
import ordermanagement.list.IObject;
import ordermanagement.list.OrderList;
import ordermanagement.list.ProductList;
import ordermanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class Order implements IObject, Comparable<Order> {

    public static String ORDER_NAME_PATTERN = "[a-zA-Z]{5-30}";
    private static String ORDER_ID_FORMAT = "DXXX";
    public static String ORDER_ID_PATTERN = "D\\d{3}";
    private static final int ATTRIBUTE_COUNT = 6;

    private String orderID;
    private Customer customer;
//    private String productID;
//    private int orderQuantity;
    private List<OrderLine> productList;
    private Date orderDate;
    private boolean status;

    public Order() {
        this.productList = new ArrayList();
    }

//    public Order(String orderID, String CustomerID, String productID, int orderQuantity, Date orderDate, boolean status) {
//        this.orderID = orderID;
//        this.CustomerID = CustomerID;
//        this.productID = productID;
//        this.orderQuantity = orderQuantity;
//        this.orderDate = orderDate;
//        this.status = status;
//    }
    @Override
    public String getId() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        if (Util.validateString(orderID, Order.ORDER_ID_PATTERN, true)) {
            this.orderID = orderID;
        } else {
            System.out.println("Order ID pattern(" + this.ORDER_ID_FORMAT + ")!");
        }

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
        }
    }
//        if (validateOrderCustomerId(CustomerID)) {
//            this.orderID = orderID;
//        } else {
//            System.out.println("Error...");
//        }
//    }

    public List<OrderLine> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderLine> productList) {
        if (productList != null) {
            this.productList.addAll(productList);
        }
    }

//    public String getProductID() {
//        return productID;
//    }
//
//    public void setProductID(String productID) {
//        if (validateOrderCustomerId(productID)) {
//            this.productID = productID;
//        } else {
//            System.out.println("Error...");
//        }
//    }
//
//    public int getOrderQuantity() {
//        return orderQuantity;
//    }
//
//    public void setOrderQuantity(int orderQuantity) {
//        if (orderQuantity > 0) {
//            this.orderQuantity = orderQuantity;
//        } else {
//            System.out.println("Error...");
//        }
//    }
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        try {
            this.orderDate = orderDate;
        } catch (Exception ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OrderLine orderLine : productList) {
            sb.append(System.lineSeparator());
            sb.append(this.orderID);
            sb.append(Util.SEPARATOR);
            sb.append(this.customer.getId());
            sb.append(Util.SEPARATOR);
            sb.append(orderLine.getpId());
            sb.append(Util.SEPARATOR);
            sb.append(orderLine.getQuantity());
            sb.append(Util.SEPARATOR);
            sb.append(Util.toString(this.orderDate));
            sb.append(Util.SEPARATOR);
            sb.append(this.status);
        }

        return sb.toString().substring(1);
    }

    public int setAttribute(String[] attributes) {
        int idx = 0;
        if (attributes != null && attributes.length >= ATTRIBUTE_COUNT) {

            this.orderID = attributes[idx++].trim();
            String customerID = attributes[idx++].trim();
//            this.customer = CustomerList.getInstance().g
//            this.productID = attributes[idx++].trim();
//            this.orderQuantity = Integer.parseInt(attributes[idx++].trim());
            this.productList.add(new OrderLine(attributes[idx++].trim(), Integer.parseInt(attributes[idx++].trim())));
            try {
                this.orderDate = Util.toDate(attributes[idx++].trim());
            } catch (ParseException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.status = attributes[idx++].trim().equalsIgnoreCase("true");

        }
        return idx;
    }

    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Util.SEPARATOR));
        }
        return 0;
    }

    @Override
    public void output() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(this.orderID);
//        sb.append(Util.SEPARATOR);
//        sb.append(this.CustomerID);
//        sb.append(Util.SEPARATOR);
//        sb.append(this.productID);
//        sb.append(Util.SEPARATOR);
//        sb.append(this.orderQuantity);
//        sb.append(Util.SEPARATOR);
//        sb.append(Util.toString(this.orderDate));
//        sb.append(Util.SEPARATOR);
//        sb.append(this.status);
        System.out.println(toString());
    }

    public void input() {
        this.orderID = inputOrderId();
        this.CustomerID = inputOrderCustomerId();
        String productID = null;
        int orderQuantity;
        do {
            productID = inputOrderProductId();
            if (!productID.isBlank()) {
                orderQuantity = Util.inputInteger("Input quantity", 0, Integer.MAX_VALUE);
                this.productList.add(new OrderLine(productID, orderQuantity));
            }
        } while (!productID.isBlank() || this.productList.isEmpty());
        this.orderDate = Util.inputDate("Input date");
        this.status = Util.inputBoolean("Input status");
    }

    private boolean validateOrderId(String id, boolean checkExists) {
        if (OrderList.getInstance().isOrderIdExists(id)) {
            System.out.println("This Order ID has existed!");
        }
        return Util.validateString(id, Order.ORDER_ID_PATTERN, true)
                && (!checkExists || !OrderList.getInstance().isOrderIdExists(id));
    }

    private boolean validateOrderProductId(String id) {
        if (Util.validateString(id, Product.PRODUCT_ID_PATTERN, true)) {
            boolean check = ProductList.getInstance().isProductIdExists(id);
            if (!check) {
                System.out.println("Product has not already existed!");
            }
            return check;
        }
        return false;
    }

    private boolean validateOrderCustomerId(String id) {
        if (Util.validateString(id, customer.ID_PATTERN, true)) {
            boolean check = CustomerList.getInstance().isCustomerIdExists(id);
            if (!check) {
                System.out.println("Customer has not already existed!");
            }
            return check;
        }
        return false;
    }

    private String inputOrderId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the Order id with the pattern(" + ORDER_ID_FORMAT + ")");

        } while (!validateOrderId(inputId, true));

        return inputId;
    }

    private String inputOrderCustomerId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the Customer id with the pattern(" + customer.ID_FORMAT + ")");
        } while (!validateOrderCustomerId(inputId));

        return inputId;
    }

    private String inputOrderProductId() {
        String inputId;
        do {
//            inputId = Util.inputString("Please enter the Product id with the pattern(" + Product.PRODUCT_ID_FORMAT + ")");
            inputId = Util.inputString("Please enter the Product id with the pattern(" + Product.PRODUCT_ID_FORMAT + ")", true);
        } while (!inputId.isBlank() && !validateOrderProductId(inputId));

        return inputId;
    }

    private String updateOrderId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the Order id with the pattern(" + ORDER_ID_FORMAT + ")", true);

        } while (!validateOrderId(inputId, true) && !inputId.isBlank());

        return inputId;
    }

    private String updateOrderCustomerId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the Customer id with the pattern(" + customer.ID_FORMAT + ")", true);
        } while (!validateOrderCustomerId(inputId) && !inputId.isBlank());

        return inputId;
    }

    private String updateOrderProductId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the Product id with the pattern(" + Product.PRODUCT_ID_FORMAT + ")", true);
        } while (!validateOrderProductId(inputId) && !inputId.isBlank());

        return inputId;
    }

    @Override
    public int compareTo(Order o) {
        return this.orderID.compareToIgnoreCase(o.orderID);
    }

    public void update() {
        String updateID = updateOrderId();
        String updateProductID = updateOrderProductId();
        String updateCustomerID = updateOrderCustomerId();
        int updateQuantity = Util.inputInteger("Input quantity", 0, Integer.MAX_VALUE, true);
        Date updateDate = Util.inputDateAllowEmty("Input date");
        Boolean updatestatus = Util.inputBooleanAllowEmpty("Input status");
        if (!updateID.isBlank()) {
            setOrderID(updateID);
        }
//        if (!updateProductID.isBlank()) {
//            setProductID(updateProductID);
//        }
//        if (!updateCustomerID.isBlank()) {
//            setCustomerID(updateCustomerID);
//        }
//        if (updateQuantity != (int) NaN) {
//            setOrderQuantity(updateQuantity);
//        }
        if (updateDate != null) {
            setOrderDate(updateDate);
        }
        if (null != (Boolean) updatestatus) {
            setStatus((boolean) updatestatus);
        }

    }

}
