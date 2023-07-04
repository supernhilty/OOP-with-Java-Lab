package ordermanagement.model;

import ordermanagement.list.IObject;
import ordermanagement.list.ProductList;
import ordermanagement.utils.Util;

/**
 *
 * @author leyen
 */
public class Product implements IObject {

    public static String PRODUCT_ID_PATTERN = "P\\d{3}";
    public static String PRODUCT_ID_FORMAT = "PXXX";
    private static final String PRODUCT_NAME_PATTERN = "[\\a-zA-Z]{5,30}";
    private static final int ATTRIBUTE_COUNT = 5;

    private String productId;
    private String productName;
    private String unit;
    private String origin;
    private double price;

    public Product() {
    }

    public Product(String productId, String productName, String unit, String origin, double price) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    @Override
    public String getId() {
        return productId;
    }

    public void setProductId(String productId) {
        if (Util.validateString(productId, Product.PRODUCT_ID_PATTERN, true)) {
            this.productId = productId;
        } else {
            System.out.println("Error..");
        }

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (Util.validateString(productId, Product.PRODUCT_NAME_PATTERN, true)) {
            this.productName = productName;
        } else {
            System.out.println("Error..");
        }

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        if (validateUnit(unit)) {
            this.unit = unit;
        } else {
            System.out.println("Error...");
        }

    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Error...");
        }

    }

    @Override
    public void output() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.productId);
        sb.append(Util.SEPARATOR);
        sb.append(this.productName);
        sb.append(Util.SEPARATOR);
        sb.append(this.unit);
        sb.append(Util.SEPARATOR);
        sb.append(this.origin);
        sb.append(Util.SEPARATOR);
        sb.append(this.price);
        System.out.println(sb.toString());
    }

    public int setAttribute(String attributes[]) {
        int idx = 0;
        if (attributes != null && attributes.length >= ATTRIBUTE_COUNT) {
            this.productId = attributes[idx++].trim();
            this.productName = attributes[idx++].trim();
            this.unit = attributes[idx++].trim();
            this.origin = attributes[idx++].trim();
            this.price = Double.parseDouble(attributes[idx++].trim());
            return idx;
        }
        return idx;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.productId);
        sb.append(Util.SEPARATOR);
        sb.append(this.productName);
        sb.append(Util.SEPARATOR);
        sb.append("box of ").append(this.unit).append(" tablets");
        sb.append(Util.SEPARATOR);
        sb.append(this.origin);
        sb.append(Util.SEPARATOR);
        sb.append(this.price);
        return sb.toString();
    }

    public void input() {
        this.productId = inputId();
        this.productName = inputName();
        this.unit = inputUnit();
        this.origin = Util.inputString("Input origin");
    }

    private boolean validateId(String id, boolean checkExists) {
         boolean check = ProductList.getInstance().isProductIdExists(id);
        if(check){
            System.out.println("This Product ID has existed!");
        }
        return Util.validateString(id, Product.PRODUCT_ID_PATTERN, true)
                && (!checkExists || !check);
    }

    private String inputId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the id with the pattern(" + PRODUCT_ID_FORMAT + ")");
        } while (!validateId(inputId, true));

        return inputId;
    }

    private String inputName() {
        String inputName;
        do {
            inputName = Util.inputString("Please enter product name");
            if (!Util.validateString(inputName, PRODUCT_NAME_PATTERN, true)) {
                System.out.println("Name has length from 5 to 30!");
            }
        } while (!Util.validateString(inputName, PRODUCT_NAME_PATTERN, true));

        return inputName;
    }

    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Util.SEPARATOR));
        }
        return 0;
    }

    private boolean validateUnit(String unit) {
        String test = "box of ";
        return unit.contains(test);
    }

    private String inputUnit() {

        int unit;

        unit = Util.inputInteger("Please enter unit: box of ... tablets.", 0, Integer.MAX_VALUE);
        String inputUnit = "box of " + this.unit + " tablets";

        return inputUnit;
    }

}
