package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.ToolCards.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameStartMessage implements Serializable {
    public HashMap<String, WindowPanel> chosenPanels;
    public ArrayList<Dice> draftpool;
    public ArrayList<ToolCard> toolCards;
    public ArrayList<PublicObjectiveCard> publicObjectiveCards;
    public ArrayList<String> playersUsername;
    public ArrayList<Player> players;

    public GameStartMessage(HashMap<String, WindowPanel> chosenPanels, ArrayList<Dice> draftpool, ArrayList<ToolCard> toolCards, ArrayList<PublicObjectiveCard> publicObjectiveCards,
                            ArrayList<String> playersUsername, ArrayList<Player> players){
        this.chosenPanels = chosenPanels;
        this.draftpool = draftpool;
        this.toolCards = toolCards;
        this.publicObjectiveCards = publicObjectiveCards;
        this.playersUsername = playersUsername;
        this.players = players;
    }

}
