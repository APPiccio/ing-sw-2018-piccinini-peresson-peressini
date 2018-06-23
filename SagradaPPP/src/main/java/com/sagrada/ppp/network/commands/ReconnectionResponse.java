package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.ReconnectionResult;

import java.io.Serializable;

/**
 * network container class used in socket connection. Response to:
 * @see ReconnectionRequest
 */
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
