package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.WindowPanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameStartNotification implements Response, Serializable {

    public HashMap<String, WindowPanel> chosenPanels;
    public ArrayList<Dice> draftpool;

    public GameStartNotification(HashMap<String, WindowPanel> chosenPanels, ArrayList<Dice> draftpool){
        this.chosenPanels  = chosenPanels;
        this.draftpool = draftpool;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
