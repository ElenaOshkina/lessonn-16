package ru.oshkina.demo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;

public class SimpleChatClientA {
    private JTextField outgoing;
    private PrintWriter writer;
    private Socket socket;

    //Создаем GUI и подключаем слушатель для событий к кнопке отправки
    //Вызываем метод setUpNetworking()
    public void go() {
        JFrame frame = new JFrame("Simple Chat Client");
        JPanel minPanel = new JPanel();
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        minPanel.add(outgoing);
        minPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, minPanel);
        setUpNetworking();
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    //Создаем сокет и PrintWriter
    //Присваиваем PrintWriter переменной writer
    public void setUpNetworking() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Соединение установлено");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class SendButtonListener implements ActionListener {
        //получаем текст из текстового поля и отправляем
        //его на сервер с помощью переменной writer (PrintWriter)
        @Override
        public void actionPerformed(ActionEvent e) {
            //именно здесь начинаеттся непосредственная запись
            //Помните, что переменная writer подключена к входящему потоку из сокета,
            //поэтому при каждом вызове println() данные передаются по сети на сервер
            try {
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args){
        new SimpleChatClientA().go();
    }
}
