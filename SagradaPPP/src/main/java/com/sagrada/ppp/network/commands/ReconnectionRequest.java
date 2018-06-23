package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.GameObserver;

import java.io.Serializable;

/**
 * network container class used in socket connection.
 * Used to send a request of reconnection to a game leaved before.
 */
public class ReconnectionRequest implements Serializable, Request {

    public int gameHashCode;
    public int playerHashCode;
    public GameObserver gameObserver;

    public ReconnectionRequest(int gameHashCode, int playerHashCode, GameObserver gameObserver) {
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
        this.gameObserver = gameObserver;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
