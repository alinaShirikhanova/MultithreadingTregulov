package lesson3;

public class StateExample extends Thread {
    @Override
    public void run() {
        System.out.println("Состояние в начале выполнения: " + this.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        System.out.println("Состояние перед завершением: " + this.getState());
    }

    public static void main(String[] args) {
        StateExample t = new StateExample();
        System.out.println("Состояние до запуска: " + t.getState());  // NEW
        t.start();
        try {
            Thread.sleep(500);
            System.out.println("Состояние после запуска: " + t.getState());
            t.join();
        } catch (InterruptedException e) { }
        System.out.println("Состояние после завершения: " + t.getState());  // TERMINATED
    }
}
