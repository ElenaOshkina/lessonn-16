package ru.oshkina.two.threads;

public class RunThreads implements Runnable {

    public void run() {
        for (int i = 0; i < 25; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is running");
        }
    }
}
