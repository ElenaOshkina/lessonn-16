package ru.oshkina.demo1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class CommonAdviceServer {
    private final String[] adviceList = {"Перестань искать оправдания", "Не сомневайся в себе", "Прикладывай 100%-усилия", "Сделайте ДЗ"};

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
            log.error("Exception:", e);
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        CommonAdviceServer server = new CommonAdviceServer();
        server.go();
    }
}
