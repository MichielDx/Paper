import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.Random;

public class Worker extends UntypedActor {
    private int messages = 0;
    private final String vowels = "aeuio";
    final ActorRef stats = getContext().actorOf(Props.create(Stats.class), "stats");
    private Random rnd = new Random();

    @Override
    public void onReceive(Object message) throws Throwable {
        messages++;
        if(rnd.nextInt(10000000)+1 == 1){
            throw new RuntimeException("Something went wrong with my calculation");
        }
        if (message instanceof String) {
            String temp = (String) message;
            int count = calculate(temp);
            stats.tell(new Result((String) message, count), getSelf());
        }
    }

    private int calculate(String message) {
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            if (vowels.contains(message.substring(i, i + 1))) {
                count++;
            }
        }
        return count;
    }

    public static Props props() {
        return Props.create(Worker.class);
    }

}
