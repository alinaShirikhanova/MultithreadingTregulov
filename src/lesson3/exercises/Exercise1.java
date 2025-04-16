package lesson3.exercises;

/**
 *  Создайте два потока, которые выполняют разные задачи
 *  (например, имитируют загрузку данных и вычислительную задачу).
 *  Основной поток должен ждать, пока оба завершатся, используя метод join().
 */

class ThreadEx extends Thread{
    private String taskName;
    private int timeToWait;

    public ThreadEx(String taskName, int timeToWait) {
        this.taskName = taskName;
        this.timeToWait = timeToWait;
    }

    @Override
    public void run() {
        try {
            System.out.println(taskName + " начал работу");
            Thread.sleep(timeToWait);
            System.out.println(taskName + " закончил работу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        ThreadEx thread1 = new ThreadEx("1", 1000);
        ThreadEx thread2 = new ThreadEx("2", 2000);

        System.out.println("Main начал свою работу");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main закончил свою работу");
    }
}
