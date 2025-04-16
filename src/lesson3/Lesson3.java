package lesson3;

public class Lesson3 {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("Thread main started");
//        MyThread5 thread5 = new MyThread5();
//        thread5.start();
//        thread5.join(2000);
//        System.out.println("Thread main ended");
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(500);
                // Меняем имя внутри потока
                Thread.currentThread().setName("Updated-Thread");
                System.out.println("Новое имя потока: " + Thread.currentThread().getName() + Thread.currentThread().getPriority());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setName("Initial-Thread");
        t.start();
    }
}

class MyThread5 extends Thread {
    public void run() {
        System.out.println("Thread 0 started");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread 0 ended");
    }
}