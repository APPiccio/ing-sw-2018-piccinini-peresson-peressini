package com.sagrada.ppp;

import java.io.Serializable;
import java.util.ArrayList;

public class EndTurnMessage implements Serializable {

    public Player previousPlayer;
    public Player currentPlayer;
    public ArrayList<Player> players;
    public int turn;
    public ArrayList<Dice> draftpool;
    public RoundTrack roundTrack;

    public EndTurnMessage(Player previousPlayer, Player currentPlayer, ArrayList<Player> players,int turn, ArrayList<Dice> draftpool, RoundTrack roundTrack){
        this.previousPlayer = previousPlayer;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.turn = turn;
        this.draftpool = draftpool;
        this.roundTrack = roundTrack;
    }
}
