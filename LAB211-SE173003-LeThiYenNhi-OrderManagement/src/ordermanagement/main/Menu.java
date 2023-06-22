package ordermanagement.main;

import ordermanagement.model.User;
import ordermanagement.services.UserManagement;
import ordermanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class Menu {

    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.PRODUCT,
        MenuItem.ORDER,
        MenuItem.CUSTOMER
    };

    private final MenuItem[] productOptions = {
        MenuItem.BACK,
        MenuItem.PRODUCT_SHOW_ALL
    };

    private final MenuItem[] customerOptions = {
        MenuItem.BACK,
        MenuItem.CUSTOMER_ADD_NEW,
        MenuItem.CUSTOMER_SHOW_ALL,
        MenuItem.CUSTOMER_FILTER_BY_ID,
        MenuItem.CUSTOMER_UPDATE,
        MenuItem.CUSTOMER_SAVE_TO_FILE
    };

    private final MenuItem[] orderOptions = {
        MenuItem.BACK,
        MenuItem.ORDER_ADD_NEW,
        MenuItem.ORDER_UPDATE,
        MenuItem.ORDER_SHOW_ALL_ASC_BY_NAME,
        MenuItem.ORDER_SHOW_PENDING,
        MenuItem.ORDER_DELETE,
        MenuItem.ORDER_SAVE_TO_FILE
    };

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(MenuItem.MAIN_OPTIONS);
            }
            if (primaryOption != MenuItem.EXIT && !isRepeatAction()) {
                subOption = getChoice(primaryOption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Order management:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);
        User currentUser = UserManagement.getInstance().getCurrentUser();
        for (MenuItem item : optionList) {
            if (currentUser.checkRole(item.getRole())) {
                if (choice == 0) {
                    return item;
                }
                choice--;
            }
        }
        return optionList[0];
    }

    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        User currentUser = UserManagement.getInstance().getCurrentUser();
        for (int i = 1; i < optionList.length; i++) {
            if (currentUser.checkRole(optionList[i].getRole())) {
                System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
                numItems++;
            }
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }

    private boolean isRepeatAction() {
        switch (subOption) {
            case CUSTOMER_ADD_NEW:
            case CUSTOMER_FILTER_BY_ID:
            case CUSTOMER_SAVE_TO_FILE:
            case CUSTOMER_UPDATE:
            case ORDER_ADD_NEW:
            case ORDER_DELETE:
            case ORDER_SAVE_TO_FILE:
            case ORDER_UPDATE:
                String confirm = Util.inputString("Repeat action (Y/N)");
                return confirm.trim().toLowerCase().startsWith("y");
        }
        return false;
    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case PRODUCT ->
                    productOptions;
                case CUSTOMER ->
                    customerOptions;
                case ORDER ->
                    orderOptions;
                default ->
                    primaryOptions;
            };
        }
        return optionList;
    }
}
