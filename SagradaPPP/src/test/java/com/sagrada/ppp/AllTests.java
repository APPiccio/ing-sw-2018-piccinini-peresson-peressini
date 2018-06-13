package com.sagrada.ppp;

import com.sagrada.ppp.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
        CellTest.class,
        ColorTest.class,
        DiceBagTest.class,
        DiceTest.class,
        RoundTrackTest.class
})
public class AllTests {

}