package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.WindowPanel;
import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.ToolCards.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameStartNotification implements Response, Serializable {

    public HashMap<String, WindowPanel> chosenPanels;
    public ArrayList<Dice> draftpool;
    public ArrayList<ToolCard> toolCards;
    public ArrayList<PublicObjectiveCard> publicObjectiveCards;

    public GameStartNotification(HashMap<String, WindowPanel> chosenPanels, ArrayList<Dice> draftpool, ArrayList<ToolCard> toolCards, ArrayList<PublicObjectiveCard> publicObjectiveCards){
        this.chosenPanels  = chosenPanels;
        this.draftpool = draftpool;
        this.publicObjectiveCards = publicObjectiveCards;
        this.toolCards = toolCards;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
