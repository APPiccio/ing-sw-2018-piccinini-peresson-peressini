package com.sagrada.ppp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ToolCardNotificationMessage implements Serializable {

    public int toolCardID;
    public Player player;
    public ArrayList<Dice> draftPool;
    public RoundTrack roundTrack;

    public ToolCardNotificationMessage(int toolCardID, Player player, ArrayList<Dice> draftPool, RoundTrack roundTrack) {
        this.toolCardID = toolCardID;
        this.player = player;
        this.draftPool = draftPool;
        this.roundTrack = roundTrack;
    }
}
