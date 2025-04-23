package lesson10;

public class LivelockExample {
    static class Spoon {
        private Diner owner;

        public Spoon(Diner d) {
            this.owner = d;
        }

        public Diner getOwner() {
            return owner;
        }

        public synchronized void use() {
            System.out.println(owner.name + " ест!");
        }

        public synchronized void setOwner(Diner d) {
            this.owner = d;
        }
    }

    static class Diner {
        private final String name;
        private boolean isHungry = true;

        public Diner(String name) {
            this.name = name;
        }

        public void eatWith(Spoon spoon, Diner friend) {
            while (isHungry) {
                if (spoon.getOwner() != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignored) {
                    }
                    continue;
                }
                if (friend.isHungry) {
                    System.out.println(name + ": Ты ешь первым, " + friend.name);
                    spoon.setOwner(friend);
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name + ": Я наелся!");
                spoon.setOwner(friend);
            }
        }
    }

    public static void main(String[] args) {
        Diner a = new Diner("Алина");
        Diner b = new Diner("Катя");
        Spoon spoon = new Spoon(a);
        new Thread(() -> a.eatWith(spoon, b)).start();
        new Thread(() -> b.eatWith(spoon, a)).start();
    }
}
