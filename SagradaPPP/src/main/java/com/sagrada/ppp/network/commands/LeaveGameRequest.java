package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.LobbyObserver;

import java.io.Serializable;

public class LeaveGameRequest implements Request, Serializable {

    public int gameHashCode;
    public String username;


    public LeaveGameRequest(int gameHashCode, String username){
        this.gameHashCode = gameHashCode;
        this.username = username;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
