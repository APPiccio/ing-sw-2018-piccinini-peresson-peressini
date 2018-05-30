package com.sagrada.ppp.network.commands;

import java.io.Serializable;

public class SpecialDicePlacementResponse implements Serializable, Response {

    public boolean result;

    public SpecialDicePlacementResponse(boolean result) {
        this.result = result;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
