package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.GameStartMessage;

import java.io.Serializable;

/**
 * Notification sent to all clients subscribed to a game observer that informs about the start of a game.
 */
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
