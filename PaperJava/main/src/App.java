import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Michiel on 26/01/2017.
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        final String text = getText();
        final String[] words = text.split(" ");
          /*
        for (int i = 0; i < words.length; i++) {
            new Thread(new Worker(words[i])).start();
        }
        System.out.println("Finished all threads");
        */
        for (int i = 0; i < words.length; i++) {
            Runnable worker = new Worker(words[i]);
            executor.execute(worker);
        }

        System.out.println("Finished all threads");
        executor.shutdown();
        while (!executor.isTerminated()) {
        }


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
            e.printStackTrace();
        }
        return "";
    }
}
