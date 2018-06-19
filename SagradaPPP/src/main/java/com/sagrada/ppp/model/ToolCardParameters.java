package com.sagrada.ppp.model;

import java.io.Serializable;

/**
 * Contains all the parameters needed to complete a tool card usage on the server.
 */
public class ToolCardParameters implements Serializable {

    public volatile Integer draftPoolDiceIndex;
    public volatile Integer roundTrackDiceIndex;
    public volatile Integer panelDiceIndex;
    public volatile Integer secondPanelDiceIndex;
    public volatile Integer roundTrackRoundIndex;
    public volatile Integer panelCellIndex;
    public volatile Integer secondPanelCellIndex;
    public volatile Integer toolCardID;
    public volatile Integer actionSign;
    public volatile Integer diceValue;
    public volatile Boolean twoDiceAction;

    /**
     * Resets all the parameters to their default state.
     */
    public void reset(){
        toolCardID = 0;
        draftPoolDiceIndex = null;
        roundTrackDiceIndex = null;
        roundTrackRoundIndex = null;
        panelDiceIndex = null;
        panelCellIndex = null;
        actionSign = null;
        secondPanelCellIndex = null;
        secondPanelDiceIndex = null;
        diceValue = null;
        twoDiceAction = null;
    }
}
