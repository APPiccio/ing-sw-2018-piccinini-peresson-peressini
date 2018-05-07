package com.sagrada.ppp.cards;

import com.sagrada.ppp.Color;
import com.sagrada.ppp.Dice;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.WindowPanel;
import java.util.ArrayList;

/**
 *  Card description:
 *  Column Color Variety: columns with no repeated colors
 */
public class PublicObjectiveCard2 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard2() {
        super(StaticValues.PUBLICOBJECTIVECARD2_NAME, 2);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        ArrayList<Color> colors = new ArrayList<>();
        int coloredColumns = 0;
        for (int i = 0; i < StaticValues.PATTERN_COL; i++) {
            for (int j = 0; j < StaticValues.PATTERN_ROW; j++) {
                Dice tempDice = playerWindowPanel.getCell(j, i).getDiceOn();
                if(tempDice == null || colors.contains(tempDice.getColor())) {
                    break;
                }
                else {
                    colors.add(tempDice.getColor());
                }
            }
            if(colors.size() == 4) {
                coloredColumns++;
            }
            colors.clear();
        }
        return coloredColumns * 5;
    }

}