package com.javarush.task.task30.task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Vaisiliy Es'kin on 03/13/17.
 */
public class Connection implements Closeable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Connection(Socket socket) throws IOException {
        ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
        this.out = out2;
        ObjectInputStream in2 = new ObjectInputStream(socket.getInputStream());
        this.in = in2;

        this.socket = socket;
    }

    public void send(Message message) throws IOException
    {
        synchronized (out) {
            out.writeObject(message);
            out.flush();
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {

        Message message;

        synchronized (in) {
            message = (Message)in.readObject();
            return message;
        }

    }


    public SocketAddress getRemoteSocketAddress() {

        return socket.getRemoteSocketAddress();
    }


    public void close() throws IOException {

        in.close();
        out.close();
        socket.close();
    }


}
