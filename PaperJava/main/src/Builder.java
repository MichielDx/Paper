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
