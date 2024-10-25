package service.custom;

import dto.Product;
import javafx.collections.ObservableList;
import service.SuperService;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(String id);
    ObservableList<Product> getAll();
    Product searchProduct(String id);
    ObservableList<String> getProductIds();
    String generateId ();
}
