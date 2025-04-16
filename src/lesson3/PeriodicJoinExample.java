package lesson3;

public class PeriodicJoinExample extends Thread {
    @Override
    public void run() {
        System.out.println("Начало долгой задачи в потоке " + this.getName());
        try {
            Thread.sleep(4000); // имитируем долгую операцию
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван.");
        }
        System.out.println("Задача завершена в потоке " + this.getName());
    }

    public static void main(String[] args) {
        PeriodicJoinExample t = new PeriodicJoinExample();
        t.start();
        while (t.isAlive()) {
            try {
                // Каждые 1 секунду проверять состояние потока
                t.join(1000);
                System.out.println("Все ещё ожидаем завершения потока " + t.getName());
            } catch (InterruptedException e) {
                System.out.println("Главный поток был прерван во время ожидания.");
            }
        }
        System.out.println("Поток завершился. Главный поток продолжает выполнение.");
    }
}

