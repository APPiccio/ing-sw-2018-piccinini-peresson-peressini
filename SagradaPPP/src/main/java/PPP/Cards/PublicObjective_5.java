package PPP.Cards;

import PPP.StaticValues;
import PPP.WindowPanel;

//Light Shades: set of 1 & 2 values anywhere

public class PublicObjective_5 extends PublicObjective implements PublicObjectiveCard {

    private int numberOfOne = 0;
    private int numberOfTwo = 0;

    public int getScore(WindowPanel playerWindowPanel) {
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 1) {
                numberOfOne++;
            }
            else if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 2) {
                numberOfTwo++;
            }
        }
        if (numberOfOne < numberOfTwo) {
            return numberOfOne * 2;
        }
        else {
            return numberOfTwo * 2;
        }
    }

}
