package com.sagrada.ppp.model;

import java.io.Serializable;

/**
 * class container sent to the user after a request of toolcard usage
 */
public class IsToolCardUsableResult implements Serializable {

    public boolean result;
    public int toolCardID;

    /**
     * @param result
     * @param toolCardID the ID of the card
     */
    public IsToolCardUsableResult(boolean result, int toolCardID) {
        this.result = result;
        this.toolCardID = toolCardID;
    }
}
