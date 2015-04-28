package controllers;

import java.util.HashSet;
import java.util.Set;

import model.Table;

import org.json.JSONArray;
import org.json.JSONException;

import akka.actor.ActorRef;
import controllers.game.PlayerActor;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

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
	
	public static WebSocket<String> join() {
		String tableId = request().body().asJson().get("table_id").asText();
		Table table = null;
		for (Table t : tables) {
			if (t.getId().toString().equals(tableId)) {
				table = t;
				break;
			}
		}
		WebSocket<String> newConnection = WebSocket.withActor(PlayerActor::props);
		if (table != null && table.join(newConnection)) {
			return newConnection;			
		}
		else {
			return WebSocket.reject(badRequest());
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
