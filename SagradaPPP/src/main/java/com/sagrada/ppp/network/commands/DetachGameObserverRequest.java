package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.GameObserver;

import java.io.Serializable;

public class DetachGameObserverRequest implements Request, Serializable {

    public int gameHashCode;
    public int playerHashCode;

    public DetachGameObserverRequest(int gameHashCode, int playerHashCode){
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
