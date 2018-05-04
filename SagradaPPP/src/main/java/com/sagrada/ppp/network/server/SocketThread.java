package com.sagrada.ppp.network.server;

import com.sagrada.ppp.utils.StaticValues;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread {
    private Socket socket;

    public SocketThread(Socket socket){
        this.socket = socket;
    }

    public void run() {
        InputStream in = null;
        BufferedReader reader = null;
        DataOutputStream out = null;
        try {
            in = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            //DO SOCKET SERVER STUFF TO CLIENT
            try {
                line = reader.readLine();
                if ((line == null) || line.equalsIgnoreCase(StaticValues.COMMAND_QUIT)) {
                    socket.close();
                    return;
                } else {
                    //DECODE CLIENT INPUT


                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
