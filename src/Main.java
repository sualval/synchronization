public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        dealer.start();
        Client client1 = new Client("client1");
        Client client2 = new Client("client2");
        Client client3 = new Client("client3");
        Client client4 = new Client("client4");
        client1.setDealer(dealer);
        client2.setDealer(dealer);
        client3.setDealer(dealer);
        client4.setDealer(dealer);

        new Thread(client1).start();
        new Thread(client2).start();
        new Thread(client3).start();
        new Thread(client4).start();


    }
}

