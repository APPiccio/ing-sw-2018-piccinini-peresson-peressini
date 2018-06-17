package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.cards.toolcards.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameStartMessage implements Serializable {
    public ArrayList<Dice> draftpool;
    public ArrayList<ToolCard> toolCards;
    public ArrayList<PublicObjectiveCard> publicObjectiveCards;
    public ArrayList<Player> players;
    //from here, objects needed for reconnection
    public Player currentPlayer;
    public RoundTrack roundTrack;

    public GameStartMessage(ArrayList<Dice> draftpool,
                            ArrayList<ToolCard> toolCards, ArrayList<PublicObjectiveCard> publicObjectiveCards,
                            ArrayList<Player> players,
                            RoundTrack roundTrack, Player currentPlayer){
        this.draftpool = draftpool;
        this.toolCards = toolCards;
        this.publicObjectiveCards = publicObjectiveCards;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.roundTrack = roundTrack;
    }

}
