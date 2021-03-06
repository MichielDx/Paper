import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        final String text = getText();
        final String[] words = text.split(" ");


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
