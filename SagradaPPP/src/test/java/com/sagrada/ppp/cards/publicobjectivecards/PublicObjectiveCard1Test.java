package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard1Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard1();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(24, publicObjectiveCard.getScore(TestPanels.panel_1_0()));
        assertEquals(12, publicObjectiveCard.getScore(TestPanels.panel_1_1()));
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.panel_1_2()));
    }



}