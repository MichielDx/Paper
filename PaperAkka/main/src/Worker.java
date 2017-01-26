import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class Worker extends UntypedActor {
    private int messages = 0;
    private final String vowels = "aeuio";
    final ActorRef stats = getContext().actorOf(Props.create(StatsActor.class));
    @Override
    public void onReceive(Object message) throws Throwable {
        messages++;
        if (message instanceof String) {
            String temp = (String) message;
            int result = calculate(temp);
            stats.tell(result, getSelf());
        }
    }

    private int calculate(String message) {
        int count = 0;
        for (int i = 0; i < message.length()-1; i++) {
            if(vowels.contains(message.substring(i,i+1))){
                count++;
            }
        }
        return count;
    }

    public static Props props() {
        return Props.create(Worker.class);
    }

}
