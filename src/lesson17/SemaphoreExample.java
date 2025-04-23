package lesson17;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore callBox = new Semaphore(2);


    }
}
class Person extends Thread {
    private String name;
}