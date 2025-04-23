package lesson17;

import java.util.concurrent.*;

/**
 * Запустить задачу с таймаутом 3 сек:
 * если не успела, прервать и вернуть дефолт.
 */
public class Task3 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> future = es.submit(() -> longRunningTask());
        try {
            future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("Не успела");
        }
        es.shutdown();

    }

    private static String longRunningTask() throws InterruptedException {
        Thread.sleep(2000);
        return null;
    }
}
