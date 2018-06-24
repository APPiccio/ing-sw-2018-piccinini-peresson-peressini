package com.sagrada.ppp.network.commands;


import java.io.Serializable;

/**
 * Class container used in socket connection. Request of joining a game (new or already present depends on project guidelines
 */
public class JoinGameRequest implements Request , Serializable {

    public String username;

    public JoinGameRequest(String username){
        this.username = username;
    }

    public Response handle(RequestHandler handler){
        return handler.handle(this);
    }

}
