package PPP.Cards;

import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;

//Medium Shades: sets of 3 & 4 values anywhere

public class PublicObjectiveCard6 extends PublicObjectiveCard {

    public PublicObjectiveCard6() {
        super(StaticValues.PUBLICOBJECTIVECARD6_NAME, 6);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        int numberOfThree = 0;
        int numberOfFour = 0;

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {

            Dice tempDice = playerWindowPanel.getCellWithIndex(i).getDiceOn();

            if (tempDice == null) {
                continue;
            } else if ((tempDice.getValue() == 3)) {
                numberOfThree++;
            } else if ((tempDice.getValue() == 4)) {
                numberOfFour++;
            }
        }
        if(numberOfThree < numberOfFour) {
            return numberOfThree * 2;
        }
        else {
            return numberOfFour * 2;
        }
    }

}
