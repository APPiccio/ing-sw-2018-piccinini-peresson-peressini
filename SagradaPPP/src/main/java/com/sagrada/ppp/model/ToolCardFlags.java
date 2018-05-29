package com.sagrada.ppp.model;

public class ToolCardFlags {
    public volatile boolean isDraftPoolDiceRequired;
    public volatile boolean isRoundTrackDiceRequired;
    public volatile boolean isPanelDiceRequired;
    public volatile boolean isPanelCellRequired;
    public volatile boolean isActionSignRequired;

    public ToolCardFlags(){
    }

    public void reset(){
        isDraftPoolDiceRequired = false;
        isPanelCellRequired = false;
        isPanelDiceRequired = false;
        isRoundTrackDiceRequired = false;
        isActionSignRequired = false;
    }
}
