package PPP.Cards;

import PPP.StaticValues;
import PPP.WindowPanel;

//Medium Shades: set of 3 & 4 values anywhere

public class PublicObjectiveCard6 extends PublicObjectiveCard implements PublicObjectiveCardAction {

    private int numberOfThree = 0;
    private int numberOfFour = 0;

    public PublicObjectiveCard6() {
        super(StaticValues.PUBLICOBJECTIVECARD6_NAME, 6);
    }

    public int getScore(WindowPanel playerWindowPanel) {
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 1) {
                numberOfThree++;
            }
            else if ((playerWindowPanel.getCellWithIndex(i)).getValue() == 2) {
                numberOfFour++;
            }
        }
        if (numberOfThree < numberOfFour) {
            return numberOfThree * 2;
        }
        else {
            return numberOfFour * 2;
        }
    }

}
