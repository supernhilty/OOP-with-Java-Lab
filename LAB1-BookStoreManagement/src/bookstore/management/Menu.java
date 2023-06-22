package bookstore.management;

import bookstore.data.BookManagement;
import bookstore.data.PublisherManagement;
import java.io.IOException;
import java.util.Scanner;
import utils.Utils;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static BookManagement b = BookManagement.getInstance();
    private static PublisherManagement p = PublisherManagement.getInstance();

    public Menu() throws IOException {
        try {
            loadData();
        } catch (Exception e) {
        }
    }

    public void loadData() throws IOException {
        p.readFile();
        b.readFile();
    }

    private static String[] opsparent = {
        "Publishers' management",
        "Books' management",
        "Quit"};

    private static String[] opsbook = {
        "Create a Book",
        "Search the Book",
        "Update Book",
        "Delete Book information",
        "Save to file",
        "Print all lists from file",
        "Create a layout",
        "Back"
    };

    private static String[] opspublisher = {
        "Create a Publisher",
        "Delete Publisher information",
        "Save to file",
        "Print all lists from file",
        "Back"
    };

    public int getChoice(String[] ops) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < ops.length; i++) {
            System.out.println((i + 1) + ". " + ops[i]);
        }
        System.out.println("=================================================");
        return Utils.inputInt("Your choices", 1, ops.length);

    }

    public void display() throws BException, IOException {

        System.out.println("==============BookStore Management===============");
        int choiceStore = getChoice(opsparent);

        do {
            switch (choiceStore) {
                case 1:
                    System.out.println("******************Publisher******************");
                    processPublisher();
                    break;
                case 2:
                    System.out.println("*********************Book*********************");
                    processBook();
                    break;
                case 3:                    
                    return;
            }

        } while (choiceStore != 3);
    }

    public void display(int choiceStore) throws BException, IOException {

        System.out.println("==============BookStore Management===============");
        do {
            switch (choiceStore) {
                case 1:
                    processPublisher();
                    break;
                case 2:
                    processBook();
                    break;
                case 3:
                    return;
            }

        } while (choiceStore != 3);
    }

    public void processPublisher() throws BException, IOException {
        int choice = getChoice(opspublisher);
        do {
            switch (choice) {
                case 1:
                    p.addNewPublisher();
                    menu("publisher");
                    break;
                case 2:
                    p.deletePublisher();
                    menu("publisher");
                    break;
                case 3:
                    p.saveToFile();
                    menu("publisher");
                    break;
                case 4:
                    p.printFromFile();
                    menu("publisher");
                    break;
                case 5:
                    display();
                    break;

            }

        } while (choice != 5);

    }

    public void processBook() throws BException, IOException {
        int choice = getChoice(opsbook);
        do {
            switch (choice) {
                case 1:
                    b.addNew();
                    menu("book");
                    break;
                case 2:
                    b.searchTheBook();
                    menu("book");
                    break;
                case 3:
                    b.updateBook();
                    menu("book");
                    break;
                case 4:
                    b.deleteBook();
                    menu("book");
                    break;
                case 5:
                    b.saveToFile();
                    menu("book");
                    break;
                case 6:
                    b.printFile();
                    menu("book");
                    break;
                case 7:
                    b.createLayout();                    
                    break;
                case 8:
                    display();
                    break;

            }

        } while (choice != 8);
    }

    public void menu(String menuType) throws BException, IOException {
        boolean wrong;
        System.out.println("Do you want to go back to menu?");
        do {
            wrong = false;
            System.out.print("Your choice (Y/N): ");
            String choice = sc.next().toUpperCase();
            wrong = !choice.equals("Y") && !choice.equals("N");
            if (wrong) {
                System.err.println("Please input again!");
            } else {
                if (choice.equals("Y")) {
                    switch (menuType) {
                        case "publisher":
                            display(1);
                            break;
                        case "book":
                            display(2);
                            break;
                        case "main":
                            display();

                    }
                } else {
                    return;
                }
            }
        } while (wrong);
    }
}
