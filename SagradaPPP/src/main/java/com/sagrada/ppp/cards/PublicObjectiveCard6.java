package com.sagrada.ppp.cards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.WindowPanel;

//Medium Shades: sets of 3 & 4 values anywhere

public class PublicObjectiveCard6 extends PublicObjectiveCard {

    public PublicObjectiveCard6() {
        super(StaticValues.PUBLICOBJECTIVECARD6_NAME, 6);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        int numberOfThree = 0;
        int numberOfFour = 0;
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = playerWindowPanel.getCell(i).getDiceOn();
            if (tempDice != null) {
                int tempValue = tempDice.getValue();
                if (tempValue == 3) {
                    numberOfThree++;
                }
                else if (tempValue == 4) {
                    numberOfFour++;
                }
            }
        }
        if(numberOfThree < numberOfFour) {
            return numberOfThree * 2;
        }
        else {
            return numberOfFour * 2;
        }
    }

}