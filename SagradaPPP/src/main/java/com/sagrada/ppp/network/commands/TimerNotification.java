package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.TimerStatus;

import java.io.Serializable;

public class TimerNotification implements Response, Serializable {

    public long timerStart;
    public TimerStatus timerStatus;

    public TimerNotification(long timerStart, TimerStatus timerStatus){
        this.timerStart = timerStart;
        this.timerStatus = timerStatus;
    }


    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
