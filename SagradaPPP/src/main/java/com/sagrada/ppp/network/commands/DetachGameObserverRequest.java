package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.GameObserver;

import java.io.Serializable;

public class DetachGameObserverRequest implements Request, Serializable {

    public int gameHashCode;
    public GameObserver gameObserver;

    public DetachGameObserverRequest(int gameHashCode, GameObserver gameObserver){
        this.gameHashCode = gameHashCode;
        this.gameObserver = gameObserver;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
