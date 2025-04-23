package lesson13;

public class CounterDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread counter = new Thread(() -> {
            int i = 1;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(i++);
                    Thread.sleep(1_000);
                }
            } catch (InterruptedException e) {
                // завершаем работу
            }
            System.out.println("Counter stopped");
        });
        counter.start();
        Thread.sleep(5_000);
        counter.interrupt();
    }
}
