package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.ToolCardNotificationMessage;

import java.io.Serializable;

public class UsedToolCardNotification implements Serializable, Response {

    public ToolCardNotificationMessage toolCardNotificationMessage;

    public UsedToolCardNotification(ToolCardNotificationMessage toolCardNotificationMessage) {
        this.toolCardNotificationMessage = toolCardNotificationMessage;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
