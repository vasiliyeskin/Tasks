package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Vaisiliy Es'kin on 03/13/17.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {

        try {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                entry.getValue().send(message);
            }
        } catch (IOException e) {
            System.out.println("Message didn't send");
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Port?");
        int numberOfSocket = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(numberOfSocket)) {
            ConsoleHelper.writeMessage("Server is running.");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            super();
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();

                if (message.getType() == MessageType.USER_NAME) {
                    if (!message.getData().isEmpty() && !connectionMap.containsKey(message.getData())) {
                        connectionMap.put(message.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));

                        return message.getData();
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String key : connectionMap.keySet()) {
                Message message = new Message(MessageType.USER_ADDED, key);

                if (!key.equals(userName)) {
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String s = userName + ": " + message.getData();

                    Message formattedMessage = new Message(MessageType.TEXT, s);
                    sendBroadcastMessage(formattedMessage);
                } else {
                    ConsoleHelper.writeMessage("Error");
                }
            }
        }

        @Override
        public void run() {
            // 1. Выводить сообщение, что установлено новое соединение с удаленным адресом, который можно получить с помощью метода getRemoteSocketAddress.
            ConsoleHelper.writeMessage("Established connection with address" + socket.getRemoteSocketAddress());
            String clientName = null;
            // 2. Создавать Connection, используя поле socket.
            try (Connection connection = new Connection(socket)) {
                ConsoleHelper.writeMessage("Your use port: " + connection.getRemoteSocketAddress());
                // 3. Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента.
                clientName = serverHandshake(connection);
                // 4. Рассылать всем участникам чата информацию об имени присоединившегося участника (сообщение с типом USER_ADDED). Подумай, какой метод подойдет для этого лучше всего.
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                // 5. Сообщать новому участнику о существующих участниках.
                sendListOfUsers(connection, clientName);
                // 6. Запускать главный цикл обработки сообщений сервером.
                serverMainLoop(connection, clientName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                if(clientName != null) {
                    connectionMap.remove(clientName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
                    ConsoleHelper.writeMessage(String.format("Connection (%s).", socket.getRemoteSocketAddress()));
                }
            }
            ConsoleHelper.writeMessage(String.format("Connection with remote address (%s) is closed.", socket.getRemoteSocketAddress()));
        }
    }
}
