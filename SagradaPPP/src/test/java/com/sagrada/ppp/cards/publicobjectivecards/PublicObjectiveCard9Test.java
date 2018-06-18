package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.cards.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard9Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard9();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(11, publicObjectiveCard.getScore(TestPanels.panel_9_0()));
        assertEquals(20, publicObjectiveCard.getScore(TestPanels.panel_9_1()));
        assertEquals(10, publicObjectiveCard.getScore(TestPanels.panel_9_2()));
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.panel_9_3()));
        assertEquals(6, publicObjectiveCard.getScore(TestPanels.panel_9_4()));
        assertEquals(10, publicObjectiveCard.getScore(TestPanels.panel_9_5()));
        assertEquals(12, publicObjectiveCard.getScore(TestPanels.panel_9_6()));
        assertEquals(13, publicObjectiveCard.getScore(TestPanels.panel_9_7()));
    }

}