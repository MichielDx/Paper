import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Integer) {
            log.info("simulating an average db transaction of 0.5 sec");
            Thread.sleep(500);
        }
        if (message instanceof String) {
            log.info("writing away contents of my parent Builder");
            try (FileWriter fw = new FileWriter("output.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println((String) message);
            } catch (IOException e) {
                log.error("Something went wrong trying to write rebuildedText");
            }
        }
    }

    public static Props props() {
        return Props.create(Writer.class);
    }
}
