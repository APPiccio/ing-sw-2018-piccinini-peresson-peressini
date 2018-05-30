package com.sagrada.ppp.model;

import java.io.Serializable;

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
    }
}
