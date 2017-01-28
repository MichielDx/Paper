import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    static synchronized void receive(Object message) {
        if (message instanceof Integer) {
            System.out.println("simulating an average db transaction of 0.5 sec");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        if (message instanceof String) {
            try (FileWriter fw = new FileWriter("output.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println((String)message);
            } catch (IOException e) {

            }
        }
    }
}
