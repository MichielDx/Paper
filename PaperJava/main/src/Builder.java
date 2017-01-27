import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Michiel on 26/01/2017.
 */
class Builder {
    private static int messages = 0;
    private static StringBuilder stringBuilder = new StringBuilder();

    synchronized static void receive(Object message) {
        messages++;
        if (message instanceof String) {
            stringBuilder.append((String) message).append(" ");
            if (messages == 1000000) {
                Writer.receive(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }
    }
}
