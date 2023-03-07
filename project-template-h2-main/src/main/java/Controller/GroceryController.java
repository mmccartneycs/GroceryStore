package Controller;

import Model.Grocery;
import Model.User;
import Service.GroceryService;
import Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class GroceryController{
    GroceryService groceryService;
    UserService userService;
    public GroceryController(){
        this.groceryService = new GroceryService();
        this.userService = new UserService;
    }

    public Javalin startAPI(){
        Javalin app = Javalin.create();

        app.post("/register", this::postUserHandeler);
        app.post("/login", this::postLoginUserHandler);
        app.patch("/member/{member_id}", this::patchUserInfoHandler);
        app.put("/cart", this::putCartHandler);
        app.delete("/cart", this::deleteCartHandler);
        app.get("/cart", this::getCartHandler);
        app.get("/cart/checkout", this::getCheckoutMemberHandler);
        app.post("/cart/checkout", this::postCheckoutHandler);
        app.get("/products", this::getProductsHandler);
        app.get("/products/{item}", this::getItemHandler);
        app.get("/search", this::getSearchHandler);
        app.get"/search/{filters}", this::getFiltersHandler);
        return app;
    }

    private void postUserHandeler(Context ctx) throws JsonProcessingException{

    }

    private void postLoginUserHandler(Context ctx) throws JsonProcessingException{

    }

    private void patchUserInfoHandler(Context ctx) throws JsonProcessingException{

    }

    private void putCartHandler(Context ctx) throws JsonProcessingException{

    }

    private void deleteCartHandler(Context ctx) throws JsonProcessingException{

    }

    private void getCartHandler(Context ctx) throws JsonProcessingException{

    }

    private void getCheckoutMemberHandler(Context ctx) throws JsonProcessingException{

    }

    private void postCheckoutHandler(Context ctx) throws JsonProcessingException{

    }

    private void getProductsHandler(Context ctx) throws JsonProcessingException{

    }

    private void getItemHandler(Context ctx) throws JsonProcessingException{

    }
    private void getSearchHandler(Context ctx) throws JsonProcessingException{

    }
    private void getFiltersHandler(Context ctx) throws JsonProcessingException{

    }
}