package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.controller.SocketClientController;

import java.io.IOException;

/**
 * Creation of the client's socket end that will establish the connection to server
 */
public class SocketConnection implements ConnectionMode {

    @Override
    public RemoteController getController() {
        try {
            System.out.println("Creating socket client controller");
            return new SocketClientController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}