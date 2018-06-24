package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Player;

import java.io.Serializable;

/**
 * Class container used in socket connection. As the name said, used to notify player disconnection
 */
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
