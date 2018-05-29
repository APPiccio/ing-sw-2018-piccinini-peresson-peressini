package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.ToolCardParameters;

import java.io.Serializable;

public class UseToolCardRequest implements Request, Serializable {

    public int gameHashCode;
    public int playerHashCode;
    public ToolCardParameters toolCardParameters;

    public UseToolCardRequest(int gameHashCode, int playerHashCode, ToolCardParameters toolCardParameters) {
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
        this.toolCardParameters = toolCardParameters;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.handle(this);
    }
}
