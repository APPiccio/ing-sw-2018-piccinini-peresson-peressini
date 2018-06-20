package com.sagrada.ppp.network.commands;


import java.io.Serializable;

/**
 * Container for the request of detaching one of the client's GameObservers
 */
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
