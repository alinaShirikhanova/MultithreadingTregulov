package lesson18;

import java.util.concurrent.Semaphore;

/**
 * Задача 1: Бассейн с 4 дорожками
 * Симулируйте работу бассейна, где одновременно могут плавать 4 человека.
 * 10 посетителей пытаются попасть в бассейн. Каждый "плавает" от 1 до 3 секунд.
 */
public class Task1 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4, true);

        for (int i = 0; i < 10; i++) {
            final int guestId = i;
            new Thread(() ->
            {
                try {
                    System.out.printf("Гость %d пытается попасть в бассейн%n", guestId);
                    semaphore.acquire();
                    System.out.printf("Гость %d попал в бассейн%n", guestId);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }
            ).start();
        }
    }
}
