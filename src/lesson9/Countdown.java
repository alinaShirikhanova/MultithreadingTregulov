package lesson9;

/**
 * Напишите класс Countdown с методом awaitZero(),
 * который ожидает, пока внутреннее число не станет
 * равным 0. Метод decrement() уменьшает число и,
 * если оно равно 0, вызывает notifyAll().
 */
public class Countdown {
    private int count;

    public Countdown(int count) {
        this.count = count;
    }

    public synchronized void decrement() {
        if (count > 0) {
            count--;
            System.out.println("Decremented, count = " + count);
            if (count == 0) {
                notifyAll();
            }
        }
    }

    public synchronized void awaitZero() {
        while (count != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Count is zero, proceeding...");
    }
}

class CountdownDemo {
    public static void main(String[] args) {
        Countdown countdown = new Countdown(5);

        Thread waiter = new Thread(countdown::awaitZero, "Waiter");
        waiter.start();

        Thread decrementer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                countdown.decrement();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        }, "Decrementer");

        decrementer.start();
    }
}
