package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

import java.util.EnumMap;

/**
 *  Card description:
 *  Color Variety: sets of one of each color anywhere
 */
public class PublicObjectiveCard10 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard10() {
        super(StaticValues.PUBLIC_OBJECTIVE_CARD_10_NAME, 10);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        EnumMap<Color, Integer> colorVariety = new EnumMap<>(Color.class);
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = playerWindowPanel.getCell(i).getDiceOn();
            if (tempDice != null) {
                Color tempDiceColor = tempDice.getColor();
                if (colorVariety.containsKey(tempDiceColor)) {
                    colorVariety.computeIfPresent(tempDiceColor, (key, value) -> value + 1);
                } else {
                    colorVariety.put(tempDiceColor, 1);
                }
            }
        }
        if (colorVariety.size() != StaticValues.NUMBER_OF_COLORS) {
            return 0;
        }
        else {
            int minValue = Integer.MAX_VALUE;
            for (Color color : colorVariety.keySet()) {
                int tempValue = colorVariety.get(color);
                if (tempValue < minValue) {
                    minValue = tempValue;
                }
            }
            return minValue * 4;
        }
    }

}