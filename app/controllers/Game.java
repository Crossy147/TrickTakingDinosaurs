package controllers;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import play.mvc.Controller;
import play.mvc.Result;

import model.Table;
import java.lang.*;

public class Game extends Controller {
    public static Result deal() {
        System.out.println("Hello deal!");
        return ok();
    }
}

