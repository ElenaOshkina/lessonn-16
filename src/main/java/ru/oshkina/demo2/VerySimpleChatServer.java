package ru.oshkina.demo2;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class VerySimpleChatServer {
    List<PrintWriter> clientOutputStreams;

    public class ClientHandler implements Runnable {
        private BufferedReader reader;

        public ClientHandler(Socket clientSocket) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                reader = new BufferedReader(inputStreamReader);
            } catch (Exception ex) {
                log.error("Exception:", ex);
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Прочитано:" + message);
                    toTellEveryOne(message);
                }
            } catch (Exception ex) {
                log.error("Exception:", ex);
            }
        }
    }

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }

    public void go() {
        clientOutputStreams = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Соединение с клиентом установлено:");
            }

        } catch (Exception ex) {
            log.error("Exception:", ex);
        }
    }

    public void toTellEveryOne(String message) {
        final Iterator<PrintWriter> it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception ex) {
                log.error("Exception:", ex);
            }
        }
    }
}
