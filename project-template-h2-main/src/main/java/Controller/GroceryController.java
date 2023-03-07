package Controller;

import Model.Product;
import Model.User;
import Model.Cart;
import Service.ProductService;
import Service.UserService;
import Service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class GroceryController{
    ProductService productService;
    UserService userService;
    public GroceryController(){
        this.productService = new ProductService();
        this.userService = new UserService;
    }

    public Javalin startAPI(){
        Javalin app = Javalin.create();

        app.post("/register", this::postUserHandler);
        app.post("/login", this::postLoginUserHandler);
        //app.patch("/member/{member_id}", this::patchUserInfoHandler);
        app.patch("/cart/{quantity}", this:patchCartHandler());
        app.get("/cart", this::getCartHandler);
        app.get("/checkout/{member_id}", this::getCheckoutMemberHandler);
        //app.post("/cart/checkout", this::postCheckoutHandler);
        app.get("/product", this::getProductsHandler);
        app.get("/product/{item}", this::getItemByNameHandler);
        app.get("/product/{search}", this::getSearchHandler);
        app.get("/product/{filters}", this::getFiltersHandler);
        return app;
    }

    private void postUserHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(ctx.body(), User.class);
        User newUser = userService.addUser(user);
        if(newUser != null){
            ctx.json(newUser);
        }else{
            ctx.status(400);
        }
    }

    private void postLoginUserHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(ctx.body(), User.class);
        User login = userService.verifyUser(user);
        if(login != null){
            ctx.json(login);
        }else{
            ctx.status(401);
        }
    }

    /*private void patchUserInfoHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper
    }*/

    private void patchCartHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Cart cart = om.readValue(ctx.body(), Cart.class);
        int upc = product.getUpc();
        String q_input = ctx.pathParam("quantity");
        int q = Integer.parseInt(q_input);
        Cart patchedCart = CartService.patchCartByUpc(upc, q);
    }

    private void getCartHandler(Context ctx) throws JsonProcessingException{
        ctx.json(cartService.getCart());
    }

    private void getCheckoutMemberHandler(Context ctx) throws JsonProcessingException{
        String user_id = ctx.pathParam("member_id");
        int id = Integer.parseInt(user_id);
        ctx.json(userService.getCredentials(id));
    }

    /*private void postCheckoutHandler(Context ctx) throws JsonProcessingException{

    }*/

    private void getProductsHandler(Context ctx) throws JsonProcessingException{
        ctx.json(productService.getAllGroceries());
    }

    private void getItemByNameHandler(Context ctx) throws JsonProcessingException{
        String item_input = ctx.pathParam("item");
        Product product = productService.getProductByName(item_input);
        if(product != null){
            ctx.json(product);
        }
    }
    private void getSearchHandler(Context ctx) throws JsonProcessingException{
        String search_input = ctx.pathParam("search");
        Product product = productService.getProductByName(search_input);
        if(product != null){
            ctx.json(product);
        }
    }
    private void getFiltersHandler(Context ctx) throws JsonProcessingException{
        String filters = ctx.pathParam("filters");
        ctx.json(productService.getGroceriesByFilters(filters));
    }
}