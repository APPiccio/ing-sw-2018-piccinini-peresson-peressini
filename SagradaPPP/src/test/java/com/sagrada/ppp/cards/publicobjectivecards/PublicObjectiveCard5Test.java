package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.cards.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard5Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard5();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(10*2, publicObjectiveCard.getScore(TestPanels.panel_5_0()));
        assertEquals(5*2, publicObjectiveCard.getScore(TestPanels.panel_5_1()));
        assertEquals(9*2, publicObjectiveCard.getScore(TestPanels.panel_5_2()));
    }

}