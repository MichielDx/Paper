import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class Worker extends UntypedActor {
    private int messages = 0;
    private final String vowels = "aeuio";
    final ActorRef stats = getContext().actorOf(Props.create(Stats.class), "stats");

    @Override
    public void onReceive(Object message) throws Throwable {
        messages++;
        if (messages % 100000 == 0) {
            throw new RuntimeException("Something went wrong with my calculation");
        }
        if (message instanceof String) {
            String temp = (String) message;
            Result result = calculate(temp);
            stats.tell(result, getSelf());
        }
    }

    private Result calculate(String message) {
        int count = 0;
        for (int i = 0; i < message.length() - 1; i++) {
            if (vowels.contains(message.substring(i, i + 1))) {
                count++;
            }
        }
        return new Result(message, count);
    }

    public static Props props() {
        return Props.create(Worker.class);
    }

}
