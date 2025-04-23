package lesson12;

import java.io.FileWriter;
import java.io.IOException;

public class DaemonShutdownExample {
    public static void main(String[] args) throws InterruptedException {

                Thread daemon = new Thread(() -> {
                    try {
                        FileWriter writer = new FileWriter("log.txt");
                        writer.write("Запись начата...");
                        writer.flush();
                        Thread.sleep(2000); // long task
                        writer.write("Запись завершена.");
                        writer.close();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Выполняется блок finally демон-потока");
                    }
                });
                daemon.setDaemon(true);
                daemon.start();

                Thread.sleep(1000); // main завершается раньше
            }
}

