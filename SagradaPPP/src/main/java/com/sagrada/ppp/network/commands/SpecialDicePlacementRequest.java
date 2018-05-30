package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Dice;

import java.io.Serializable;

public class SpecialDicePlacementRequest implements Request, Serializable {

    public int gameHashCode;
    public int playerHashCode;
    public int cellIndex;
    public Dice dice;

    public SpecialDicePlacementRequest(int gameHashCode, int playerHashCode, int cellIndex, Dice dice) {
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
        this.cellIndex = cellIndex;
        this.dice = dice;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
