public class Barber extends Thread {

    private final Queue queue;

    public Barber(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isAlive()) {
            Client client = queue.getClient();
            if (client == null) {
                synchronized (this) {
                    try {
                        System.out.println("Перукар спить");
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else {
                synchronized (client) {
                    try {
                        System.out.println("Перукар покликав наступного клієнта");

                        client.notify();
                        int i = client.getClientNumber();
                        client.wait();

                        System.out.println("Перукар стриже клієнта №"+i);
                        Thread.sleep(100);
                        System.out.println("Перукар закінчив стригти клієнта №"+i);

                        client.notify();
                        client.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
