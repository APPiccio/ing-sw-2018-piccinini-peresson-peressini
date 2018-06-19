package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Container that groups all the information of the usage of a tool card.
 */
public class UseToolCardResult implements Serializable {

    public boolean result;
    public int toolCardId;
    public int toolCardCost;
    public ArrayList<Dice> draftpool;
    public RoundTrack roundTrack;
    public ArrayList<Player> players;
    public Dice dice;
    public String msg;

    public UseToolCardResult(boolean result, int toolCardId, int toolCardCost, ArrayList<Dice> draftpool, RoundTrack roundTrack, ArrayList<Player> players, Dice dice, String msg) {
        this.result = result;
        this.toolCardId = toolCardId;
        this.toolCardCost = toolCardCost;
        this.draftpool = draftpool;
        this.roundTrack = roundTrack;
        this.players = players;
        this.dice = dice;
        this.msg = msg;
    }
}
