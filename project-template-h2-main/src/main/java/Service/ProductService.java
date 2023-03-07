
public class ProductService{
    private ProductDAO productDAO;

    public ProductService(){
        productDAO = new productDAO();
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