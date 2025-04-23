package lesson12;

public class DaemonInheritanceDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread parent = new Thread(() -> {
            System.out.println("Parent isDaemon? " + Thread.currentThread().isDaemon());

            // Создаем дочерний поток внутри демона
            Thread child = new Thread(() -> {
                System.out.println("Child isDaemon? " + Thread.currentThread().isDaemon());
            });

            // Обратите внимание: мы НЕ вызываем child.setDaemon(true);
            child.start();

            try {
                // Ждём, чтобы успеть увидеть вывод child
                child.join();
            } catch (InterruptedException ignored) {}
        });

        // Делаем родительский поток демоном
        parent.setDaemon(true);
        parent.start();

        // Даём демону время запуститься и создать child
        Thread.sleep(500);

        System.out.println("Main thread exiting");
        // После этого, раз у нас нет user‑потоков, JVM завершится вместе с parent и любыми демонами
    }
}