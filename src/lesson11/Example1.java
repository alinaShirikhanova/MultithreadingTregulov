package lesson11;

import java.util.concurrent.locks.ReentrantLock;

public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Поток-1 захватил замок");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Поток-1 прерван во сне");
            } finally {
                lock.unlock();
                System.out.println("Поток-1 освободил замок");
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Поток-2 пытается захватить замок через lockInterruptibly()");
            lock.lock(); // реагирует на interrupt
            try {
                System.out.println("Поток-2 захватил замок");
            } finally {
                lock.unlock();
            }

        });

        t1.start();

        Thread.sleep(100); // ждем, чтобы t1 успел захватить замок
        t2.start();

        Thread.sleep(500); // ждем, чтобы t2 начал ждать
        t2.interrupt();     // Прерываем t2
    }
}
