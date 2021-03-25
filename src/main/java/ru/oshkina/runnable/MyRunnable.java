package ru.oshkina.runnable;

public class MyRunnable implements Runnable {

    @Override
    public void run() { //именно сюда помещается задача, которую поток должен выполнять

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        go();
    }

    public void go() {
        doMore();
    }

    public void doMore() {
        System.out.println("Вершина стека");
    }
}

