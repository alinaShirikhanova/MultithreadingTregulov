package lesson18;

import java.util.concurrent.Semaphore;

public class Task2 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            int client = i;
            new Thread(
                    ()-> {

                        try {
                            System.out.printf("Клиент %d ждет своего столика%n", client);
                            semaphore.acquire();
                            System.out.printf("Клиент %d дождался своего столика%n", client);
                            Thread.sleep(2000);
                            System.out.printf("Клиент %d уходит%n", client);
                            semaphore.release();

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
            ).start();
        }
    }
}
