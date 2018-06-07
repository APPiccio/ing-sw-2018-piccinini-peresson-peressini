package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Player;

import java.io.Serializable;

public class PlayerReconnectionNotification implements Response, Serializable {

    public Player reconnectingPlayer;

    public PlayerReconnectionNotification(Player reconnectingPlayer) {
        this.reconnectingPlayer = reconnectingPlayer;
    }

    @Override
    public void handle(ResponseHandler handler) {

    }
}
