package com.sagrada.ppp.view.gui;

public interface GuiEventBus {

    void onCellClicked(int row, int col);
    void onDiceClicked(int row, int col);
    void onRoundTrackDiceClicked(int diceIndex, int roundIndex);

}