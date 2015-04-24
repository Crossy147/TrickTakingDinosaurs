package controllers;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import play.mvc.Controller;
import play.mvc.Result;

import model.Table;

public class TableLobby extends Controller {

	private static Set<Table> tables = new HashSet<Table>();
	
	public static Result create() {
		String tableName = request().body().asJson().get("table_name").asText();
		Table newTable = new Table(tableName);
		Result result;
		try{
			result = ok(newTable.toJson().toString());
			tables.add(newTable);
			return result;
		} catch (JSONException e) {
			return badRequest();
		}
	}
	
	public static Result list() {
		JSONArray result = new JSONArray();
		for (Table t : tables) {
			try {
				result.put(t.toJson());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ok(result.toString());
	}
	
}
