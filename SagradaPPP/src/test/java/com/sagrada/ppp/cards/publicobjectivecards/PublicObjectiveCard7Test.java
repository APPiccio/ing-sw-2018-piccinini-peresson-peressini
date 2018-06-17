package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard7Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard7();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(10*2, publicObjectiveCard.getScore(TestPanels.panel_7_0()));
        assertEquals(5*2, publicObjectiveCard.getScore(TestPanels.panel_7_1()));
        assertEquals(9*2, publicObjectiveCard.getScore(TestPanels.panel_7_2()));
    }

}