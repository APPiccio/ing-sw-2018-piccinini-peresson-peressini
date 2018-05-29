package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.UseToolCardResult;

import java.io.Serializable;

public class UseToolCardResponse implements Response, Serializable {

    public UseToolCardResult useToolCardResult;


    public UseToolCardResponse(UseToolCardResult useToolCardResult) {
        this.useToolCardResult = useToolCardResult;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
