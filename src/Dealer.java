import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Dealer extends Thread {
    static final ReentrantLock locker = new ReentrantLock(true);
    static final Condition condition = locker.newCondition();
    private static final ArrayList<Car> listCar = new ArrayList<>();
    private static final int WAITING_TIME_BETWEEN_SALES = 40;
    private static final int MAX_CARS_TO_BUY = 10;
    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (count < MAX_CARS_TO_BUY) {
            addCar();
        }
        Thread.currentThread().interrupt();
    }

    public void addCar() {
        locker.lock();
        try {
            Thread.sleep(WAITING_TIME_BETWEEN_SALES);
            listCar.add(new Car("Toyota"));
            System.out.println("Добавлен 1 автомобиль " + listCar.get(0).brand);
            condition.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            locker.unlock();
        }
    }

    public void sellCar(Client client) {
        locker.lock();
        try {
            System.out.println(client.name + " зашел в магазин");
            while (listCar.size() != 0) {
                listCar.remove(0);
                System.out.printf("%s Купил авто,в магазине осталось %d%n", client.name, listCar.size());
                count++;
            }
            System.out.println("Авто отсутствует для продажи");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}
