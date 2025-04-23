package lesson11;

import java.util.concurrent.locks.ReentrantLock;

public class LockFairnessDemo {

    // Сначала попробуем с fair = true или false
    static ReentrantLock lock = new ReentrantLock(true); // меняй на false для сравнения

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            final int threadNum = i;
            new Thread(() -> {
                try {
                    Thread.sleep(threadNum * 100); // немного сдвинем старт
                    System.out.println("Поток " + threadNum + " пытается захватить замок");
                    lock.lock();
                    System.out.println("Поток " + threadNum + " захватил замок");
                    Thread.sleep(500); // держим замок
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Поток " + threadNum + " освобождает замок");
                    lock.unlock();
                }
            }).start();
        }
    }
}

