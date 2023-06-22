/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.data;

import bookstore.management.BException;
import bookstore.management.Menu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author leyen
 */
public class BookManagement {

    private static BookManagement instance = new BookManagement();

    public static BookManagement getInstance() {
        return instance;
    }

    private List<Book> bookList;

    public List<Book> bookList() {
        return bookList;
    }

    private BookManagement() {
        this.bookList = new ArrayList<>();
    }

    public void addNew() throws BException, IOException {
        Book book = new Book();
        book.input();

        if (searchTheBookByID(book.getId()) != null) {
            System.out.println("This book has already existed!");
        } else {
            this.bookList.add(book);
            System.out.println("Successfully!");
        }
        //saveToFile();

    }

    public Book searchTheBookByID(String bId) {
        for (Book book : bookList) {
            if (book.getId().equalsIgnoreCase(bId)) {

                return book;
            }
        }

        return null;
    }

    public List<Book> searchTheBook(String str) {
        List<Book> pBookList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getName().contains(str) || book.getPublisherId().equalsIgnoreCase(str)) {
                pBookList.add(book);
            }
        }

        return pBookList;
    }

    public void searchTheBook() {
        String pId = Utils.inputString("Input book publisher id or book name you want to search");
        if (searchTheBook(pId) != null) {
            System.out.println("Found!");
            System.out.println("| BookID |              Name              |    Price   |  Quantity  | PublisherID |     Status    |");
            for (Book book : searchTheBook(pId)) {
                System.out.println(book);
            }            
        } else {
            System.out.println("Have no result!");
        }
    }

    public void updateBook() throws BException, IOException {
        String bId = Utils.inputString("Enter the Book ID you want to update");

        if (searchTheBookByID(bId) == null) {
            System.out.println("Book ID does not exist");
            return;
        }
        Book book = searchTheBookByID(bId);
        System.out.println("| BookID |              Name              |    Price   |  Quantity  | PublisherID |     Status    |");
        System.out.println(book.toString());
        book.input();
        for (int i = 0; i < bookList().size(); i++) {
            if (bookList.get(i).getId().equals(bId)) {
                bookList.set(i, book);
                System.out.println("Sucessfully!");
            }
        }
        //saveToFile();

    }

    public void deleteBook() throws BException, IOException {
        String bId = Utils.inputString("Enter the Book ID you want to delete");

        if (searchTheBookByID(bId) == null) {
            System.out.println("Book ID does not exist");
            return;
            //menu
        }
        Book book = searchTheBookByID(bId);

        bookList.remove(book);
        //saveToFile();
        System.out.println("Deleted sucessfully");

    }

    public void saveToFile() throws BException, IOException {
        File f = new File("Book.dat");
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Book book : bookList) {
            bw.write(book.toString() + "\n");
        }

        bw.close();
        fw.close();

    }

    public void readFile() throws FileNotFoundException, IOException {

        File f = new File("Book.dat");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PublisherManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] info = line.split("[|]");
            String id = info[1].trim();
            String name = info[2].trim();
            double price = Double.parseDouble(info[3].trim());
            int quantity = Integer.parseInt(info[4].trim());
            String publisherId = info[5].trim();
            String Status = info[6].trim();
            bookList.add(new Book(id, name, price, quantity, publisherId, Status));

        }

        br.close();
        fr.close();

    }

    public void printFile() throws BException, IOException {
        //1readFile();
        if (bookList.isEmpty()) {
            System.err.println("The list is empty!");
        } else {
            Collections.sort(bookList);
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.println("| BookID |              Name              |    Price   |  Quantity  | PublisherID |     Status    |");
            System.out.println("|--------+--------------------------------+------------+------------+-------------+---------------|");
            for (Book book : bookList) {
                System.out.println(book.toString());
                System.out.println("|--------+--------------------------------+------------+------------+-------------+---------------|");
            }

        }

    }

    public void createLayout() throws BException, IOException {
        new Menu().menu("main");
    }
}
