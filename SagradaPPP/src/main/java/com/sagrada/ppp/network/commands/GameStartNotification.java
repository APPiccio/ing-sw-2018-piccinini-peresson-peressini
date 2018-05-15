package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.WindowPanel;

import java.io.Serializable;
import java.util.HashMap;

public class GameStartNotification implements Response, Serializable {

    public HashMap<String, WindowPanel> chosenPanels;

    public GameStartNotification(HashMap<String, WindowPanel> chosenPanels){
        this.chosenPanels  = chosenPanels;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
