package lesson23;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PageViewService {
    private final ConcurrentHashMap<String, AtomicInteger> pageViews = new ConcurrentHashMap<>();

    public void viewPage(String pageUrl) {
        pageViews.computeIfAbsent(pageUrl, k -> new AtomicInteger(0)).incrementAndGet();
    }

    public int getViews(String pageUrl) {
        return pageViews.getOrDefault(pageUrl, new AtomicInteger(0)).get();
    }
}
