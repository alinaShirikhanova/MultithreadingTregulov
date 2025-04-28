package lesson20;

import lesson7.Example2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Задача 1: Буфер обмена между производителем и потребителем
 * Реализуйте два потока:
 * <p>
 * Producer генерирует список целых чисел длины 5,
 * заполняет его рандомными числами, затем обменивается
 * этим списком через Exchanger с потребителем.
 * <p>
 * Consumer получает заполненный список, выводит
 * его на экран, очищает (вызывает clear()), и
 * обменивается обратно пустым списком.
 * Проведите 3 цикла обмена.
 */
public class Task1 {
    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger();
        new Thread(

                () -> {
                    try {
                        List<Integer> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 5; j++) {
                                list.add(j);
                            }
                            System.out.println("A отправляет список: " + list);
                            List<Integer> reply = exchanger.exchange(list);
                            System.out.println("А получил список: " + reply);

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

        new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 3; i++) {
                            System.out.println("B отправляет пустой список: ");
                            List<Integer> reply = exchanger.exchange(List.of());
                            System.out.println("B получил список: " + reply);

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
    }
}