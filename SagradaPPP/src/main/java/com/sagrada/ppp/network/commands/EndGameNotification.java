package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.PlayerScore;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * End game notification.
 * Is sent to all clients when the game ends.
 */
public class EndGameNotification implements Response, Serializable {

    public ArrayList<PlayerScore> playersScore;

    public EndGameNotification(ArrayList<PlayerScore> playersScore) {
        this.playersScore = playersScore;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
