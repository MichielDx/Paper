import akka.actor.Props;
import akka.actor.UntypedActor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Builder extends UntypedActor {
    private int messages = 0;
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void onReceive(Object message) throws Throwable {
        messages++;
        if (message instanceof String) {
            stringBuilder.append((String) message).append(" ");
            if (messages == 1000001) {
                try (FileWriter fw = new FileWriter("rebuildedText.txt", false);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    out.println(stringBuilder.toString());
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
            }
        }

    }

    public static Props props() {
        return Props.create(Builder.class);
    }
}
