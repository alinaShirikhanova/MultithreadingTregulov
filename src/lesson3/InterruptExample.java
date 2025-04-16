package lesson3;

class JoinExample3 extends Thread {
    @Override
    public void run() {
        System.out.println("Поток " + this.getName() + " запущен.");
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.println("Поток был прерван.");
        }
        System.out.println("Поток " + this.getName() + " завершён.");
    }

    public class InterruptExample {
        public static void main(String[] args) {
            JoinExample3 thread = new JoinExample3();
            try {
                thread.join(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
