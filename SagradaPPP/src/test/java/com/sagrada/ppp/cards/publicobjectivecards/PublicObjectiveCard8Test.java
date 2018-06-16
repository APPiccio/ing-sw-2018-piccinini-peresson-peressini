package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard8Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard8();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.panel_8_0()));
        assertEquals(5, publicObjectiveCard.getScore(TestPanels.panel_8_1()));
        assertEquals(10, publicObjectiveCard.getScore(TestPanels.panel_8_2()));
        assertEquals(15, publicObjectiveCard.getScore(TestPanels.panel_8_3()));
    }

}