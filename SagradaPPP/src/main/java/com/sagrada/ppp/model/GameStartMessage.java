package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.cards.toolcards.ToolCard;

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
    //from here, objects needed for reconnection
    public Player currentPlayer;
    public RoundTrack roundTrack;

    public GameStartMessage(HashMap<String, WindowPanel> chosenPanels, ArrayList<Dice> draftpool,
                            ArrayList<ToolCard> toolCards, ArrayList<PublicObjectiveCard> publicObjectiveCards,
                            ArrayList<String> playersUsername, ArrayList<Player> players,
                            RoundTrack roundTrack, Player currentPlayer){
        this.chosenPanels = chosenPanels;
        this.draftpool = draftpool;
        this.toolCards = toolCards;
        this.publicObjectiveCards = publicObjectiveCards;
        this.playersUsername = playersUsername;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.roundTrack = roundTrack;
    }

}
