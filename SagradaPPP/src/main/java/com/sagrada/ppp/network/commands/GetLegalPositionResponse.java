package com.sagrada.ppp.network.commands;

import java.io.Serializable;
import java.util.ArrayList;

public class GetLegalPositionResponse implements Serializable, Response {

    public ArrayList<Integer> positions;

    public GetLegalPositionResponse(ArrayList<Integer> positions) {
        this.positions = positions;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
