package com.sagrada.ppp.cards;

import com.sagrada.ppp.Color;
import com.sagrada.ppp.Dice;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.WindowPanel;

import java.util.ArrayList;

//Row Color Variety: rows with no repeated colors

public class PublicObjectiveCard1 extends PublicObjectiveCard {

    public PublicObjectiveCard1() {
        super(StaticValues.PUBLICOBJECTIVECARD1_NAME, 1);
    }

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
