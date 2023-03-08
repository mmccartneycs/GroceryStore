package Service;

import Model.Product;
import Model.User;
import DAO.ProductDAO;
import DAO.UserDAO;

public class ProductService{
    private ProductDAO productDAO;
    private UserDAO userDAO;

    public ProductService(){
        productDAO = new ProductDAO();
    }

    public User getAllProducts(Product product){
        return userDAO.getAllProducts(product);
    }

    public User getProductByName(String filter){
        return userDAO.getProductByName(filter);
    }

    public User getProductsByFilters(String filter){
        return userDAO.getProductsByFilters(filter);
    }
}