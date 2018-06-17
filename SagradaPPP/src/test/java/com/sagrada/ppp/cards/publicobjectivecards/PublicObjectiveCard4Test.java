package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.TestPanels;
import org.junit.*;

import static org.junit.Assert.*;

public class PublicObjectiveCard4Test {

    private PublicObjectiveCard publicObjectiveCard;

    @Before
    public void setUp() {
        publicObjectiveCard = new PublicObjectiveCard4();
    }

    @Test
    public void getScore() {
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.createBlankPanel()));
        assertEquals(20, publicObjectiveCard.getScore(TestPanels.panel_4_0()));
        assertEquals(8, publicObjectiveCard.getScore(TestPanels.panel_4_1()));
        assertEquals(0, publicObjectiveCard.getScore(TestPanels.panel_4_2()));
    }

}