package PPP;

import PPP.Cards.PublicObjectiveCard;
import PPP.Cards.PublicObjectiveCard1;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class PublicObjectiveCardTest {

    @Test
    public static void card1() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        boolean result;

        for (int j = 0; j < StaticValues.PATTERN_ROW; j = j++) {
            for (int i = 0; i < StaticValues.PATTERN_COL; i++) {
                if (j % 2 != 0) {
                    result = panel.addDiceOnCellWithIndex(i, new Dice(Color.values()[i]));
                    while (!result) {
                        result = panel.addDiceOnCellWithIndex(i, new Dice(Color.values()[i]));
                    }
                } else {
                    result = panel.addDiceOnCellWithIndex(i, new Dice(Color.values()[StaticValues.PATTERN_COL - 1 - i]));
                    while (!result) {
                        result = panel.addDiceOnCellWithIndex(i, new Dice(Color.values()[i]));
                    }
                }
            }

            ArrayList<Cell> cells = new ArrayList<>();
            for (Cell cell : cells) {
                System.out.println(cell.getDiceOn().getColor());
            }

            PublicObjectiveCard card = new PublicObjectiveCard1();

            assertEquals(24, card.getScore(panel));

        }
    }
}
