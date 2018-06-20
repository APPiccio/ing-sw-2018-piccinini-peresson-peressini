package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.EndTurnMessage;
import com.sagrada.ppp.network.commands.Response;
import com.sagrada.ppp.network.commands.ResponseHandler;

import java.io.Serializable;

/**
 * Response sent to all clients.
 * It contains all the information about the ending of a turn
 */
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
