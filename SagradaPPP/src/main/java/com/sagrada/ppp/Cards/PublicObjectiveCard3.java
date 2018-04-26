package com.sagrada.ppp.Cards;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.Utils.StaticValues;
import com.sagrada.ppp.WindowPanel;

import java.util.ArrayList;

//Row Shade Variety: rows with no repeated values

public class PublicObjectiveCard3 extends PublicObjectiveCard {

    public PublicObjectiveCard3() {
        super(StaticValues.PUBLICOBJECTIVECARD3_NAME, 3);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        ArrayList<Integer> values = new ArrayList<>();
        int differentRows = 0;

        for (int i = 0; i < StaticValues.PATTERN_ROW; i++) {
            for (int j = 0; j < StaticValues.PATTERN_COL; j++) {
                Dice tempDice = playerWindowPanel.getCell(i, j).getDiceOn();
                if(tempDice == null || values.contains(tempDice.getValue())) {
                    break;
                }
                else {
                    values.add(tempDice.getValue());
                }
            }
            if(values.size() == 5) {
                differentRows++;
            }
            values.clear();
        }
        return differentRows * 5;
    }

}
