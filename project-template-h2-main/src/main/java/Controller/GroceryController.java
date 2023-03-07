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
        return app;
    }

    private void postUserHandeler(Context ctx) throws JsonProcessingException{

    }
}