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
        //app.patch("/member/{member_id}", this::patchUserInfoHandler);
        app.patch("/cart/{quantity}", this:patchCartHandler());
        app.get("/cart", this::getCartHandler);
        app.get("/cart/checkout", this::getCheckoutMemberHandler);
        app.post("/cart/checkout", this::postCheckoutHandler);
        app.get("/grocery", this::getGroceriesHandler);
        app.get("/grocery/{item}", this::getItemHandler);
        app.get("/grocery/{search}", this::getSearchHandler);
        app.get("/grocery/{filters}", this::getFiltersHandler);
        return app;
    }

    private void postUserHandeler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        User user = om.readVaoue(ctx.body(), User.class);
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
        Grocery grocery = om.readValue(ctx.body(), Grocery.class);
        int upc = grocery.getUpc();
        String q_input = ctx.pathParam("quantity");
        int q = Integer.parseInt(q_input);
        Grocery patchedGrocery = GroceryController.patchCartByUpc(upc, q);
    }

    private void getCartHandler(Context ctx) throws JsonProcessingException{
        ctx.json(groceryService.getCart());
    }

    private void getCheckoutMemberHandler(Context ctx) throws JsonProcessingException{

    }

    private void postCheckoutHandler(Context ctx) throws JsonProcessingException{

    }

    private void getGroceriesHandler(Context ctx) throws JsonProcessingException{
        ctx.json(groceryService.getAllGroceries());
    }

    private void getItemHandler(Context ctx) throws JsonProcessingException{
        String item_input = ctx.pathParam("item");
        Grocery grocery = groceryService.getGroceryByName(item_input);
        if(grocery != null){
            ctx.json(grocery);
        }
    }
    private void getSearchHandler(Context ctx) throws JsonProcessingException{
        String search_input = ctx.pathParam("search");
        Grocery grocery = groceryService.getGroceryByName(search_input);
        if(grocery != null){
            ctx.json(grocery);
        }
    }
    private void getFiltersHandler(Context ctx) throws JsonProcessingException{
        String filters = ctx.pathParam("filters");
        ctx.json(groceryService.getGroceriesByFilters(filters));
    }
}