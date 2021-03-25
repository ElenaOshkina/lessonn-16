package ru.oshkina.two.threads;

public class Demo {

    public static void main(String[] args) {
        RunThreads runner = new RunThreads();

        Thread alpha = new Thread(runner);
        Thread beta = new Thread(runner);

        alpha.setName("Alpha thread");
        beta.setName("Beta thread");

        alpha.start();
        beta.start();
    }
}
