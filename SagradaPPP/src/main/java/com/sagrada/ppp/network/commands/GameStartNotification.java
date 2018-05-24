package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.GameStartMessage;

import java.io.Serializable;

public class GameStartNotification implements Response, Serializable {

    public GameStartMessage gameStartMessage;

    public GameStartNotification(GameStartMessage gameStartMessage){
        this.gameStartMessage = gameStartMessage;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
