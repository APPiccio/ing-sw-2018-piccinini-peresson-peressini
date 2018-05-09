package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class JoinPlayerNotification implements Response, Serializable {
    public int numOfPlayers;
    public String username;

    public JoinPlayerNotification(int numOfPlayers, String username){
        this.numOfPlayers = numOfPlayers;
        this.username = username;
    }


    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
