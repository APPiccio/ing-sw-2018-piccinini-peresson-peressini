package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.JoinGameResult;

import java.io.Serializable;

public class JoinGameResponse implements Response, Serializable {
    public JoinGameResult joinGameResult;

    public JoinGameResponse(JoinGameResult joinGameResult){
        this.joinGameResult = joinGameResult;
    }
}
