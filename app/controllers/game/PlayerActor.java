package controllers.game;

import model.Table;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class PlayerActor extends UntypedActor {
	
	private final ActorRef out;
	
	public PlayerActor(ActorRef out, Table table) throws Exception {
		this.out = out;
		if (!table.join(getSelf())) {
			throw new Exception();
		}
	}
	
	public static Props props(ActorRef out, Table table) {
		return Props.create(PlayerActor.class, out, table);
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			out.tell("{\"message\":\"test\"}", self());
		}
	}

}
