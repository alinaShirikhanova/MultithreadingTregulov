package lesson20;

import lesson7.Example2;

import java.lang.ref.PhantomReference;
import java.util.List;
import java.util.concurrent.Exchanger;

import static lesson20.Action.*;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        Friend alina = new Friend("Алина", List.of(PAPER, ROCK, ROCK), exchanger);
        Friend alex = new Friend("Алексей", List.of(ROCK, ROCK, SCISSORS), exchanger);
        alina.start();
        alex.start();
    }
}

enum Action {
    PAPER, SCISSORS, ROCK
}

class Friend extends Thread {
    private String name;
    private List<Action> actions;
    Exchanger<Action> exchanger;

    public Friend(String name, List<Action> actions, Exchanger exchanger) {
        this.name = name;
        this.actions = actions;
        this.exchanger = exchanger;
    }

    private void whoWins(Action myAction, Action friendAction) {
        if (myAction.equals(PAPER) && friendAction.equals(ROCK) ||
                myAction.equals(ROCK) && friendAction.equals(SCISSORS) ||
                myAction.equals(SCISSORS) && friendAction.equals(PAPER)){
            System.out.printf("%s Выиграл!%n", name);

        }
    }

    public void run() {
        Action reply;
        for (Action action : actions) {
            try {
                reply = exchanger.exchange(action);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            whoWins(action, reply);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
