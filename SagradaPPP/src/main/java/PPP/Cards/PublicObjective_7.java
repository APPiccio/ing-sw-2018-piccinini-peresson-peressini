package PPP.Cards;

import PPP.StaticValues;
import PPP.WindowPanel;

//Deep Shades: set of 5 & 6 values anywhere

public class PublicObjective_7 extends PublicObjective implements PublicObjectiveCard {

    private int numberOfFive = 0;
    private int numberOfSix = 0;

    public int getScore(WindowPanel playerWindowPanel) {
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 1) {
                numberOfFive++;
            }
            else if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 2) {
                numberOfSix++;
            }
        }
        if (numberOfFive < numberOfSix) {
            return numberOfFive * 2;
        }
        else {
            return numberOfSix * 2;
        }
    }

}
