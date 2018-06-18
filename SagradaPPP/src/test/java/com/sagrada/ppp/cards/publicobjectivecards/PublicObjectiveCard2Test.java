package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.cards.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard2Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard2();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(25, publicObjectiveCard.getScore(TestPanels.panel_2_0()));
        assertEquals(15, publicObjectiveCard.getScore(TestPanels.panel_2_1()));
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.panel_2_2()));
    }

}