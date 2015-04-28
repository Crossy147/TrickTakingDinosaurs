package controllers.game;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class PlayerActor extends UntypedActor {
	
	private final ActorRef out;
	
	public PlayerActor(ActorRef out) {
		this.out = out;
	}
	
	public static Props props(ActorRef out) {
		return Props.create(PlayerActor.class, out);
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			out.tell("test", self());
		}
	}

}
