package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Player;
import com.sagrada.ppp.network.commands.Response;
import com.sagrada.ppp.network.commands.ResponseHandler;

import java.io.Serializable;

public class PlayerAFKNotification implements Serializable, Response {

    public Player player;
    public boolean isLastPlayer;
    public Player lastPlayer;

    public PlayerAFKNotification(Player player, boolean isLastPlayer, Player lastPlayer) {
        this.player = player;
        this.isLastPlayer = isLastPlayer;
        this.lastPlayer = lastPlayer;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
