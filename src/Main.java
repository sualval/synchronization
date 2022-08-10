import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        dealer.start();
        List<Client> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new Client("client " + i));
        }

        list.forEach(client -> client.setDealer(dealer));
        list.forEach(client -> new Thread(client).start());


    }
}

