package com.sagrada.ppp.network.server;

import com.sagrada.ppp.Service;
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

        String line;
        while (true) {
            //DO SOCKET SERVER STUFF TO CLIENT
            try {
                System.out.println("mi metto in attesa");
                line = in.readObject().toString();
                System.out.println("leggo..");
                if ((line == null) || line.equalsIgnoreCase(StaticValues.COMMAND_QUIT)) {
                    socket.close();
                    return;
                } else {
                    //DECODE CLIENT INPUT
                    System.out.println("detected input : " + line);
                    switch (line){
                        case StaticValues.COMMAND_SHOW_GAMES:
                            out.writeObject(service.getJoinableGames());
                            break;
                        default:
                            break;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
