package lesson3.exercises;

/**
 *  Создайте поток, который генерирует исключение в методе run().
 *  Установите обработчик необработанных исключений, который
 *  выведет подробную информацию о случившемся исключении.
 */
public class Exercise3 {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Доступно процессоров: " + processors);
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        thread.setUncaughtExceptionHandler((t, e)-> System.out.println(t + " " + e.getMessage()));
        thread.start();
    }
}
