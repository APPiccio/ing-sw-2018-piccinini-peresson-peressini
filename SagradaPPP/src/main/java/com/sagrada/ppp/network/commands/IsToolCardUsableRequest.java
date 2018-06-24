package com.sagrada.ppp.network.commands;

import java.io.Serializable;

/**
 * Class container used in socket connection. Used by client to check the usability of a specific toolCard.
 */
public class IsToolCardUsableRequest implements Request, Serializable {

    public int gameHashCode;
    public int playerHashCode;
    public int toolCardIndex;

    public IsToolCardUsableRequest(int gameHashCode, int playerHashCode, int toolCardIndex) {
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
        this.toolCardIndex = toolCardIndex;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
