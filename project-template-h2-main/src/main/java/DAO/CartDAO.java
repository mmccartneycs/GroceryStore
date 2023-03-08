package DAO;

import Model.Cart;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO{

    public List<Cart> getCart(cart_id){
        Connection con = ConnectionUtil.getConnection();
        List<Cart> cartList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM cart WHERE cart_id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cart_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cart cart = new Cart(rs.getint("upc"),
                        rs.getInt("quantity"));
                cartList.add(cart);
            }
        }
        catch(SQLException e){
            System.out.println(e.getCart());
        }
        return cartList;
    }

    public patchCartByUpc(int cart_id, int upc, int quantity){
        Connection con = ConnectionUtil.getConnection();
        List<Cart> cartList = new ArrayList<>();
        try{
            String sql = "UPDATE cart SET quantity = ? WHERE cart_id = ? AND upc = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cart_id);
            ps.setInt(3, upc);
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getCart());
        }
    }

}
