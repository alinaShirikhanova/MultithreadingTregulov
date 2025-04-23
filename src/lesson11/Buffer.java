package lesson11;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int bufferSize;
    private int size;
    private int[] buffer;
    private int readPos;
    private int writePos;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(int bufferSize) {
        this.bufferSize = bufferSize;
        this.buffer = new int[bufferSize];
    }


    public boolean acquireBoth(ReentrantLock lockA, ReentrantLock lockB) throws InterruptedException {
        int timeout = 1_000;
        long start = System.currentTimeMillis();
        if(!lockA.tryLock(timeout, TimeUnit.MILLISECONDS)){
            return false;
        }
        long elapsed = System.currentTimeMillis();
        long delta = elapsed - start;
        return timeout > delta && lockB.tryLock(delta, TimeUnit.MILLISECONDS);
    }


        public void put(int element) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (size == bufferSize) {
                notFull.await();
            }
            buffer[writePos] = element;
            writePos = (writePos + 1) % bufferSize;
            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (size == 0) {
                notEmpty.await();
            }
           int elem= buffer[readPos];
            System.out.println(elem);
            readPos = (readPos + 1) % bufferSize;
            size--;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
