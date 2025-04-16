package lesson3;

public class ExceptionHandlerExample extends Thread {
    @Override
    public void run() {
        throw new RuntimeException("Ошибка в потоке!");
    }

    public static void main(String[] args) {
        ExceptionHandlerExample t = new ExceptionHandlerExample();
        t.setUncaughtExceptionHandler(
                (t1, e) ->
                        System.out.println("Обработчик исключений: Поток " + t1.getName()
                                + " выбросил исключение: " + e.getMessage())
        );
        t.start();
    }
}
