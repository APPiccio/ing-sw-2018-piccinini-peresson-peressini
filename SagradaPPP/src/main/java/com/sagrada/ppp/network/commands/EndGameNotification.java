package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.PlayerScore;

import java.io.Serializable;
import java.util.ArrayList;

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
