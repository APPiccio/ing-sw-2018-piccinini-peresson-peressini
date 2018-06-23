package com.sagrada.ppp;

import com.sagrada.ppp.cards.publicobjectivecards.*;
import com.sagrada.ppp.cards.toolcards.*;
import com.sagrada.ppp.cards.toolcards.ToolCardTest;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.commands.*;
import com.sagrada.ppp.network.server.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({

        ToolCardTest.class,
        CommandToolCard1Test.class,
        CommandToolCard2Test.class,
        CommandToolCard3Test.class,
        CommandToolCard4Test.class,
        CommandToolCard5Test.class,
        CommandToolCard6Test.class,
        CommandToolCard7Test.class,
        CommandToolCard8Test.class,
        CommandToolCard9Test.class,
        CommandToolCard10Test.class,
        CommandToolCard11Test.class,
        CommandToolCard12Test.class,

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

        ToolCardNotificationMessageTest.class,
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
        PlayerTest.class,
        RoundTrackTest.class,
        WindowPanelTest.class,
        ServiceTest.class,
        LobbyTimerTest.class,
        ServiceTest.class,
        PlayerScoreTest.class,
        PlaceDiceResultTest.class,
        ReconnectionResultTest.class,
        ToolCardFlagsTest.class,
        ToolCardParametersTest.class,
        UseToolCardResultTest.class,

        DetachGameObserverRequestTest.class,
        DicePlacedNotificationTest.class,
        DisableAFKRequestTest.class,
        DisconnectionRequestTest.class,
        DisconnectionResponseTest.class,
        EndGameNotificationTest.class,
        EndTurnNotificationTest.class,
        EndTurnRequestTest.class,
        GameStartNotificationTest.class,
        GetLegalPositionRequestTest.class,
        GameTest.class
})
public class AllTests {

}