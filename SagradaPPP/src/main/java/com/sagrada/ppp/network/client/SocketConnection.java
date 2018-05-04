package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.RemoteController;

public class SocketConnection implements ConnectionMode {
    public SocketConnection(){
        super();
    }

    @Override
    public RemoteController getController() {
        return null;
    }
}
