package ordermanagement.main;

/**
 * 
 * @author leyen
 */

public enum MenuItem {
    MAIN_OPTIONS("Order management", UserRole.USER),
    EXIT("Exit", UserRole.USER),
    BACK("Back", UserRole.USER),
    
    PRODUCT("Product", UserRole.USER),
    PRODUCT_SHOW_ALL("List all Products", UserRole.USER),    
    
    ORDER("Order", UserRole.USER),   
    ORDER_SHOW_ALL_ASC_BY_NAME("List all Orders in ascending order of Customer name", UserRole.USER),
    ORDER_SHOW_PENDING("List all pending Orders", UserRole.USER),
    ORDER_ADD_NEW("Add an Order", UserRole.ADMIN),
    ORDER_UPDATE("Update", UserRole.ADMIN),
    ORDER_DELETE("Delete", UserRole.ADMIN),
    ORDER_SAVE_TO_FILE("Save Orders to file", UserRole.ADMIN),
    
    CUSTOMER("Customer", UserRole.USER),
    CUSTOMER_SHOW_ALL("Show all", UserRole.USER),
    CUSTOMER_FILTER_BY_ID("Filter by id", UserRole.USER),    
    CUSTOMER_ADD_NEW("Add new", UserRole.ADMIN),
    CUSTOMER_UPDATE("Update", UserRole.ADMIN),
    CUSTOMER_SAVE_TO_FILE("Save Customers to the file", UserRole.ADMIN);
    
    

    private final UserRole role;
    private final String label;

    public UserRole getRole() {
        return role;
    }

    public String getLabel() {
        return label;
    }

    private MenuItem(String label, UserRole role) {
        this.role = role;
        this.label = label;
    }

}
