package com.sagrada.ppp.Cards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.StaticValues;
import com.sagrada.ppp.WindowPanel;

import java.util.HashMap;

//Shade Variety: sets of one of each value anywhere

public class PublicObjectiveCard8 extends PublicObjectiveCard {

    public PublicObjectiveCard8() {
        super(StaticValues.PUBLICOBJECTIVECARD8_NAME, 8);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        HashMap<Integer, Integer> shadeVariety = new HashMap<>();
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = playerWindowPanel.getCellWithIndex(i).getDiceOn();
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