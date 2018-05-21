package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class PlaceDiceRequest implements Serializable, Request {

    public int gameHashCode;
    public int playerHashCode;
    public int diceIndex;
    public int row;
    public int col;

    public PlaceDiceRequest(int gameHashCode, int playerHashCode, int diceIndex, int row, int col){
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
        this.diceIndex = diceIndex;
        this.row = row;
        this.col = col;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
