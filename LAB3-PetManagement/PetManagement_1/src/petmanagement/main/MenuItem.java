package petmanagement.main;

/**
 * Menu item.
 *
 * @author hasu
 */
public enum MenuItem {    
    EXIT("Quit", UserRole.USER),
    PET_ADD("Add a pet", UserRole.ADMIN),
    PET_FIND("Find a pet", UserRole.USER),
    PET_UPDATE("Update a pet", UserRole.ADMIN),
    PET_DELETE("Delete a pet", UserRole.ADMIN),
    ORDER_ADD("Add an order", UserRole.USER),
    ORDER_LIST("List orders", UserRole.USER),
    ORDER_SORT("Sort orders", UserRole.USER),
    DATA_SAVE("Save data", UserRole.USER),
    DATA_LOAD("Load data", UserRole.USER);
    

    private final String label;
    private final UserRole role;

    public String getLabel() {
        return label;
    }

    public UserRole getRole() {
        return role;
    }

    private MenuItem(String label, UserRole role) {
        this.label = label;
        this.role = role;
    }

}
