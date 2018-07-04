package com.sagrada.ppp.network.server;

import com.sagrada.ppp.utils.StaticValues;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import static java.lang.System.*;

public class ServerThread extends Thread {

    ServerSocket serverSocket;
    Socket socket;
    private int port = StaticValues.SOCKET_PORT;
    private Service service;

    public ServerThread(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            out.println("-->Listening on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                out.println("I/O error: " + e);
            }
            // new thread for a client
            new SocketThread(socket, service).start();
        }
    }
}
