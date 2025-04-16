package lesson3;


public class StackTraceExample extends Thread {
    @Override
    public void run() {
        StackTraceElement[] stack = this.getStackTrace();
        System.out.println("Стек вызовов потока " + this.getName() + ":");
        for (StackTraceElement element : stack) {
            System.out.println("  " + element);
        }
    }

    public static void main(String[] args) {
        StackTraceExample t = new StackTraceExample();
        t.start();
    }
}
