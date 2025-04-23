package lesson10;

public class DeadlockEx {
    public final static Object lock1 = new Object();
    public final static Object lock2 = new Object();

    public static void main(String[] args) {
Thread1 thread1 = new Thread1();
Thread2 thread2 = new Thread2();
thread1.start();
thread2.start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Попытка Thread 1 захватить монитор 1");
        synchronized (DeadlockEx.lock1) {
            System.out.println("Thread 1 монитор 1 захвачен");
            System.out.println("Попытка Thread 1 захватить монитор 2");
            synchronized (DeadlockEx.lock2) {
                System.out.println("Thread 1 монитор 2 захвачен");
            }
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        System.out.println("Попытка Thread 2 захватить монитор 2");
        synchronized (DeadlockEx.lock2) {
            System.out.println("Thread 2 монитор 2 захвачен");
            System.out.println("Попытка Thread 2 захватить монитор 1");
            synchronized (DeadlockEx.lock1) {
                System.out.println("Thread 2 монитор 1 захвачен");
            }
        }
    }
}
