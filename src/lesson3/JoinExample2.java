package lesson3;

public class JoinExample2 extends Thread {
    @Override
    public void run() {
        System.out.println("Поток " + this.getName() + " запущен.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван.");
        }
        System.out.println("Поток " + this.getName() + " завершён.");
    }

    public static void main(String[] args) {
        JoinExample2 t = new JoinExample2();
        t.start();
        try {
            System.out.println("Главный поток ожидает завершения потока t...");
            t.join();   // Ожидается завершение потока t
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Главный поток продолжает выполнение.");
    }
}
