import java.util.ArrayList;

public class Dealer extends Thread {
    private static final ArrayList<Car> listCar = new ArrayList<>();
    private static final int WAITING_TIME_BETWEEN_SALES = 50;
    private static final int MAX_CARS_TO_BUY = 10;
    private int count = 0;


    @Override
    public void run() {
        while (count < MAX_CARS_TO_BUY) {
            addCar();
            try {
                Thread.sleep(WAITING_TIME_BETWEEN_SALES);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        currentThread().interrupt();
    }

    public synchronized void addCar() {

        listCar.add(new Car("Toyota"));
        System.out.println("Добавлен 1 автомобиль " + listCar.get(0).brand);
        notify();
    }

    public synchronized void sellCar(Client client) {
        System.out.println(client.name + " зашел в магазин");
        while (listCar.size() != 0) {
            listCar.remove(0);
            System.out.printf("%s Купил авто,в магазине осталось %d%n", client.name, listCar.size());
            count++;
        }
        System.out.println("Авто отсутствует для продажи");
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}