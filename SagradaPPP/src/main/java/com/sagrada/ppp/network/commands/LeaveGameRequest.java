package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class LeaveGameRequest implements Request,Serializable {
    public String username;
    public int gameHashCode;

    public LeaveGameRequest(String username,int gameHashCode){
        this.username = username;
        this.gameHashCode = gameHashCode;
    }

    @Override
    public Response handle(RequestHandler handler){
        return handler.handle(this);
    }

}
