package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class ErrorResponse implements Response, Serializable {

    public String errorMessage;

    public ErrorResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
