package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DicePlacedMessage implements Serializable {

    public String username;
    public WindowPanel panel;
    public ArrayList<Dice> draftPool;

    DicePlacedMessage(String username, WindowPanel panel, ArrayList<Dice> draftPool) {
        this.username = username;
        this.panel = panel;
        this.draftPool = draftPool;
    }

}