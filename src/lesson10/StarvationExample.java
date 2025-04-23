package lesson10;

public class StarvationExample {
    private final Object lock = new Object();

    public void runThread(String name) {
        while (true) {
            synchronized (lock) {
                System.out.println(name + " получил доступ");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        }
    }

    public static void main(String[] args) {
        StarvationExample ex = new StarvationExample();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> ex.runThread("Рабочий поток")).start();
        }

        Thread starved = new Thread(() -> ex.runThread("Голодающий поток"));
        starved.setPriority(Thread.MIN_PRIORITY); // Пониженный приоритет
        starved.start();
    }
}