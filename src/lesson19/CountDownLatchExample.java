package lesson19;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        new Friend("Алексей1", countDownLatch);
        new Friend("Алексей2", countDownLatch);
        new Friend("Алексей3", countDownLatch);
        new Friend("Алексей4", countDownLatch);
        new Friend("Алексей5", countDownLatch);

        marketStaffIsOnPlace();
        everythingIsReady();
        openMarket();
    }
    static CountDownLatch countDownLatch = new CountDownLatch(3);
    private static void marketStaffIsOnPlace() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Market staff is on place");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    private static void everythingIsReady() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Everything is ready, so let's open the market");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    private static void openMarket() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Market is  opened");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }
}

class Friend extends Thread {
    private String name;
    private CountDownLatch countDownLatch;
    public Friend(String name, CountDownLatch countDownLatch){
        this.name = name;
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.printf("%s Заходит%n", name);

        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}