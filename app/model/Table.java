package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import akka.actor.ActorRef;

public class Table {
	
	private static final String FIELD_ID = "id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_PLAYERS_COUNT = "players_count";
	
	private final UUID id;
	private final String name;
	private int playersCount = 1;
	
	private List<ActorRef> connections = new ArrayList<>();
	
	public Table(String name) {
		id = UUID.randomUUID();
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public synchronized boolean join(ActorRef newPlayer) {
		if (connections.size() < 4) {
			for (ActorRef out : connections) {
				out.tell("joined", ActorRef.noSender());
			}
			connections.add(newPlayer);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Table)) {
			return false;
		}	
		Table t = (Table)o;
		return id.equals(t.id);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(FIELD_ID, id);
		json.put(FIELD_NAME, name);
		json.put(FIELD_PLAYERS_COUNT, playersCount);
		return json;
	}
}
