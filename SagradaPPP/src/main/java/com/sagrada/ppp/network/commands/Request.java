package com.sagrada.ppp.network.commands;

public interface Request  {

    Response handle(RequestHandler handler);
}
