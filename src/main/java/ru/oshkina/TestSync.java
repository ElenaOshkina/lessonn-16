package ru.oshkina;

public class TestSync implements Runnable {
    private int balance = 1; //todo: подумать как сделать так, чтобы переменная не кешировалась в каждом потоке

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            increment();
            System.out.println("balance = " + balance);
        }
    }

    public synchronized void increment() {
        int temp = balance;//a 10
        balance = temp * 2;//b 11
    }


    public static void main(String[] args) {
        final TestSync testSync = new TestSync();

        Thread a = new Thread(testSync);
        Thread b = new Thread(testSync);

        a.start();
        b.start();
    }
}
