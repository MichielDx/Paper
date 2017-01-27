import java.util.Random;

/**
 * Created by Michiel on 26/01/2017.
 */
public class Worker implements Runnable {
    private final String vowels = "aeuio";
    private Object message;
    private Random rnd = new Random();

    public Worker(Object message) {
        this.message = message;
    }

    @Override
    public void run() {
        if(rnd.nextInt(100000)+1 == 1){
            throw new RuntimeException("Something went wrong with my calculation");
        }
        if (message instanceof String) {
            Builder.receive(message);
            String temp = (String) message;
            int result = calculate(temp);
            Stats.receive(result);
        }
    }

    private int calculate(String message) {
        int count = 0;
        for (int i = 0; i < message.length() - 1; i++) {
            if (vowels.contains(message.substring(i, i + 1))) {
                count++;
            }
        }
        return count;
    }
}
