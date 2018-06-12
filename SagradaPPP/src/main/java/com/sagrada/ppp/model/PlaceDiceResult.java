package com.sagrada.ppp.model;


import java.io.Serializable;
import java.util.ArrayList;

public class PlaceDiceResult implements Serializable {
    public String message;
    public boolean status;
    public WindowPanel panel;
    public ArrayList<Dice> draftPool;

    public PlaceDiceResult(String message, boolean status, WindowPanel panel, ArrayList<Dice> draftPool){
        this.message = message;
        this.status = status;
        this.panel = panel;
        this.draftPool = draftPool;
    }
}
