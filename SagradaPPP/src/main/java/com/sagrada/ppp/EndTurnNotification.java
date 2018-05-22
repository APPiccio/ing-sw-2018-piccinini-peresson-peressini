package com.sagrada.ppp;

import com.sagrada.ppp.network.commands.Response;
import com.sagrada.ppp.network.commands.ResponseHandler;

import java.io.Serializable;

public class EndTurnNotification implements Response, Serializable {

    public EndTurnMessage endTurnMessage;

    public EndTurnNotification(EndTurnMessage endTurnMessage){
        this.endTurnMessage = endTurnMessage;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
