class Stats {
    private static int total = 0;
    private static int messages = 0;

    static synchronized void receive(Object message){
        if (message instanceof Result){
            messages++;
            total += ((Result) message).getVowelcount();
            if (messages % 1000 == 0) {
                System.out.println("current number of vowels counted: " + total);
            }
            
            if (messages == 1000000){
                System.out.println("Initiating db transaction");
                Writer.receive(total);
            }
        }
    }
}
