package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.JoinGameResult;

import java.io.Serializable;

public class JoinGameResponse implements Response, Serializable {
    public JoinGameResult joinGameResult;

    public JoinGameResponse(JoinGameResult joinGameResult){
        this.joinGameResult = joinGameResult;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
