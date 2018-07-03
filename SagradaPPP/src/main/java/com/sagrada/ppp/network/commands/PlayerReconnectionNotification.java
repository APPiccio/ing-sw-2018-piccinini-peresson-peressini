package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Player;

import java.io.Serializable;

/**
 * Class container used in socket connection, during player reconnection attempt
 */
public class PlayerReconnectionNotification implements Response, Serializable {

    public Player reconnectingPlayer;

    public PlayerReconnectionNotification(Player reconnectingPlayer) {
        this.reconnectingPlayer = reconnectingPlayer;
    }

    @Override
    public void handle(ResponseHandler handler) {
        this.handle(handler);
    }
}
