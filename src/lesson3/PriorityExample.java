package lesson3;

public class PriorityExample extends Thread {
    public PriorityExample(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println( i + ". Поток " + this.getName() + " с приоритетом " + this.getPriority() + " выполняется. ");
        }

    }

    public static void main(String[] args) {
        PriorityExample highPriorityThread = new PriorityExample("HighPriority");
        PriorityExample lowPriorityThread = new PriorityExample("LowPriority");

        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);

        highPriorityThread.start();
        lowPriorityThread.start();
    }
}
