package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Container that groups all the information that the clients need when a dice is placed.
 */
public class DicePlacedMessage implements Serializable {

    public String username;
    public WindowPanel panel;
    public ArrayList<Dice> draftPool;

    public DicePlacedMessage(String username, WindowPanel panel, ArrayList<Dice> draftPool) {
        this.username = username;
        this.panel = panel;
        this.draftPool = draftPool;
    }

}