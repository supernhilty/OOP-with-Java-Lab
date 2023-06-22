/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.data;

import utils.Utils;
import bookstore.management.BException;
import static utils.Utils.inputInt;

/**
 *
 * @author leyen
 */
public class Book implements Comparable<Book> {

    private static PublisherManagement p = PublisherManagement.getInstance();
    public static final String ID_FORMAT = "Bxxxxx";
    private static final String ID_PATTERN = "B\\d{5}";
    private static final String NAME_PATTERN = "[\\a-zA-Z]{5,30}";
    private static final String availStatus = "Available";
    private static final String nAvailStatus = "Not Available";

    private String id;
    private String name;
    private double price;
    private int quantity;
    private String publisherId;
    private String status;

    public Book() {
    }

    public Book(String id, String name, double price, int quantity, String publisherId, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.publisherId = publisherId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws BException {
        if (!Utils.validateStringPattern(id, Book.ID_PATTERN)) {

            throw new BException("Book Id has pattern Bxxxxx, with xxxxx is five digits, and is not allowed to duplicate");

        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws BException {
        if (!Utils.validateStringPattern(name, Book.NAME_PATTERN)) {

            throw new BException("Book Name is a string and has length from 5 to 30 characters.");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws BException {
        if (price <= 0) {
            throw new BException("Book Name is a string and has length from 5 to 30 characters.");

        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws BException {
        if (quantity <= 0) {
            throw new BException("Book Quantity is an integer number and greater than 0.");
        }
        this.quantity = quantity;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String pubId) throws BException {
        if (p.searchPublisherById(pubId) == null) {
            throw new BException("This publisher Id has not already existed!");

        }

        this.publisherId = pubId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws BException {
        if (!status.equalsIgnoreCase(availStatus)
                && !status.equalsIgnoreCase(nAvailStatus)) {
            throw new BException("Available or Not available");
        }
        this.status = status;
    }

    public void input() throws BException {
        while (true) {
            try {
                setId(Utils.inputString("Input id with patern " + Book.ID_FORMAT));
                break;
            } catch (BException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                setName(Utils.inputString("Input name"));
                break;
            } catch (BException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                setPrice(Utils.InputDouble("Input price", 0.000001, Double.MAX_VALUE));
                break;
            } catch (BException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                setPublisherId(Utils.inputString("Input Publisher Id"));

                break;
            } catch (BException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                setQuantity(Utils.inputInt("Input quantity", 0, Integer.MAX_VALUE));
                break;
            } catch (BException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                setStatus(inputStatus());
                break;
            } catch (BException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public String toString() {
        return String.format("|%-8s|%-32s|%-12f|%-12d|%-13s|%-15s|",
                id, name, price, quantity,
                publisherId, status);

    }

    @Override
    public int compareTo(Book o) {
        if (o != null) {
            return this.id.compareTo(o.id);
        }
        return 1;
    }

    public String inputStatus() {
        System.out.println("Input status:");
        System.out.println("1. Available");
        System.out.println("2. Not available");

        int ops = inputInt("Input choice", 1, 2);
        String rs = "";

        switch (ops) {
            case 1:
                rs = "Available";
                break;
            case 2:
                rs = "Not available";
                break;
        }

        return rs;
    }
}
