package lesson8;

public class Example1 {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Counter {
    static int count;
}

class MyRunnable implements Runnable {
    private void doWork2(){
        System.out.println("Ура");
    }
    public void doWork1() {
        doWork2();
        synchronized (this) {
            Counter.count++;
            System.out.println(Counter.count);
        }
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            doWork1();
        }
    }
}