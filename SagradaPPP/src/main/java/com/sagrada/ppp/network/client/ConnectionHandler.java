package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.RemoteController;

public class ConnectionHandler {

    private ConnectionMode connectionMode;
    private ConnectionModeEnum connectionModeEnum;

    public ConnectionHandler() {
        this(ConnectionModeEnum.RMI);
    }

    public ConnectionHandler(ConnectionModeEnum type){
        if(type.equals(ConnectionModeEnum.RMI)){
            connectionMode = new RmiConnection();
        }
        else {
            connectionMode = new SocketConnection();
        }
        connectionModeEnum = type;
    }

    public RemoteController getController(){
        return connectionMode.getController();
    }
}