package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.cards.toolcards.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to package all the objects needed in order to notify
 * the user of the game start (right after the lobby).
 *
 * It's also used for player reconnection
 */
public class GameStartMessage implements Serializable {
    public ArrayList<Dice> draftpool;
    public ArrayList<ToolCard> toolCards;
    public ArrayList<PublicObjectiveCard> publicObjectiveCards;
    public ArrayList<Player> players;
    //from here, objects needed for reconnection
    public Player currentPlayer;
    public RoundTrack roundTrack;

    /**
     * @param draftpool current game draftpool
     * @param toolCards current game toolCards
     * @param publicObjectiveCards current game publicObjCards
     * @param players current list of players
     * @param roundTrack current game roundtrack
     * @param currentPlayer used to inform user of the current active player in case of reconnection
     */
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
