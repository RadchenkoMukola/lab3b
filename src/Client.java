public class Client extends Thread {
    private Barber barber;
    private int clientNumber;

    public void setBarber(Barber mybarber) {
        this.barber = mybarber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }


    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println("Клієнт №"+clientNumber+" зайшов і чекає");
                synchronized (barber) {
                    barber.notify();
                }
                wait();
                System.out.println("Клієнт №" + clientNumber + " сів у крісло");
                notify();
                System.out.println("Клієнт №" + clientNumber + " заснув");
                wait();
                System.out.println("Клієнт №" + clientNumber + " прокинувся і пішов");
                System.out.println("===============================================");
                notify();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}