package ru.oshkina.demo2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClientAExample {
    private JTextField outgoing;
    private PrintWriter writer;
    private Socket socket;

    public void go() {
        //Создаем GUI и подключаем слушатель для событий к кнопке отправки
        //Вызываем метод setUpNetworking()
    }

    public void setUpNetworking() {
        //Создаем сокет и PrintWriter
        //Присваиваем PrintWriter переменной writer
    }

    public class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //получаем текст из текстового поля и отправляем
            //его на сервер с помощью переменной writer (PrintWriter)
        }
    }
}
