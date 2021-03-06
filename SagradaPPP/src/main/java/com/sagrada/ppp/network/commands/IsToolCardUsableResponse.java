package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.IsToolCardUsableResult;

import java.io.Serializable;

/**
 * Class container used in socket connection. Server response to:
 * @see IsToolCardUsableRequest
 */
public class IsToolCardUsableResponse implements Response, Serializable {

    public IsToolCardUsableResult isToolCardUsableResult;

    public IsToolCardUsableResponse(IsToolCardUsableResult isToolCardUsableResult) {
        this.isToolCardUsableResult = isToolCardUsableResult;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
