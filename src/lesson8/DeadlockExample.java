package lesson8;

public class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    // Метод, где захват происходит сначала lock1, затем lock2.
    public void methodA() {
        synchronized (lock1) {
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            synchronized (lock2) {
                System.out.println("Method A executed");
            }
        }
    }

    // Метод, где захват происходит сначала lock2, затем lock1, что может привести к deadlock.
    public void methodB() {
        synchronized (lock2) {
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            synchronized (lock1) {
                System.out.println("Method B executed");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockExample example = new DeadlockExample();
        Thread t1 = new Thread(example::methodA);
        Thread t2 = new Thread(example::methodB);
        t1.start();
        t2.start();
    }
}
