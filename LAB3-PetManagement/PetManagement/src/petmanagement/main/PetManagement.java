/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petmanagement.main;

import petmanagement.controller.OrderController;
import petmanagement.controller.PetController;
import petmanagement.controller.UserController;
import petmanagement.model.OrderModel;
import petmanagement.model.PetModel;
import petmanagement.utils.Singleton;
import petmanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class PetManagement {

    private UserController userController;
    private PetController petController;
    private OrderController orderController;

    private PetManagement() {
    }

    private void prepareData() {
        this.userController = Singleton.getInstance(UserController.class);
        this.petController = Singleton.getInstance(PetController.class);
        this.orderController = Singleton.getInstance(OrderController.class);

        this.petController.loadModels();
        this.orderController.loadModels();

//        this.petController.refresh();
//        this.orderController.refresh();
    }

    private void addPet() {
        if (this.userController.checkCurrentUserRole(UserRole.ADMIN)) {
            PetModel model = this.petController.addNewModel();
            if (model != null) {
                System.out.println("Success!");
                this.petController.showModel(model);
            } else {
                System.out.println("Failed!");
            }
        } else {
            System.out.println("Error!");
        }
    }

    private void findPet() {
        String id = Util.inputString("Input Pet ID want to find", false);
        if (this.userController.checkCurrentUserRole(UserRole.USER)) {
            this.petController.filterByPetId(id);            
        } else {
            System.out.println("Error!");
        }

    }

    private void updatePet() throws ModelException {
        String id = Util.inputString("Input Pet ID want to update", false);
        if (this.userController.checkCurrentUserRole(UserRole.USER)) {
            this.petController.updatePet(id);
        } else {
            System.out.println("Error!");
        }
    }

    private void deletePet() {
        String id = Util.inputString("Input Pet ID want to delete", false);
        if (this.userController.checkCurrentUserRole(UserRole.ADMIN)) {
            this.petController.deletePet(id);
        } else {
            System.out.println("???");
        }
    }

    private void addOrder() {
        if (this.userController.checkCurrentUserRole(UserRole.ADMIN)) {
            OrderModel model = this.orderController.addNewModel();
            if (model != null) {
                System.out.println("Success!");
                this.orderController.showModel(model);
            } else {
                System.out.println("Failed!");
            }
        } else {
            System.out.println("???");
        }
    }

    private void listOrder() {
        System.out.println("List orders");
        this.orderController.showAll();
    }

//    private void sortOrder() {
//        this.orderController.sortOrder();
//    }

    private void listPet() {
        this.petController.showAll();
    }

    private void run() throws ModelException {
        Menu menu = new Menu();
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {

                case PET_ADD ->
                    addPet();
                case PET_LIST ->
                    listPet();
                case PET_DELETE ->
                    deletePet();
                case PET_FIND ->
                    findPet();
                case PET_UPDATE ->
                    updatePet();
                case ORDER_ADD ->
                    addOrder();
                case ORDER_LIST ->
                    listOrder();
//                case ORDER_SORT ->
//                    sortOrder();
                case EXIT -> {
                    System.out.println("Exited!");
                }
                default ->
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    private void start() throws ModelException {
        System.out.println("University management");
        System.out.println("Login ...");
        if (Singleton.getInstance(UserController.class).login()) {
            prepareData();
            run();
        } else {
            System.out.println("Login failed!");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ModelException {
        new PetManagement().start();
    }

}
