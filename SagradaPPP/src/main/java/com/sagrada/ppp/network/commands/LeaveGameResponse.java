package com.sagrada.ppp.network.commands;



import com.sagrada.ppp.model.LeaveGameResult;

import java.io.Serializable;

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