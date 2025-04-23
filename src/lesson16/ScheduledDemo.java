package lesson16;

import java.time.LocalTime;
import java.util.concurrent.*;

public class ScheduledDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
// 1) Однократная отложенная задача
        ses.schedule(() -> System.out.println("Запуск через 5 сек"), 5, TimeUnit.SECONDS);

// 2) Фиксированная частота
        ses.scheduleAtFixedRate(() -> {
            System.out.println("Heartbeat " + LocalTime.now());
        }, 0, 1, TimeUnit.SECONDS);


// 3) Повтор с задержкой
        ses.scheduleWithFixedDelay(() -> {
            doLongTask();
            System.out.println("Снова через 2 сек");
        }, 0, 2, TimeUnit.SECONDS);

// 4) Отложенный Callable
        Future<String> f = ses.schedule(() -> "Done", 3, TimeUnit.SECONDS);
        System.out.println(f.get());

    }

    private static void doLongTask() {

    }
}
