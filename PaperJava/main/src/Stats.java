class Stats {
    private static int total = 0;
    private static int messages = 0;

    static synchronized void receive(Object message){
        messages++;
        total += (int) message;
        if (messages % 1000 == 0) {
            System.out.println("current mean of vowels: " + total);
        }
    }
}
