package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.GameObserver;

import java.io.Serializable;

public class DetachGameObserverRequest implements Request, Serializable {

    public int gameHashCode;

    public DetachGameObserverRequest(int gameHashCode){
        this.gameHashCode = gameHashCode;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
