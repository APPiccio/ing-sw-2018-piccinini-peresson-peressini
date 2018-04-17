package PPP;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DiceTest {
    @Test
    public static void diceValueTest()  {
        for (Color color: Color.values()) {
            assertEquals(18,new DiceBag().numberOfColor(color) );
        }

    }
}
