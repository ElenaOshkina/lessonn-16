package ru.oshkina;

public class TestSync implements Runnable {
    private volatile int balance = 1;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            increment();
        }
    }

    public synchronized void increment() {
        int temp = balance;//a 10
        balance = temp * 2;//b 11
        System.out.println("balance = " + balance + " " + Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        final TestSync testSync = new TestSync();

        Thread a = new Thread(testSync);
        Thread b = new Thread(testSync);

        a.start();
        b.start();
    }
}
