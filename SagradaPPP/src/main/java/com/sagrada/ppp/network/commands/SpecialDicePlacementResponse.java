package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.PlaceDiceResult;

import java.io.Serializable;

/**
 * Class container used in socket connection. Response to :
 * @see SpecialDicePlacementRequest
 */
public class SpecialDicePlacementResponse implements Serializable, Response {

    public PlaceDiceResult result;

    public SpecialDicePlacementResponse(PlaceDiceResult result) {
        this.result = result;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
