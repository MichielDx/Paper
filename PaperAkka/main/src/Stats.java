import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public class Stats extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private int total = 0;
    private int messages = 0;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Result){
            messages++;
            total += ((Result) message).getVowelcount();
            if (messages % 100000 == 0) {
                log.info("current number of vowels counted: " + total);
            }
        }
    }
}
