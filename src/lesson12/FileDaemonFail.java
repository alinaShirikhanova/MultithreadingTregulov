package lesson12;

import java.io.IOException;
import java.io.PrintWriter;

public class FileDaemonFail {
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread daemon = new Thread(() -> {
            try (PrintWriter out = new PrintWriter("demo.txt")) {
                out.println("Начало записи");
                out.flush();
                Thread.sleep(2000);
                out.println("Конец записи");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        daemon.setDaemon(true);
        daemon.start();
        daemon.join();

        Thread.sleep(1000); // main завершился раньше
    }
}

