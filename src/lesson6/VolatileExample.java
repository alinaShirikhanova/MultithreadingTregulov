package lesson6;

class ThreadExample extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + " " + i++);;
        }


        System.out.println("The end");
    }
}

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadExample thread1 = new ThreadExample();
        thread1.start();
        ThreadExample thread2 = new ThreadExample();
        thread2.start();
    }
}
