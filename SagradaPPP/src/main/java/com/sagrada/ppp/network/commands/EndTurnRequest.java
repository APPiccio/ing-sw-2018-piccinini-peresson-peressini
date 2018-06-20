package com.sagrada.ppp.network.commands;

import java.io.Serializable;

/**
 * Request sent by a client expressing the will to end his turn.
 */
public class EndTurnRequest implements Request, Serializable {

    public int gameHashCode;
    public int playerHashCode;

    public EndTurnRequest(int gameHashCode, int playerHashCode){
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
