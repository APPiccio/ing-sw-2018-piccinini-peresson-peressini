package com.sagrada.ppp.network.commands;

public interface ResponseHandler {

    void handle(Response response);
    void handle(JoinGameResponse response);
    void handle(TimerNotification response);
    void handle(PlayerEventNotification response);
    void handle(LeaveGameResponse response);
    void handle(PanelChoiceNotification response);
    void handle(GameStartNotification response);
    void handle(DisconnectionResponse response);
    void handle(PlaceDiceResponse response);
}
