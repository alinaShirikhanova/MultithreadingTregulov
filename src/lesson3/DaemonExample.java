package lesson3;

public class DaemonExample extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Демон-поток " + this.getName() + " работает в фоне...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
//        DaemonExample daemonThread = new DaemonExample();
//        daemonThread.setDaemon(true);
//        daemonThread.start();
//
//        System.out.println("Главный поток завершает работу. Демон-поток прервётся автоматически.");

        Thread thread = new Thread();
        System.out.println(thread.getState()); // NEW
        }
    }
