package com.sagrada.ppp.network.commands;

import java.io.Serializable;

/**
 * Request to disable a player's afk status
 */
public class DisableAFKRequest implements Serializable, Request {

    public int gameHashCode;
    public int playerHashCode;

    public DisableAFKRequest(int gameHashCode, int playerHashCode) {
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
