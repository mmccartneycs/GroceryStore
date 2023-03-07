package Service;

import Model.Cart;
import DAO.CartDAO;
import DAO.ProductDAO;

import java.util.Lists;

public class CartService{
    public CartDAO cartDAO;
    public ProductDAO productDAO;

    public CartService(){
        cartDAO = new CartDAO();
        productDAO = new ProductDAO();
    }

    public List<Cart> getCart(){
        return cartDAO.getFullCart();
    }
}