package lesson19;

import lesson14.ExecutorServiceDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Есть 3 «рабочих» модуля, каждый должен
 * вернуть своё число. После их работы
 * главная нить должна собрать сумму этих
 * трёх результатов и вывести её.
 */
public class SumCollector {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CountDownLatch latch = new CountDownLatch(3);
        List<Future<Integer>> futures = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            int thread = i;
            futures.add(service.submit(
                    () -> {
                        try {
                            System.out.printf("Поток %d приступил%n", (thread + 1));
                            Thread.sleep(2000);
                            int random = 1 + (int) (Math.random() * 10);
                            System.out.println(random);
                            latch.countDown();
                            return random;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            ));

        }
        latch.await();
        int sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }
        System.out.println(sum);
        /*
        3. Альтернативный вариант без CountDownLatch
Поскольку вы всё равно используете Future, дожидаться завершения можно просто через get():
List<Future<Integer>> futures = …; // как выше
int sum = 0;
for (Future<Integer> f : futures) {
    sum += f.get();  // здесь main-поток заблокируется, пока таск не завершится
}
System.out.println("Сумма = " + sum);
service.shutdown();
         */
    }
}
