package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Player;

import java.io.Serializable;

public class PlayerDisconnectionNotification implements Response, Serializable {

    public Player disconnectingPlayer;

    public PlayerDisconnectionNotification(Player disconnectingPlayer) {
        this.disconnectingPlayer = disconnectingPlayer;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
