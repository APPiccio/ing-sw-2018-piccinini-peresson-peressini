package PPP.Cards;

import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.ArrayList;

//Column Shade Variety: columns with no repeated values

public class PublicObjectiveCard4 extends PublicObjectiveCard{

    public PublicObjectiveCard4() {
        super(StaticValues.PUBLICOBJECTIVECARD4_NAME, 4);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        ArrayList<Integer> values = new ArrayList<>();
        int differentColumns = 0;

        for (int i = 0; i < StaticValues.PATTERN_COL; i++) {
            for (int j = 0; j < StaticValues.PATTERN_ROW; j++) {
                Dice tempDice = playerWindowPanel.getCellWithPosition(j, i).getDiceOn();
                if(tempDice == null || values.contains(tempDice.getValue())) {
                    break;
                }
                else {
                    values.add(tempDice.getValue());
                }
            }
            if(values.size() == 4) {
                differentColumns++;
            }
            values.clear();
        }
        return differentColumns * 4;
    }

}
