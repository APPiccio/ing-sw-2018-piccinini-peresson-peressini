package com.sagrada.ppp.network.server;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.Service;
import com.sagrada.ppp.network.commands.*;
import com.sagrada.ppp.utils.StaticValues;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread {
    private Socket socket;
    private Service service;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SocketThread(Socket socket, Service service){
        this.socket = socket;
        this.service = service;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            //DO SOCKET SERVER STUFF TO CLIENT
            try {
                System.out.println("mi metto in attesa");
                JoinGameRequest request = (JoinGameRequest) in.readObject();
                Response response = use(request);
                out.writeObject(response);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Response use(JoinGameRequest request){
        JoinGameResult joinGameResult = service.joinGame(request.username);
        return new JoinGameResponse(joinGameResult);
    }

}
