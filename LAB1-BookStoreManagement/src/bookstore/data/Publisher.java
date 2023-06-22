/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.data;

import bookstore.management.BException;
import utils.Utils;

/**
 *
 * @author leyen
 */
public class Publisher implements Comparable<Publisher> {

    public static final String ID_FORMAT = "Pxxxxx";
    private static final String ID_PATTERN = "P\\d{5}";
    private static final String NAME_PATTERN = "[\\a-zA-Z]{5,30}";
    private static final String PHONE_PATTERN = "\\d{10,12}";

    private String id;
    private String name;
    private String phone;

    public Publisher(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Publisher() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws BException {
        if (!Utils.validateStringPattern(id, Publisher.ID_PATTERN)) {
            throw new BException("Error: Publisher Id has pattern " + Publisher.ID_FORMAT + ", with xxxxx is five digits, and is not allowed to duplicate");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws BException {
        if (!Utils.validateStringPattern(name, NAME_PATTERN)) {
            throw new BException("Error: Publisher Name is a string and has a length from 5 to 30 characters.");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws BException {
        if (!Utils.validateStringPattern(phone, PHONE_PATTERN)) {
            throw new BException("Error: The phone is a number string that has a length from 10 to 12");
        }
        this.phone = phone;
    }

    public void input() throws BException {
        while (true) {
            try {
                setId(Utils.inputString("Input id"));
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
                setPhone(Utils.inputString("Input phone"));
                break;
            } catch (BException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public String toString() {
        return String.format("|%-13s|%-32s|%-14s|", id, name, phone);
    }

    @Override
    public int compareTo(Publisher o) {
        if (o != null) {
            return this.id.compareTo(o.id);
        }
        return 1;
    }

}
