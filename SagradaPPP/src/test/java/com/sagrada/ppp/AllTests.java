package com.sagrada.ppp;

import com.sagrada.ppp.cards.publicobjectivecards.*;
import com.sagrada.ppp.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
        PublicObjectiveCard1Test.class,
        PublicObjectiveCard2Test.class,

        CellTest.class,
        ColorTest.class,
        DiceBagTest.class,
        DicePlacedMessageTest.class,
        DiceTest.class,
        EndTurnMessageTest.class,
        PlayerTest.class,
        RoundTrackTest.class
})
public class AllTests {

}