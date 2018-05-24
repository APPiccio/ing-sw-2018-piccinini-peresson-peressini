package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.Dice;

public interface WindowPanelEventBus {
    void onCellClicked(int x, int y);
    void onDiceClicked(DiceButton diceButton,Dice dice);
}
