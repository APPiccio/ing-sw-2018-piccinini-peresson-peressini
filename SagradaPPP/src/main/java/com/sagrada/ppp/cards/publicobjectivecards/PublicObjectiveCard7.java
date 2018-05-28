package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

/**
 *  Card description:
 *  Deep Shades: sets of 5 & 6 values anywhere
 */
public class PublicObjectiveCard7 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard7() {
        super(StaticValues.PUBLICOBJECTIVECARD7_NAME, 7);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        int numberOfFive = 0;
        int numberOfSix = 0;
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = playerWindowPanel.getCell(i).getDiceOn();
            if (tempDice != null) {
                int tempValue = tempDice.getValue();
                if (tempValue == 5) {
                    numberOfFive++;
                }
                else if (tempValue == 6) {
                    numberOfSix++;
                }
            }
        }
        if(numberOfFive < numberOfSix) {
            return numberOfFive * 2;
        }
        else {
            return numberOfSix * 2;
        }
    }

}