package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;


public class Application extends Controller {

	public static String USER = "user";
	public static String HOME = "/";
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result signin() {
    	String username = request().body().asJson().get("username").asText();
    	session(USER, username); //TODO encrypt
    	return ok();
    }

}
