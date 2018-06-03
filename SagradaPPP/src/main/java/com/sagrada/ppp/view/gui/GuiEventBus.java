package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.Dice;

public interface GuiEventBus {
    void onCellClicked(int row, int col);
    void onDiceClicked(int row, int col);
    void onRoundTrackDiceClicked(int diceIndex, int roundIndex);
}
