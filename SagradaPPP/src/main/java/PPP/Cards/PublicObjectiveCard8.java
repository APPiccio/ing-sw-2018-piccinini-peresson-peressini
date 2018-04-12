package PPP.Cards;

import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.Arrays;

//Shade Variety: Sets of one of each value anywhere

public class PublicObjectiveCard8 extends PublicObjectiveCard implements PublicObjectiveCardAction {

    int [] occurrences = {0, 0, 0, 0, 0, 0};

    public PublicObjectiveCard8() {
        super(StaticValues.PUBLICOBJECTIVECARD8_NAME, 8);
    }

    public int getScore(WindowPanel playerWindowPanel) {
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 1) {
                occurrences[0]++;
            }
            else if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 2) {
                occurrences[1]++;
            }
            else if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 3) {
                occurrences[2]++;
            }
            else if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 4) {
                occurrences[3]++;
            }
            else if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 5) {
                occurrences[4]++;
            }
            else {
                occurrences[5]++;
            }
        }
        return Arrays.stream(occurrences).min().getAsInt() * 5;
    }

}
