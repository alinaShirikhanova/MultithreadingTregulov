package lesson17;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Параллельно вычислить 1 000 факториалов
 * и найти максимальный результат.
 */
public class Task1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BigInteger max = BigInteger.ZERO;
        List<Future<BigInteger>> futures = new ArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 1; i < 1001; i++) {
            final int n = i;  // «запоминаем» текущее значение i в final-переменной
            futures.add(service.submit(() -> getFactorial(n)));
        }

        for (Future<BigInteger> future : futures) {
            BigInteger value = future.get();
            if (value.compareTo(max) > 0) {
                max = value;
            }

        }
        service.shutdown();
        System.out.println("Максимальный факториал из 1…1000 = " + max);
    }

    public static BigInteger getFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}

