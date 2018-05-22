package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.PlaceDiceResult;

import java.io.Serializable;

public class PlaceDiceResponse implements Serializable, Response {

    public PlaceDiceResult placeDiceResult;

    public PlaceDiceResponse(PlaceDiceResult placeDiceResult){
        this.placeDiceResult = new PlaceDiceResult(placeDiceResult.message, placeDiceResult.status, placeDiceResult.panel);
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}