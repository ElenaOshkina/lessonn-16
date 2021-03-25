package ru.oshkina.music;

import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


@Slf4j
public class MusicServer {

    private ArrayList<ObjectOutputStream> clientOutputStreams;

    public static void main(String[] args) {
        new MusicServer().go();
    }

    public class ClientHandler implements Runnable {
        ObjectInputStream in;
        Socket sock;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                in = new ObjectInputStream(sock.getInputStream());

            } catch (Exception ex) {
                log.error("Exception:", ex);
            }
        }

        public void run() {
            Object o1;
            Object o2;
            try {
                while ((o1 = in.readObject()) != null) {
                    o2 = in.readObject();
                    System.out.println("read two objects");
                    tellEveryone(o1, o2);
                }
            } catch (Exception ex) {
                log.error("Exception:", ex);
            }
        }
    }


    public void go() {
        clientOutputStreams = new ArrayList<>();
        try {
            ServerSocket serverSock = new ServerSocket(4242);
            while (true) {
                Socket clientSocket = serverSock.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutputStreams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        } catch (Exception ex) {
            log.error("Exception:", ex);
        }
    }

    public void tellEveryone(Object one, Object two) {
        Iterator<ObjectOutputStream> iterator = clientOutputStreams.iterator();
        while (iterator.hasNext()) {
            try {
                ObjectOutputStream out = iterator.next();
                out.writeObject(one);
                out.writeObject(two);
            } catch (Exception ex) {
                log.error("Exception:", ex);
            }
        }
    }
}
