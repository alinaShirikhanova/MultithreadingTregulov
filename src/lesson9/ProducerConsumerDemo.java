package lesson9;

class Data {
    private String packet;
    private boolean available = false;

    public synchronized void produce(String data) {
        while (available) { // если данные ещё не потреблены
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        packet = data;
        available = true;
        System.out.println("Produced: " + packet);
        notify(); // уведомление потребителя
    }

    public synchronized String consume() {
        while (!available) { // если данные отсутствуют
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        available = false;
        System.out.println("Consumed: " + packet);
        notify(); // уведомление производителя
        return packet;
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Data data = new Data();

        Thread producer = new Thread(() -> {
            String[] packets = { "1", "2", "3", "4", "5" };
            for (String p : packets) {
                data.produce(p);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                data.consume();
                try { Thread.sleep(800); } catch (InterruptedException e) {}
            }
        });

        producer.start();
        consumer.start();
    }
}

