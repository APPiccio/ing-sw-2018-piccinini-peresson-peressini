package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Player;

import java.io.Serializable;

public class PlayerDisconnectionNotification implements Response, Serializable {

    public Player disconnectingPlayer;
    public boolean isLastPlayer;

    public PlayerDisconnectionNotification(Player disconnectingPlayer, boolean isLastPlayer) {
        this.disconnectingPlayer = disconnectingPlayer;
        this.isLastPlayer = isLastPlayer;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
