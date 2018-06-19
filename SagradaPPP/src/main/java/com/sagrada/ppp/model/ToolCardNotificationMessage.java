package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Container used to transfer data about the usage of a tool card to all clients of a game
 */
public class ToolCardNotificationMessage implements Serializable {

    public int toolCardID;
    public int toolCardCost;
    public Player player;
    public ArrayList<Dice> draftPool;
    public RoundTrack roundTrack;

    public ToolCardNotificationMessage(int toolCardID, Player player, ArrayList<Dice> draftPool, RoundTrack roundTrack,int toolCardCost) {
        this.toolCardID = toolCardID;
        this.player = player;
        this.draftPool = draftPool;
        this.roundTrack = roundTrack;
        this.toolCardCost = toolCardCost;
    }
}
