package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Player;

import java.io.Serializable;

/**
 * Class container used in socket connection. Used to notify that a player has been inactive for a while and now
 * is currently disabled.
 */
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
