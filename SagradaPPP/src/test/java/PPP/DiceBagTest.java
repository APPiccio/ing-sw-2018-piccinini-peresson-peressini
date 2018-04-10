package PPP;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class DiceBagTest{

    @Test
    public static void diceBagGenTest() throws IllegalDiceValueException {

        DiceBag diceBag = new DiceBag();

        ArrayList<Dice> dices = diceBag.getDiceBag();

        for (Color color : Color.values()) {
            assertEquals(18, diceBag.numberOfColor(color));
        }

        assertEquals(90, diceBag.size());
    }



}