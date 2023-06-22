package ordermanagement.model;

import ordermanagement.list.CustomerList;
import ordermanagement.list.IObject;
import ordermanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class Customer implements IObject {

    public static final String ID_FORMAT = "CXXX";
    public static final String ID_PATTERN = "C\\d{3}";
    private static final int ATTRIBUTE_COUNT = 4;
    private static final String PHONE_PATTERN = "\\d{10,12}";
    private static final String NAME_PATTERN = "[\\a-zA-Z]{5,30}";

    private String id;
    private String name;
    private String address;
    private String phone;

    public Customer() {
    }

    public Customer(String id, String name, String address,
            String phone) {
        this.setId(id);
        this.setName(name);
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public final void setId(String id) {
        if (Util.validateString(id, Customer.ID_PATTERN, true)) {
            this.id = id;
        } else {
            System.out.println("Err: ...");
        }
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if (Util.validateString(name, NAME_PATTERN, true)) {
            this.name = name;
        } else {
            System.out.println("Err: ...");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (Util.validateString(phone, Customer.PHONE_PATTERN, true)) {
            this.phone = phone;
        } else {
            System.out.println("Err: ...");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(Util.SEPARATOR);
        sb.append(this.name.toUpperCase());
        sb.append(Util.SEPARATOR);
        sb.append(this.address);
        sb.append(Util.SEPARATOR);
        sb.append(this.phone);
        return sb.toString();
    }

    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Util.SEPARATOR));
        }
        return 0;
    }

    public int setAttribute(String attributes[]) {
        int idx = 0;
        if (attributes != null && attributes.length >= Customer.ATTRIBUTE_COUNT) {
            this.id = attributes[idx++].trim();
            this.name = attributes[idx++].trim();
            this.address = attributes[idx++].trim();
            this.phone = attributes[idx++].trim();

        }
        return idx;
    }

    public void input() {
        System.out.println("Input Customer...");
        this.id = inputId();
        this.name = inputName();
        this.address = inputAddress();
        this.phone = inputPhone();

    }

    protected static int getAttributeCount() {
        return Customer.ATTRIBUTE_COUNT;
    }

    private boolean validateId(String id, boolean checkExists) {
        boolean check = CustomerList.getInstance().isCustomerIdExists(id);
        if (check) {
            System.out.println("This Customer ID has existed!");
        }
        return Util.validateString(id, Customer.ID_PATTERN, true)
                && (!checkExists || !check);
    }

    private String inputId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the id with the pattern(" + Customer.ID_FORMAT + ")");
        } while (!validateId(inputId, true));

        return inputId;
    }

    private String inputName() {
        String inputName;
        do {
            inputName = Util.inputString("Please enter your name");
            if (!Util.validateString(inputName, NAME_PATTERN, true)) {
                System.out.println("Name has length from 5 to 30!");
            }
        } while (!Util.validateString(inputName, NAME_PATTERN, true));

        return inputName;
    }

    private String inputAddress() {
        return Util.inputAddress("Please enter the address");
    }

    private String inputPhone() {
        String inputPhone;
        do {
            inputPhone = Util.inputString("Please enter the phone with the length from 10 to 12");
        } while (!Util.validateString(inputPhone, Customer.PHONE_PATTERN, true));

        return inputPhone;
    }

    public void update() {
        String idUpdate = updateId();
        String nameUpdate = updateName();
        String addressUpdate = updateAddress();
        String phoneUpdate = updatePhone();
        if (!idUpdate.isBlank()) {
            setId(idUpdate);
        }
        if (!nameUpdate.isBlank()) {
            setName(nameUpdate);
        }
        if (!addressUpdate.isBlank()) {
            setAddress(addressUpdate);
        }
        if (!phoneUpdate.isBlank()) {
            setPhone(updatePhone());
        }

    }

    private String updateId() {
        String inputId;       
        do {
            inputId = Util.inputString("Please enter the id with the pattern(" + Customer.ID_FORMAT + ")", true);
        } while (!validateId(inputId, true) && !inputId.isBlank());

        return inputId;
    }

    private String updateName() {
        String inputName;
        do {
            inputName = Util.inputString("Please enter your name (length from 5 to 30)", true);            
        } while (!Util.validateString(inputName, NAME_PATTERN, true)&& !inputName.isBlank());

        return inputName;
    }

    private String updateAddress() {
        return Util.inputString("Please enter the address",true);
    }

    private String updatePhone() {
        String inputPhone;
        do {
            inputPhone = Util.inputString("Please enter the phone with the length from 10 to 12", true);
        } while (!Util.validateString(inputPhone, Customer.PHONE_PATTERN, true)&&!inputPhone.isBlank());

        return inputPhone;
    }

    @Override
    public void output() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(Util.SEPARATOR);
        sb.append(this.name.toUpperCase());
        sb.append(Util.SEPARATOR);
        sb.append(this.address);
        sb.append(Util.SEPARATOR);
        sb.append(this.phone);
        System.out.println(sb.toString());
    }
}
