package com.sagrada.ppp.network.commands;


public class PanelChoiceRequest implements Request {

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
