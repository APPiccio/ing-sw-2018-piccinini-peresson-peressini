package com.sagrada.ppp.network.commands;

public interface ResponseHandler {

    void handle(Response response);
    void handle(JoinGameResponse response);
    void handle(TimerNotification response);
    void handle(PlayerEventNotification response);
    void handle(LeaveGameResponse response);
    void handle(PanelChoiceNotification response);
}
