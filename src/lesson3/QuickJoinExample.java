package lesson3;

public class QuickJoinExample extends Thread {
    @Override
    public void run() {
        System.out.println("Быстрый поток " + this.getName() + " завершён.");
    }

    public static void main(String[] args) {
        QuickJoinExample t = new QuickJoinExample();
        t.start();
        try {
            t.join();   // Поток уже выполнил run(), join() вернётся моментально
            System.out.println("Join() завершился мгновенно, так как поток уже завершён.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

