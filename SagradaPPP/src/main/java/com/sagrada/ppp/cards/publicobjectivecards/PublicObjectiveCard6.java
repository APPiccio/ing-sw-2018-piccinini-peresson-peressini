package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

/**
 *  Card description:
 *  Medium Shades: sets of 3 & 4 values anywhere
 */
public class PublicObjectiveCard6 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard6() {
        super(StaticValues.PUBLICOBJECTIVECARD6_NAME, 6);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
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