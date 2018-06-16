package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard3Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard3();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(20, publicObjectiveCard.getScore(TestPanels.panel_3_0()));
        assertEquals(10, publicObjectiveCard.getScore(TestPanels.panel_3_1()));
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.panel_3_2()));
    }

}