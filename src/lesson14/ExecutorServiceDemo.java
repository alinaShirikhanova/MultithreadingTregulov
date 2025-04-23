package lesson14;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        Callable<String> task1 = () -> {
            Thread.sleep(1000);
            return "task1";
        };
        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "task2";
        };
        Callable<String> task3 = () -> {
            Thread.sleep(1000);
            return "task3";
        };
        Callable<String> task4 = () -> {
            Thread.sleep(1000);
            return "task4";
        };
        Callable<String> task5 = () -> {
            Thread.sleep(1000);
            return "task5";
        };
        try (ExecutorService service = Executors.newFixedThreadPool(3)) {
            Future<String> submit1 = service.submit(task1);
            Future<String> submit2 = service.submit(task2);

            Future<String> submit3 = service.submit(task3);
            Future<String> submit4 = service.submit(task4);
            Future<String> submit5 = service.submit(task5);
            submit5.cancel(true);
            try {
                System.out.println(submit1.get());
                System.out.println(submit2.get());
                System.out.println(submit3.get());
                System.out.println(submit4.get());
                System.out.println(submit5.get());
System.out.println();
            } catch (InterruptedException e) {
                System.out.println("Прервали");
            } catch (ExecutionException e) {
                System.out.println(e.getCause().getMessage());
            }


        }


    }
}
