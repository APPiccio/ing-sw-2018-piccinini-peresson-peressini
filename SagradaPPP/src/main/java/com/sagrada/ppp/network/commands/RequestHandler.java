package com.sagrada.ppp.network.commands;

public interface RequestHandler {

    Response handle(Request request);
    Response handle(JoinGameRequest request);
    Response handle(LeaveGameRequest request);
}
