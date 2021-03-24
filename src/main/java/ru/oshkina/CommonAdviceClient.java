package ru.oshkina;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommonAdviceClient {
    public void go() {
        try {
            //создаем соединение через сокет к приложению, работающему на порту 4242
            //на том же компьютере,где выполняется данный код (localhost)
            Socket s = new Socket("127.0.0.1", 4242);

            //Подключаем BufferedReader к InputStreamReader, который уже связан с исходящим потоком сокета
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Сегодня ты должен:" + advice);

            reader.close();//здесь закрываются все потоки

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CommonAdviceClient client = new CommonAdviceClient();
        client.go();
    }
}
