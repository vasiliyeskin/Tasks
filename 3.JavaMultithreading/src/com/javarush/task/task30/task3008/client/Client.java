package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Vaisiliy Es'kin on 03/14/17.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();

        BotClient botClient = new BotClient();
        botClient.run();
    }

    public void run() {
        //Реализация метода run должна:
        //  а) Создавать новый сокетный поток с помощью метода getSocketThread.
        SocketThread socketThread = getSocketThread();
        //  б) Помечать созданный поток как daemon, это нужно для того, чтобы при выходе из программы вспомогательный поток прервался автоматически.
        socketThread.setDaemon(true);
        //  в) Запустить вспомогательный поток.
        socketThread.start();
        //     г) Заставить текущий поток ожидать, пока он не получит нотификацию из другого потока.
        // Подсказка: используй wait и синхронизацию на уровне объекта. Если во время ожидания
        // возникнет исключение, сообщи об этом пользователю и выйди из программы.

        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Ошибка");
            return;
        }

        //      д) После того, как поток дождался нотификации, проверь значение clientConnected.
        // Если оно true – выведи «Соединение установлено. Для выхода наберите команду ‘exit’.«.
        // Если оно false – выведи «Произошла ошибка во время работы клиента.».
        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");

            //  е) Считывай сообщения с консоли пока клиент подключен. Если будет введена
            // команда ‘exit‘, то выйди из цикла.
            //  ж) После каждого считывания, если метод shouldSendTextFromConsole() возвращает true,
            // отправь считанный текст с помощью метода sendTextMessage().
            while (clientConnected) {
                String message;
                if (!(message = ConsoleHelper.readString()).equals("exit")) {
                    if (shouldSendTextFromConsole()) {
                        sendTextMessage(message);
                    }
                } else {
                    return;
                }
            }

        } else
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");


    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера: ");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера: ");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя: ");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));

        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка отправки");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("участник " + userName + " присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("участник " + userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;

            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();

                if (message.getType() == MessageType.NAME_REQUEST) {

                    // 	Если тип полученного сообщения NAME_REQUEST (сервер запросил имя)
                    // запросить ввод имени пользователя с помощью метода getUserName()
                    // создать новое сообщение с типом USER_NAME и введенным именем, отправить сообщение серверу.
                    String userName = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, userName));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    // Если тип полученного сообщения NAME_ACCEPTED (сервер принял имя)

                    // значит сервер принял имя клиента, нужно об этом сообщить главному потоку, он этого очень ждет.
                    // Сделай это с помощью метода notifyConnectionStatusChanged(), передав в него true. После этого выйди из метода.
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }


        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            while (true) {

                // В цикле получать сообщения, используя соединение connection
                Message message = connection.receive();

                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                } else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                }
                else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        public void run()
        {

            try {
                //  1) Запроси адрес и порт сервера с помощью методов getServerAddress() и getServerPort().
                String serverAdress = getServerAddress();
                int serverPort = getServerPort();
                //  2) Создай новый объект класса java.net.Socket, используя данные, полученные
                // в предыдущем пункте.
                Socket socket = new Socket(serverAdress,serverPort);
                //  3) Создай объект класса Connection, используя сокет из п.17.2.
                Client.this.connection = new Connection(socket);
                //  4) Вызови метод, реализующий «рукопожатие» клиента с сервером (clientHandshake()).
                clientHandshake();
                //  5) Вызови метод, реализующий основной цикл обработки сообщений сервера.
                clientMainLoop();
                //  6) При возникновении исключений IOException или ClassNotFoundException сообщи
                // главному потоку о проблеме, используя notifyConnectionStatusChanged и false в
                // качестве параметра.
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
