public class Client implements Runnable {

    private static final int WAITING_TIME_BETWEEN_BUY = 100;
    String name;
    Dealer dealer;

    public Client(String name) {
        this.name = name;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public void buyCar() {
        try {
            Thread.sleep(WAITING_TIME_BETWEEN_BUY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dealer.sellCar(this);


    }

    @Override
    public void run() {
        while (!dealer.isInterrupted()) {
            if (!Thread.currentThread().isInterrupted()) {buyCar();}
        }
    }
}
