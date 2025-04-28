package lesson23;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class WeaklyConsistentDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        // Инициализируем 0…4
        for (int i = 0; i < 5; i++) {
            map.put(i, "val" + i);
        }

        Iterator<Integer> iter = map.keySet().iterator();

        // Поток, который будет модифицировать карту
        Thread modifier = new Thread(() -> {
            // Немного подождём, чтобы итерация успела начаться
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            map.put(5, "val5");   // добавили новый ключ
            map.remove(4);        // удалили старый
        });
        modifier.start();

        // Итерация в главном потоке
        while (iter.hasNext()) {
            Integer key = iter.next();
            System.out.print(key + " ");
            // Имитируем долгую обработку каждого элемента
            try { Thread.sleep(0); } catch (InterruptedException ignored) {}
        }
        System.out.println("\nИтоговая карта: " + map);
    }
}
