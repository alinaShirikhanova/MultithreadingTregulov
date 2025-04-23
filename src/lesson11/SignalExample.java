package lesson11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SignalExample {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void waitForSignal() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ждёт сигнала...");
            condition.await(); // поток отпускает замок и ждёт сигнала
            System.out.println(Thread.currentThread().getName() + " получил сигнал и продолжает работу.");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " был прерван во время ожидания.");
        } finally {
            lock.unlock();
        }
    }

    public void sendSignal() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " подаёт сигнал...");
            condition.signal(); // пробуждает один ожидающий поток
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SignalExample example = new SignalExample();

        // Поток, который будет ждать сигнала
        Thread waiter = new Thread(example::waitForSignal, "Ожидающий поток");

        // Поток, который отправит сигнал через 2 секунды
        Thread signaler = new Thread(() -> {
            try {
                Thread.sleep(2000); // имитируем задержку
                example.sendSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Сигналящий поток");

        waiter.start();
        signaler.start();
    }
}
