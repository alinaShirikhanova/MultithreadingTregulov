package lesson16;

import java.util.List;
import java.util.concurrent.*;

public class f {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService es = Executors.newFixedThreadPool(4);
//// добавили 10 задач
//        for (int i = 0; i < 10; i++) {
//            es.submit(() -> {
//                System.out.println("Task " + Thread.currentThread().getName());
//            });
//        }
//// больше не принимаем задачи
//        es.shutdown();
//// проверим через минуту, что всё отработало
//        if (es.awaitTermination(1, TimeUnit.MINUTES)) {
//            System.out.println("All done");
//        } else {
//            System.out.println("Timeout, some tasks still running");
//        }

//
//// Pitfall: забыли вызвать shutdown() — JVM не завершится:
//        ExecutorService es = Executors.newSingleThreadExecutor();
//        es.submit(() -> doWork());
// без es.shutdown() приложение останется “висеть”
        ExecutorService es = Executors.newCachedThreadPool();
        Future<?> f = es.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }
            return "";
        });
        List<Runnable> notStarted = es.shutdownNow();
        System.out.println("Не начато: " + notStarted.size());


//// Pitfall: отмена неэффективна, если внутри задачи нет точек прерывания:
//        es = Executors.newFixedThreadPool(2);
//        es.submit(() -> {
//            while(true) { /* busy loop */ }
//        });
//        es.shutdownNow(); // поток продолжит «крутиться»

    }
}
