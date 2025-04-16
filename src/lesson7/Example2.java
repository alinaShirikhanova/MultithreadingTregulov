package lesson7;

class R implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Example2.increment();
        }
    }
}
public class Example2 {
    static int counter = 0;
    public synchronized static void increment(){
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new R());
        Thread thread2 = new Thread(new R());
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);
    }
}
