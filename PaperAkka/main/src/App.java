import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        final String text = getText();
        final String[] words = text.split(" ");
        final ActorRef supervisor = system.actorOf(Supervisor.props(), "supervisor");
        for (int i = 0; i < words.length; i++) {
            supervisor.tell(words[i], ActorRef.noSender());
        }

        System.out.println("ENTER to terminate");
        StdIn.readLine();
        system.terminate();
    }

    public static String getText() {
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}