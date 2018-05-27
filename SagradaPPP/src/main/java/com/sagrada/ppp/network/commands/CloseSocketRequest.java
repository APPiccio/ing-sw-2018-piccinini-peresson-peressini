package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class CloseSocketRequest implements Serializable, Request {

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
