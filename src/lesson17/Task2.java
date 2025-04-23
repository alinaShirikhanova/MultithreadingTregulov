package lesson17;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Организовать повторяющуюся задачу:
 * каждые 10 сек отправлять «heartbeat» в мониторинг.
 */
public class Task2 {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleAtFixedRate(()-> System.out.println("heartbeat"), 0, 10, TimeUnit.MILLISECONDS);
        service.shutdown();
    }
}
