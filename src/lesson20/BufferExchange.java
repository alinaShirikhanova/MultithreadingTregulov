package lesson20;

import java.util.*;
import java.util.concurrent.*;

public class BufferExchange {
    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        // Producer
        new Thread(() -> {
            List<Integer> buffer1 = new ArrayList<>();
            try {
                for (int cycle = 1; cycle <= 3; cycle++) {
                    buffer1.clear();
                    for (int i = 0; i < 5; i++) {
                        buffer1.add(1 + (int)(Math.random() * 100));
                    }
                    System.out.println("Producer сгенерировал: " + buffer1);
                    // Обмен
                    buffer1 = exchanger.exchange(buffer1);
                    System.out.println("Producer получил обратно: " + buffer1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer
        // Consumer
        new Thread(() -> {
            List<Integer> buffer2 = new ArrayList<>();
            try {
                for (int cycle = 1; cycle <= 3; cycle++) {
                    buffer2 = exchanger.exchange(buffer2);  // ждём заполненный буфер
                    System.out.println("Consumer получил: " + buffer2);
                    buffer2.clear();
                    System.out.println("Consumer очистил буфер");
                    // НЕ вызываем второй exchange
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
