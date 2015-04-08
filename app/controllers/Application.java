package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import views.html.tables;

public class Application extends Controller {

	private static String USER = "user";
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result signin() {
    	String username = request().body().asJson().get("username").asText();
    	session(USER, username); //TODO encrypt
    	return ok(tables.render());
    }

    public static Result login() {
        return ok(login.render());
    }

    public static Result tables() {
        return ok(tables.render());
    }

}
