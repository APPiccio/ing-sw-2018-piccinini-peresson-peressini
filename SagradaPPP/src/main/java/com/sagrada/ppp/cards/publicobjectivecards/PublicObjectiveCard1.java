package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

import java.util.ArrayList;

/**
 *  Card description:
 *  Row Color Variety: rows with no repeated colors
 */
public class PublicObjectiveCard1 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard1() {
        super(StaticValues.PUBLIC_OBJECTIVE_CARD_1_NAME, 1);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        ArrayList<Color> colors = new ArrayList<>();
        int coloredRows = 0;
        for (int i = 0; i < StaticValues.PATTERN_ROW; i++) {
            for (int j = 0; j < StaticValues.PATTERN_COL; j++) {
                Dice tempDice = playerWindowPanel.getCell(i, j).getDiceOn();
                if(tempDice == null || colors.contains(tempDice.getColor())) {
                    break;
                }
                else {
                    colors.add(tempDice.getColor());
                }
            }
            if(colors.size() == 5) {
                coloredRows++;
            }
            colors.clear();
        }
        return coloredRows * 6;
    }

}