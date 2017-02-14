import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Michiel on 14/02/2017.
 */
public class AppZonderPool {
    public static void main(String[] args) {
        final String text = getText();
        final String[] words = text.split(" ");
        Runnable worker;

        for (int i = 0; i < words.length; i++) {
            worker = new Worker(words[i]);
            new Thread(worker).start();
        }

        System.out.println("Finished all threads");
        StdIn.readLine();
    }

    public static String getText() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();

            }
            return sb.toString();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
