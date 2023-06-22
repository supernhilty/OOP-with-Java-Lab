package ordermanagement.list;

import ordermanagement.main.OrderManagement;
import ordermanagement.model.Customer;
import ordermanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class CustomerList extends ObjectList<Customer> {

    private static CustomerList instance = new CustomerList("Customer.dat");

    public static CustomerList getInstance() {
        return instance;
    }

    private CustomerList() {
    }

    private CustomerList(String filePath) {
        super(filePath);
    }

    public boolean isCustomerIdExists(String id) {
        if (id != null && !id.isBlank()) {
            Customer obj = new Customer();
            obj.setId(id);
            return this.contains(obj);
        }
        return false;
    }

    public Customer addCustomer() {
        Customer cus = new Customer();
        cus.input();
        this.add(cus);
        System.out.println("Successfully!");
        return cus;
    }

    public Customer updateCustomer(String id) {
        if (filter(id) != null) {
            Customer cus = filter(id);
            cus.output();
            System.out.println("Press ENTER if you want to keep old value!");
            cus.update();
            System.out.println("Successfully!");
            System.out.println("After updated:");
            cus.output();
            return cus;
        }else{
            System.out.println("No result!");
        }
        
        return null;
    }

    public void saveToFile() {
        this.save();
        System.out.println("Successfully!");
    }

    public void showAll() {
        OrderManagement.getInstance().getCustomerList().show();
    }

    @Override
    public Customer filter(String id) {

        if (id != null && Util.validateString(id, Customer.ID_PATTERN, true)) {
            for (Customer cus : this) {
                if (id.equalsIgnoreCase(cus.getId())) {
                    return cus;
                }
            }
        }
        return null;
    }

    @Override
    protected Customer parseString(String stringObject) {
        Customer c = new Customer();
        c.parseString(stringObject);
        return c;
    }

}
