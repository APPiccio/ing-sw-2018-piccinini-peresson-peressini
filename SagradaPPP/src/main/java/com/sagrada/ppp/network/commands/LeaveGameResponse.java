package com.sagrada.ppp.network.commands;



import com.sagrada.ppp.model.LeaveGameResult;

import java.io.Serializable;

/**
 * Class container used in socket connection. Server response to :
 * @see LeaveGameRequest
 */
public class LeaveGameResponse implements Response, Serializable {
    public LeaveGameResult leaveGameResult;

    public LeaveGameResponse(LeaveGameResult leaveGameResult ){
        this.leaveGameResult = leaveGameResult;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}