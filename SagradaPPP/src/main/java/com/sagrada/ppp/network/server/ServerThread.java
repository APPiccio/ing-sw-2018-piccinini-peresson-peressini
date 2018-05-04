package com.sagrada.ppp.network.server;

import com.sagrada.ppp.Service;
import com.sagrada.ppp.utils.StaticValues;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    ServerSocket serverSocket;
    Socket socket;
    private int port = StaticValues.SOCKET_PORT;
    private Service service;

    public ServerThread(Service service){
        this.service = service;
    }

    public void run(){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("-->Listening on port " + port);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new SocketThread(socket, service).start();
        }
    }
}
