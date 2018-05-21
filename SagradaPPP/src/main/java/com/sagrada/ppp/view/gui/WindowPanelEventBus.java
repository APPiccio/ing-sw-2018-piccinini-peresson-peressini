package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Cell;
import com.sagrada.ppp.Dice;

public interface WindowPanelEventBus {
    void onCellClicked(int id, Cell cell);
    void onDiceClicked(DiceButton diceButton,Dice dice);
}