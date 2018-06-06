package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.ReconnectionResult;

import java.io.Serializable;

public class ReconnectionResponse implements Serializable, Response {

    public ReconnectionResult reconnectionResult;

    public ReconnectionResponse(ReconnectionResult reconnectionResult) {
        this.reconnectionResult = reconnectionResult;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
