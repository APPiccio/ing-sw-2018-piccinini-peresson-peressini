package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard10Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard10();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(12, publicObjectiveCard.getScore(TestPanels.panel_10_0()));
        assertEquals(16, publicObjectiveCard.getScore(TestPanels.panel_10_1()));
        assertEquals(12, publicObjectiveCard.getScore(TestPanels.panel_10_2()));
        assertEquals(16, publicObjectiveCard.getScore(TestPanels.panel_10_3()));
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.panel_10_4()));
        assertEquals(8, publicObjectiveCard.getScore(TestPanels.panel_10_5()));
    }

}