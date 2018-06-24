package com.sagrada.ppp.network.commands;


import java.io.Serializable;

/**
 * Class container used in socket connection. Used by players to notify their panel choice.
 */
public class PanelChoiceRequest implements Request, Serializable {

    public int gameHashCode;
    public int playerHashCode;
    public int panelIndex;

    public PanelChoiceRequest(int gameHashCode, int playerHashCode, int panelIndex){
        this.gameHashCode = gameHashCode;
        this.playerHashCode = playerHashCode;
        this.panelIndex = panelIndex;
    }


    public Response handle(RequestHandler handler){
        return handler.handle(this);
    }

}
