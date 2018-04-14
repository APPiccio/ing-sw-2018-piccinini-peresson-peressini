package PPP.Cards;

import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;

//Deep Shades: sets of 5 & 6 values anywhere

public class PublicObjectiveCard7 extends PublicObjectiveCard{

    public PublicObjectiveCard7() {
        super(StaticValues.PUBLICOBJECTIVECARD7_NAME, 7);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        int numberOfFive = 0;
        int numberOfSix = 0;

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {

            Dice tempDice = playerWindowPanel.getCellWithIndex(i).getDiceOn();

            if (tempDice == null) {
                continue;
            } else if ((tempDice.getValue() == 5)) {
                numberOfFive++;
            } else if ((tempDice.getValue() == 6)) {
                numberOfSix++;
            }
        }
        if(numberOfFive < numberOfSix) {
            return numberOfFive * 2;
        }
        else {
            return numberOfSix * 2;
        }
    }

}
