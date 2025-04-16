package lesson3.exercises;

/**
 * Напишите поток, который выполняет непрерывный цикл,
 * периодически выводя сообщение. Основной поток должен
 * прервать выполнение через 3 секунды.
 */
public class Exercise2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(i++);
            }
            System.out.println("Завершаюсь");
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("Пытаюсь прервать поток...");
        thread.interrupt();
        System.out.println("Main завершился");
    }
}
