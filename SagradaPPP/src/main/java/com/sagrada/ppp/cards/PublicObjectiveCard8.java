package com.sagrada.ppp.cards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

import java.util.HashMap;

/**
 *  Card description:
 *  Shade Variety: sets of one of each value anywhere
 */
public class PublicObjectiveCard8 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard8() {
        super(StaticValues.PUBLICOBJECTIVECARD8_NAME, 8);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        HashMap<Integer, Integer> shadeVariety = new HashMap<>();
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = playerWindowPanel.getCell(i).getDiceOn();
            if (tempDice != null) {
                int tempDiceValue = tempDice.getValue();
                if (shadeVariety.containsKey(tempDiceValue)) {
                    shadeVariety.computeIfPresent(tempDiceValue, (key, value) -> value + 1);
                } else {
                    shadeVariety.put(tempDiceValue, 1);
                }
            }
        }
        if (shadeVariety.size() != StaticValues.DICE_FACES) {
            return 0;
        }
        else {
            int minValue = Integer.MAX_VALUE;
            for (Integer value : shadeVariety.keySet()) {
                int tempValue = shadeVariety.get(value);
                if (tempValue < minValue) {
                    minValue = tempValue;
                }
            }
            return minValue * 5;
        }
    }

}