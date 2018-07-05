package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.RemoteController;

public class ConnectionHandler {

    private ConnectionMode connectionMode;

    /**
     * @param type connection type
     */
    public ConnectionHandler(ConnectionModeEnum type) {
        if (type.equals(ConnectionModeEnum.RMI)) {
            connectionMode = new RmiConnection();
        }
        else {
            connectionMode = new SocketConnection();
        }
    }

    public RemoteController getController() {
        return connectionMode.getController();
    }

}