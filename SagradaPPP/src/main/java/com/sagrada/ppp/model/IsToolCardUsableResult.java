package com.sagrada.ppp.model;

import java.io.Serializable;

public class IsToolCardUsableResult implements Serializable {

    public boolean result;
    public int toolCardID;

    public IsToolCardUsableResult(boolean result, int toolCardID) {
        this.result = result;
        this.toolCardID = toolCardID;
    }
}
