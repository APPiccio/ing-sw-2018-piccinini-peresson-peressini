package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Container that groups all the information that the clients need when a turn ends.
 */
public class EndTurnMessage implements Serializable {

    public Player previousPlayer;
    public Player currentPlayer;
    public ArrayList<Player> players;
    public int turn;
    public ArrayList<Dice> draftpool;
    public RoundTrack roundTrack;

    public EndTurnMessage(Player previousPlayer, Player currentPlayer, ArrayList<Player> players, int turn, ArrayList<Dice> draftpool, RoundTrack roundTrack){
        this.previousPlayer = previousPlayer;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.turn = turn;
        this.draftpool = draftpool;
        this.roundTrack = roundTrack;
    }
}
