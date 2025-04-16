package lesson3;

public class InterruptedExample extends Thread {
    @Override
    public void run() {
        // Устанавливаем искусственно прерывание
        this.interrupt();
        System.out.println("isInterrupted(): " + this.isInterrupted());  // должно быть true
        System.out.println("Thread.interrupted(): " + Thread.interrupted()); // true и сброс
        System.out.println("После сброса isInterrupted(): " + this.isInterrupted()); // false
        System.out.println(this.getId());
    }

    public static void main(String[] args) {
        InterruptedExample t = new InterruptedExample();
        t.start();
    }
}
