package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Dice;

import java.io.Serializable;

/**
 * Request sent by a client wanting all legal positions on his panel of a dice.
 */
public class GetLegalPositionRequest implements Request, Serializable {

    public int gameHashCode;
    public int playerHashCode;
    public Dice dice;

    public GetLegalPositionRequest(int gameHashCode, int playerHashCode, Dice dice) {
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
        this.dice = dice;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
