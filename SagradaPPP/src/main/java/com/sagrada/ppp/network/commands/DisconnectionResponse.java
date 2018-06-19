package com.sagrada.ppp.network.commands;

import java.io.Serializable;

/**
 * Response about the request of disconnection of a player
 */
public class DisconnectionResponse implements Serializable, Response {

    public boolean disconnectionResult;

    public DisconnectionResponse(boolean disconnectionResult){
        this.disconnectionResult = disconnectionResult;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
