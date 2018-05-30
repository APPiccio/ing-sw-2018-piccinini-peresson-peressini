package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Dice;

import java.io.Serializable;

public class PutDiceInDraftPoolRequest implements Serializable, Request {

    public Dice dice;
    public int gameHashCode;

    public PutDiceInDraftPoolRequest(int gameHashCode, Dice dice) {
        this.dice = dice;
        this.gameHashCode = gameHashCode;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
