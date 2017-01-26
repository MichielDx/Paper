import akka.actor.Props;
import akka.actor.UntypedActor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Arthur Haelterman on 26/01/2017.
 */
public class Writer extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            try (FileWriter fw = new FileWriter("rebuildedText.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println((String) message);
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
    }

    public static Props props() {
        return Props.create(Writer.class);
    }
}
