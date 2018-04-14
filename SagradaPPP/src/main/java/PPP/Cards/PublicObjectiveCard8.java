package PPP.Cards;

import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.Arrays;

//Shade Variety: sets of one of each value anywhere

public class PublicObjectiveCard8 extends PublicObjectiveCard {

    public PublicObjectiveCard8() {
        super(StaticValues.PUBLICOBJECTIVECARD8_NAME, 8);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        int[] occurrences = {0, 0, 0, 0, 0, 0};

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {

            Dice tempDice = playerWindowPanel.getCellWithIndex(i).getDiceOn();

            if (tempDice == null) {
                continue;
            }
            else if ((tempDice.getValue() == 1)) {
                occurrences[0]++;
            }
            else if ((tempDice.getValue() == 2)) {
                occurrences[1]++;
            }
            else if ((tempDice.getValue() == 3)) {
                occurrences[2]++;
            }
            else if ((tempDice.getValue() == 4)) {
                occurrences[3]++;
            }
            else if ((tempDice.getValue() == 5)) {
                occurrences[4]++;
            }
            else {
                occurrences[5]++;
            }
        }
        return Arrays.stream(occurrences).min().getAsInt() * 5;
    }

}
