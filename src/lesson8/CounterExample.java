package lesson8;

public class CounterExample {
    private int counter = 0;

    // Синхронизированный метод для увеличения значения
    public synchronized void increment() {
        counter++;
    }

    // Синхронизированный метод для уменьшения значения
    public  void decrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        CounterExample example = new CounterExample();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                example.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                example.decrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Ожидаемое значение 0.
        System.out.println("Final counter value: " + example.getCounter());
    }
}
