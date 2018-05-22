package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.DicePlacedMessage;

import java.io.Serializable;

public class DicePlacedNotification implements Response,Serializable {

    public DicePlacedMessage dicePlacedMessage;

    public DicePlacedNotification(DicePlacedMessage dicePlacedMessage) {
        this.dicePlacedMessage = dicePlacedMessage;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
