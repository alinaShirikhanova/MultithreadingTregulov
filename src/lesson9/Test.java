package lesson9;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Поток-ожидатель ждёт...");
                    lock.wait();
                    System.out.println("Поток-ожидатель проснулся и захватил монитор!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Уведомляющий поток вызывает notify");
                lock.notify();
                try {
                    Thread.sleep(2000); // симулируем долгую работу — монитор всё ещё захвачен
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Уведомляющий поток завершил работу и вышел из synchronized");
            }
        });

        waiter.start();
        Thread.sleep(500); // даём время потоку waiter войти в wait()
        notifier.start();

    }
}
