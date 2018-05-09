package com.sagrada.ppp.network.commands;

public interface ResponseHandler {

    void handle(Response response);
    void handle(JoinGameResponse response);
    void handle(JoinPlayerNotification response);
}
