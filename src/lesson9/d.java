package lesson9;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.List;

/**
 *  Допустим, несколько потоков ждут изменения
 *  общего состояния (например, поступления
 *  данных в список). Реализуйте класс DataBuffer
 *  с методами для добавления и получения данных,
 *  где получатели ждут, пока список не станет не пустым.
 */
class DataBuffer {
    private List<String> list = new ArrayList<>();

    public synchronized void getData(){
        while(list.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " получил и очистил список: "+ list);
        list.clear();
        notifyAll();
    }

    public synchronized void putData(List<String> data){
        while (!list.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.addAll(data);
        System.out.println(Thread.currentThread().getName() + " заполнил список");
        notifyAll();
    }

}

class DataBufferDemo{
    public static void main(String[] args) {
        DataBuffer dataBuffer = new DataBuffer();
        Thread waiter1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                dataBuffer.getData();
            }
        }, "Waiter 1");
        Thread waiter2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                dataBuffer.getData();
            }
        }, "Waiter 2");
        waiter1.start();
        waiter2.start();

        Thread notifier = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                dataBuffer.putData(List.of("1", "2", "3"));
            }
        }, "Notifier");

       notifier.start();
    }
}
