package controllers;

import java.util.HashSet;
import java.util.Set;

import model.Table;

import org.json.JSONArray;
import org.json.JSONException;

import play.libs.F.Function;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import akka.actor.ActorRef;
import akka.actor.Props;
import controllers.game.PlayerActor;

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
		final Table finTable = table;
		try {
			return WebSocket.withActor(new Function<ActorRef, Props>() {
				public Props apply(ActorRef out) throws Throwable {
					return PlayerActor.props(out, finTable);
				}
			});			
		} catch (Exception e) {
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
