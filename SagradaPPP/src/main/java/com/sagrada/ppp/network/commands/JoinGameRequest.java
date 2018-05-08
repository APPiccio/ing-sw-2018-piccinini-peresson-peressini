package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.Observer;

import java.io.Serializable;

public class JoinGameRequest implements Request , Serializable {

    public String username;
    public Observer observer;

    public JoinGameRequest(String username, Observer observer){
        this.username = username;
        this.observer = observer;
    }

}
