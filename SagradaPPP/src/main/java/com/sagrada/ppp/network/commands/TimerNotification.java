package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.TimerStatus;

import java.io.Serializable;

/**
 * Class container used in socket connection.
 * Used to notify client of timer changing.
 */
public class TimerNotification implements Response, Serializable {

    public long timerStart;
    public TimerStatus timerStatus;
    public long duration;

    public TimerNotification(long timerStart, TimerStatus timerStatus, long duration){
        this.timerStart = timerStart;
        this.timerStatus = timerStatus;
        this.duration = duration;
    }


    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
