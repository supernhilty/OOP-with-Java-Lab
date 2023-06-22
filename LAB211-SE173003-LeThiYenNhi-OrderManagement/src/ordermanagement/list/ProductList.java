package ordermanagement.list;

import ordermanagement.model.Product;

/**
 *
 * @author leyen
 */
public class ProductList extends ObjectList<Product> {
    private static ProductList instance = new ProductList("Product.dat");

    public static ProductList getInstance() {
        return instance;
    }
    

    private ProductList() {
    }

    private ProductList(String filePath) {
        super(filePath);
    }

    public boolean isProductIdExists(String id) {
        if (id != null && !id.isBlank()) {
            Product obj = new Product();
            obj.setProductId(id);
            return this.contains(obj);
        }
        return false;
    }

    @Override
    public Product filter(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
//    public List<Product> filter(Date date) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    protected Product parseString(String stringObject) {
        Product obj = new Product();
        obj.parseString(stringObject);
        return obj;
    }

}
