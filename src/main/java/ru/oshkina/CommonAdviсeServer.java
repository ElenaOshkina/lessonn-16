package ru.oshkina;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CommonAdviсeServer {
    private final String[] adviceList = {"Ешьте меньшими порциями", "Купите джинсы", "Будьте честны хотя бы сегодня", "Сделайте ДЗ"};

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            //сервер входит в постоянный цикл, ожидая клиентских подключений (и обслуживая их)
            while (true) {
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        CommonAdviсeServer server = new CommonAdviсeServer();
        server.go();
    }
}
