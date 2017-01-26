import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Builder extends UntypedActor {
    private int messages = 0;
    private StringBuilder stringBuilder = new StringBuilder();
    final ActorRef writer = getContext().actorOf(Props.create(Writer.class));

    @Override
    public void onReceive(Object message) throws Throwable {
        messages++;
        if (message instanceof String) {
            stringBuilder.append((String) message).append(" ");
            if (messages == 1000001) {
                writer.tell(stringBuilder.toString(), getSelf());
            }
        }

    }

    public static Props props() {
        return Props.create(Builder.class);
    }
}
