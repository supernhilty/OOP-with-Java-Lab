package petmanagement.main;

import petmanagement.controller.UserController;
import petmanagement.utils.Singleton;
import petmanagement.utils.Util;


/**
 * Menu.
 *
 * @author hasu
 */
public class Menu {

    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.ORDER,
        MenuItem.PET,       
    };

    private final MenuItem[] petOptions = {
        MenuItem.BACK,
        MenuItem.PET_LIST,
        MenuItem.PET_ADD,
        MenuItem.PET_FIND,
        MenuItem.PET_UPDATE,
        MenuItem.PET_DELETE,       
    };

    private final MenuItem[] orderOptions = {
        MenuItem.BACK,
        MenuItem.ORDER_LIST,
        MenuItem.ORDER_SORT,
        MenuItem.ORDER_ADD,
       
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
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT) {
                subOption = getChoice(primaryOption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "University management:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);
        UserController userController = Singleton.getInstance(UserController.class);
        for (MenuItem item : optionList) {
            if (userController.checkCurrentUserRole(item.getRole())) {
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
        UserController userController = Singleton.getInstance(UserController.class);
        for (int i = 1; i < optionList.length; i++) {
            if (userController.checkCurrentUserRole(optionList[i].getRole())) {
                System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
                numItems++;
            }
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case PET ->
                    petOptions;
                case ORDER ->
                    orderOptions;               
                default ->
                    primaryOptions;
            };
        }
        return optionList;
    }
}
