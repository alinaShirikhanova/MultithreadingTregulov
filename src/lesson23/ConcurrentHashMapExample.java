package lesson23;

import java.util.HashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "fjd");
        map.put(2, null);
        map.put(null, null);
        System.out.println(map.get(1));
    }
}
