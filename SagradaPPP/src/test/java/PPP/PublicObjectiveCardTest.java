package PPP;

import PPP.Cards.PublicObjectiveCard;
import PPP.Cards.PublicObjectiveCard1;
import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.Assert.assertEquals;

public class PublicObjectiveCardTest {

    @Test
    public static void card1() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        boolean result;

        PublicObjectiveCard card = new PublicObjectiveCard1();

        result = panel.addDiceOnCellWithIndex(0,new Dice(Color.values()[0], 1));
        result = panel.addDiceOnCellWithIndex(1,new Dice(Color.values()[1], 2));
        result = panel.addDiceOnCellWithIndex(2,new Dice(Color.values()[2], 1));
        result = panel.addDiceOnCellWithIndex(3,new Dice(Color.values()[3], 2));
        result = panel.addDiceOnCellWithIndex(4,new Dice(Color.values()[4], 1));

        result = panel.addDiceOnCellWithIndex(5,new Dice(Color.values()[1], 3));
        result = panel.addDiceOnCellWithIndex(6,new Dice(Color.values()[2], 4));
        result = panel.addDiceOnCellWithIndex(7,new Dice(Color.values()[3], 3));
        result = panel.addDiceOnCellWithIndex(8,new Dice(Color.values()[4], 4));
        result = panel.addDiceOnCellWithIndex(9,new Dice(Color.values()[0], 3));

        result = panel.addDiceOnCellWithIndex(10,new Dice(Color.values()[2], 1));
        result = panel.addDiceOnCellWithIndex(11,new Dice(Color.values()[3], 2));
        result = panel.addDiceOnCellWithIndex(12,new Dice(Color.values()[4], 1));
        result = panel.addDiceOnCellWithIndex(13,new Dice(Color.values()[0], 2));
        result = panel.addDiceOnCellWithIndex(14,new Dice(Color.values()[1], 1));

        result = panel.addDiceOnCellWithIndex(15,new Dice(Color.values()[3], 3));
        result = panel.addDiceOnCellWithIndex(16,new Dice(Color.values()[4], 4));
        result = panel.addDiceOnCellWithIndex(17,new Dice(Color.values()[0], 3));
        result = panel.addDiceOnCellWithIndex(18,new Dice(Color.values()[1], 4));
        result = panel.addDiceOnCellWithIndex(19,new Dice(Color.values()[2], 3));

        assertEquals(24, card.getScore(panel));

    }
}
