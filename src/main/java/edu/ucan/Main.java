package edu.ucan;


class ExtendsThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() +  " extends Thread class!");
    }
}
class ImplementsRunnable implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " implements Runnable interface");
    }
}


public class Main {
    public static void main(String[] args) {

        ExtendsThread t0 = new ExtendsThread();
        t0.start();

        Thread t1 = new Thread(new ImplementsRunnable());
        t1.start();

        Thread lambda = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +  " usando lambda");
        });
        lambda.start();

    }
}