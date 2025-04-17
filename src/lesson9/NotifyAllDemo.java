package lesson9;

class SharedCounter {
    private int count = 0;
    private final int LIMIT = 1000;

    public synchronized void increment() {
        count++;
        System.out.println("Incremented: " + count);
        // Как только достигнут предел, пробуждаем все ожидающие потоки
        if (count >= LIMIT) {
            notifyAll();
        }
    }

    public synchronized void waitForLimit() {
        while (count < LIMIT) {
            try {
                System.out.println(Thread.currentThread().getName() + " ждет, count=" + count);
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " пробужден, count=" + count);
    }
}

public class NotifyAllDemo {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();

        // Потоки-ожидальщики
        Runnable waiter = () -> {
            counter.waitForLimit();
        };

        Thread t1 = new Thread(waiter, "Waiter 1");
        Thread t2 = new Thread(waiter, "Waiter 2");

        // Поток-увеличиватель
        Thread incrementer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();

            }
        }, "Incrementer");

        t1.start();
        t2.start();
        incrementer.start();
    }
}

