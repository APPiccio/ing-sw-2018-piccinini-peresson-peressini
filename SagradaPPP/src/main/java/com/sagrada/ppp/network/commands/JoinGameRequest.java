package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class JoinGameRequest implements Request , Serializable {

    public String username;

    public JoinGameRequest(String username){
        this.username = username;
    }

}
