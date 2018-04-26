package com.sagrada.ppp.Cards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.Utils.StaticValues;
import com.sagrada.ppp.WindowPanel;

//Light Shades: sets of 1 & 2 values anywhere

public class PublicObjectiveCard5 extends PublicObjectiveCard {

    public PublicObjectiveCard5() {
        super(StaticValues.PUBLICOBJECTIVECARD5_NAME, 5);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        int numberOfOne = 0;
        int numberOfTwo = 0;
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = playerWindowPanel.getCell(i).getDiceOn();
            if (tempDice != null) {
                int tempValue = tempDice.getValue();
                if (tempValue == 1) {
                    numberOfOne++;
                }
                else if (tempValue == 2) {
                    numberOfTwo++;
                }
            }
        }
        if(numberOfOne < numberOfTwo) {
            return numberOfOne * 2;
        }
        else {
            return numberOfTwo * 2;
        }
    }

}