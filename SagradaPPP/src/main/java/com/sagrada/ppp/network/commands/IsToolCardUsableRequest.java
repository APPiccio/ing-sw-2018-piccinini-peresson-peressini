package com.sagrada.ppp.network.commands;

import java.io.Serializable;

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
