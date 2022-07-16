public class Client extends Thread {

    private static final int WAITING_TIME_BETWEEN_BUY = 10;
    String name;
    Dealer dealer;

    public Client(String name) {
        this.name = name;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public void buyCar() {
        dealer.sellCar(this);
        try {
            Thread.sleep(WAITING_TIME_BETWEEN_BUY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        while (!dealer.isInterrupted()) {
            buyCar();
        }
    }
}
