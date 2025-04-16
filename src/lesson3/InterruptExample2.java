package lesson3;

public class InterruptExample2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Поток " + this.getName() + " засыпает на 5 секунд.");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Поток " + this.getName() + " был прерван во время сна.");
        }
    }

    public static void main(String[] args) {
        InterruptExample2 t = new InterruptExample2();
        t.start();
        try {
            Thread.sleep(2000);  // даём потоку поработать
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Главный поток прерывает поток " + t.getName());
        t.interrupt();
    }
}
