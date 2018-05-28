package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

import java.util.ArrayList;

/**
 *  Card description:
 *  Row Shade Variety: rows with no repeated values
 */
public class PublicObjectiveCard3 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard3() {
        super(StaticValues.PUBLICOBJECTIVECARD3_NAME, 3);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
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