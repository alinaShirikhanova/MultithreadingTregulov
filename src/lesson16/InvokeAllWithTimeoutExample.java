package lesson16;

import java.util.*;
import java.util.concurrent.*;

public class InvokeAllWithTimeoutExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = Arrays.asList(
                () -> {
                    Thread.sleep(1000);
                    return "Task 1 done";
                },
                () -> {
                    Thread.sleep(3000);
                    return "Task 2 done";
                },
                () -> {
                    Thread.sleep(5000);
                    return "Task 3 done";
                }
        );

        // Устанавливаем timeout = 4 секунды
        List<Future<String>> futures = executor.invokeAll(tasks);
        executor.shutdown();
        executor.submit(() -> System.out.println("Hello"));
        for (int i = 0; i < futures.size(); i++) {
            Future<String> future = futures.get(i);
            try {
                if (future.isCancelled()) {
                    System.out.println("Task " + (i + 1) + " was cancelled due to timeout.");
                } else {
                    String result = future.get();  // get() не будет блокировать, если задача завершена
                    System.out.println("Result of task " + (i + 1) + ": " + result);
                }
            } catch (ExecutionException e) {
                System.out.println("Task " + (i + 1) + " failed: " + e.getMessage());
            }
        }

        executor.shutdown();
    }
}
