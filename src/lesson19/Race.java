package lesson19;

import java.util.concurrent.CountDownLatch;

public class Race {
    public static void main(String[] args) throws InterruptedException {
        int runners = 5;
        CountDownLatch readyLatch = new CountDownLatch(runners);
        CountDownLatch startLatch = new CountDownLatch(1);

        for (int i = 1; i <= runners; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    // Подготовка
                    Thread.sleep(500 + (long)(Math.random() * 1000));
                    System.out.printf("Участник %d готов.%n", id);
                    readyLatch.countDown();

                    // Ждём сигнала старта
                    startLatch.await();
                    System.out.printf("Участник %d бежит!%n", id);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        // Ждём, пока все будут готовы
        readyLatch.await();
        System.out.println("Все готовы! На старт...");
        startLatch.countDown();  // «Старт!»
    }
}
