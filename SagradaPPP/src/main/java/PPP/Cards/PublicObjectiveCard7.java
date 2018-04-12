package PPP.Cards;

import PPP.StaticValues;
import PPP.WindowPanel;

//Deep Shades: set of 5 & 6 values anywhere

public class PublicObjectiveCard7 extends PublicObjectiveCard implements PublicObjectiveCardAction {

    private int numberOfFive = 0;
    private int numberOfSix = 0;

    public PublicObjectiveCard7() {
        super(StaticValues.PUBLICOBJECTIVECARD7_NAME, 7);
    }

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
