package PPP.Cards;

import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.ArrayList;

//Row Shade Variety: rows with no repeated values

public class PublicObjectiveCard3 extends PublicObjectiveCard{

    public PublicObjectiveCard3() {
        super(StaticValues.PUBLICOBJECTIVECARD3_NAME, 3);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        ArrayList<Integer> values = new ArrayList<>();
        int differentRows = 0;

        for (int i = 0; i < StaticValues.PATTERN_ROW; i++) {
            for (int j = 0; j < StaticValues.PATTERN_COL; j++) {
                Dice tempDice = playerWindowPanel.getCellWithPosition(i, j).getDiceOn();
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
