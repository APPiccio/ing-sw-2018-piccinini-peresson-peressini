package com.sagrada.ppp;


import java.io.Serializable;

public class PlaceDiceResult implements Serializable {
    public String message;
    public boolean status;
    public WindowPanel panel;

    public PlaceDiceResult(String message, boolean status, WindowPanel panel){
        this.message = message;
        this.status = status;
        this.panel = panel;
    }
}
