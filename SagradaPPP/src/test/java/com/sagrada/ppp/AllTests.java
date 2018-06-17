package com.sagrada.ppp;

import com.sagrada.ppp.cards.publicobjectivecards.*;
import com.sagrada.ppp.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
        PublicObjectiveCardTest.class,
        PublicObjectiveCard1Test.class,
        PublicObjectiveCard2Test.class,
        PublicObjectiveCard3Test.class,
        PublicObjectiveCard4Test.class,
        PublicObjectiveCard5Test.class,
        PublicObjectiveCard6Test.class,
        PublicObjectiveCard7Test.class,
        PublicObjectiveCard8Test.class,
        PublicObjectiveCard9Test.class,
        PublicObjectiveCard10Test.class,

        CellTest.class,
        ColorTest.class,
        DiceBagTest.class,
        DicePlacedMessageTest.class,
        DiceTest.class,
        EndTurnMessageTest.class,
        GameStartMessageTest.class,
        IsToolCardUsableResultTest.class,
        JoinGameResultTest.class,
        LeaveGameResultTest.class,
        LobbyTimerTest.class,
        PlayerTest.class,
        RoundTrackTest.class,
        WindowPanelTest.class
})
public class AllTests {

}