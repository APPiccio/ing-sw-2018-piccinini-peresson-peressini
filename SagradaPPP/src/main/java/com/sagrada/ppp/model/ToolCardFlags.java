package com.sagrada.ppp.model;

/**
 * Class used to group all flags used to acquire all the information needed for a tool card.
 */
public class ToolCardFlags {
    public volatile boolean isDraftPoolDiceRequired;
    public volatile boolean isRoundTrackDiceRequired;
    public volatile boolean isPanelDiceRequired;
    public volatile boolean isSecondPanelDiceRequired;
    public volatile boolean isPanelCellRequired;
    public volatile boolean isSecondPanelCellRequired;
    public volatile boolean isActionSignRequired;
    public volatile boolean isDiceValueRequired;
    public volatile Color colorDiceValueRequired;
    public volatile boolean isTwoDiceActionRequired;
    public volatile boolean isReRolledDiceActionRequired;
    public volatile Dice reRolledDice;

    public ToolCardFlags(){
    }

    /**
     * Resents all flags to false or a passive state.
     */
    public void reset(){
        isDraftPoolDiceRequired = false;
        isPanelCellRequired = false;

        isPanelDiceRequired = false;
        isRoundTrackDiceRequired = false;
        isActionSignRequired = false;
        isSecondPanelCellRequired = false;
        isSecondPanelDiceRequired = false;
        isDiceValueRequired = false;
        isTwoDiceActionRequired = false;
        colorDiceValueRequired = null;
        isReRolledDiceActionRequired = false;
        reRolledDice = null;
    }
}
