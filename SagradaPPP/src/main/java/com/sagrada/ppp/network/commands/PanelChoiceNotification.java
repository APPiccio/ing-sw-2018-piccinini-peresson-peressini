package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.WindowPanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class container used in socket connection. Used to notify user that someone has chose his panel and now it's the time
 * that another player make his choice
 */
public class PanelChoiceNotification implements Response, Serializable {

    public int playerHashCode;
    public ArrayList<WindowPanel> panels;
    public HashMap<String, WindowPanel> panelAlreadyChosen;
    public Color color;

    public PanelChoiceNotification(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color color){
        this.playerHashCode = playerHashCode;
        this.panels = panels;
        this.panelAlreadyChosen = panelsAlreadyChosen;
        this.color = color;
    }


    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
