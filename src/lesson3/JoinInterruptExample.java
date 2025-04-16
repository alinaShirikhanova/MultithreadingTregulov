package lesson3;

public class JoinInterruptExample extends Thread {
    @Override
    public void run() {
        System.out.println("Поток " + this.getName() + " запущен.");
        try {
            // Поток засыпает на 2000 мс (2 секунды)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Поток " + this.getName() + " был прерван во время сна.");
        }
        System.out.println("Поток " + this.getName() + " завершён.");
    }

    public static void main(String[] args) {
        JoinInterruptExample t = new JoinInterruptExample();
        t.start();

        // Захватываем ссылку на главный поток (тот, в котором выполняется main())
        Thread mainThread = Thread.currentThread();

        // Создаем отдельный поток, который через 1 секунду прервет главный поток.
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Эта обработка здесь не критична
            }
            System.out.println("Интерраптер: прерываю главный поток.");
            mainThread.interrupt();  // Прерываем главный поток
        }).start();

        try {
            System.out.println("Главный поток ожидает завершения потока t...");
            // Главный поток входит в ожидание завершения потока t.
            // Но когда на него придет сигнал прерывания, join() выбросит InterruptedException.
            t.join();
        } catch (InterruptedException e) {
            System.out.println("Главный поток был прерван во время ожидания join(): " + e);
        }

        System.out.println("Главный поток продолжает выполнение.");
    }
}
