package com.sagrada.ppp.model;

import java.io.Serializable;

/**
 * Container that groups all the information about the possible usage of a tool card.
 */
public class IsToolCardUsableResult implements Serializable {

    public boolean result;
    public int toolCardID;

    public IsToolCardUsableResult(boolean result, int toolCardID) {
        this.result = result;
        this.toolCardID = toolCardID;
    }
}
