package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.Observer;

import java.io.Serializable;

public class JoinGameRequest implements Request , Serializable {

    public String username;

    public JoinGameRequest(String username){
        this.username = username;
    }

    public Response handle(RequestHandler handler){
        return handler.handle(this);
    }

}
