package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.WindowPanel;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelChoiceNotification implements Response, Serializable {

    public int playerHashCode;
    public ArrayList<WindowPanel> panels;
    public HashMap<String, WindowPanel> panelAlreadyChosen;

    public PanelChoiceNotification(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen){
        this.playerHashCode = playerHashCode;
        this.panels = panels;
        this.panelAlreadyChosen = panelsAlreadyChosen;
    }


    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
