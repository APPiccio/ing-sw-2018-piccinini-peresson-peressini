package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class DisconnectionRequest implements Serializable, Request {

    public int playerHashCode;
    public int gameHashCode;

    public DisconnectionRequest(int gameHashCode, int playerHashCode){
        this.playerHashCode = playerHashCode;
        this.gameHashCode = gameHashCode;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
