package lesson3;

public class TimedJoinExample extends Thread {
    @Override
    public void run() {
        System.out.println("Поток " + this.getName() + " запущен и работает 3 секунды.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван.");
        }
        System.out.println("Поток " + this.getName() + " завершён.");
    }

    public static void main(String[] args) {
        TimedJoinExample t = new TimedJoinExample();
        t.start();
        try {
            System.out.println("Главный поток ожидает 1.5 секунды завершения потока t...");
            t.join(1500);
            if (t.isAlive()) {
                System.out.println("Поток t всё ещё работает после 1.5 секунд ожидания.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Главный поток продолжает выполнение, не дожидаясь полного завершения потока t.");
    }
}
