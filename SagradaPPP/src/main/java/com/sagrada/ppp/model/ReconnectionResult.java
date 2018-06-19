package com.sagrada.ppp.model;

import java.io.Serializable;

/**
 * Container that groups all the information about the reconnection of a player into a game.
 */
public class ReconnectionResult implements Serializable {

    public boolean result;
    public String message;
    public GameStartMessage gameStartMessage;

    public ReconnectionResult(boolean result, String message, GameStartMessage gameStartMessage) {
        this.result = result;
        this.message = message;
        this.gameStartMessage = gameStartMessage;
    }

}
