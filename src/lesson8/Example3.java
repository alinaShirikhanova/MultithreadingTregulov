package lesson8;

public class Example3 {
    static Object object = new Object();

    synchronized void mobileCall() {
        synchronized (object) {
            System.out.println("Mobile call starts");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Mobile call ends");
        }
    }

    synchronized void skypeCall() {
        synchronized (object) {
            System.out.println("skype call starts");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("skype call ends");
        }
    }

    synchronized void whatsAppCall() {
        synchronized (object) {
            System.out.println("WhatsApp call starts");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("WhatsApp call ends");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnableImplMobile());
        Thread thread2 = new Thread(new RunnableImplSkype());
        Thread thread3 = new Thread(new RunnableImplWhatsApp());
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class RunnableImplMobile implements Runnable {
    public void run() {
        new Example3().mobileCall();
    }
}

class RunnableImplSkype implements Runnable {
    public void run() {
        new Example3().skypeCall();
    }
}

class RunnableImplWhatsApp implements Runnable {
    public void run() {
        new Example3().whatsAppCall();
    }
}
