package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Cell;
import com.sagrada.ppp.Dice;

public interface WindowPanelEventBus {
    void onCellClicked(int x, int y);
    void onDiceClicked(DiceButton diceButton,Dice dice);
}
