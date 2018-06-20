package com.sagrada.ppp.cards.toolcards;

import org.junit.*;

import static org.junit.Assert.*;

public class ToolCardTest {

    private ToolCard toolCard;

    @Before
    public void setUp() {
        toolCard = new ToolCard1();
    }

    @Test
    public void getId() {
        assertEquals(1, toolCard.getId());
        toolCard = new ToolCard2();
        assertEquals(2, toolCard.getId());
        toolCard = new ToolCard3();
        assertEquals(3, toolCard.getId());
        toolCard = new ToolCard4();
        assertEquals(4, toolCard.getId());
        toolCard = new ToolCard5();
        assertEquals(5, toolCard.getId());
        toolCard = new ToolCard6();
        assertEquals(6, toolCard.getId());
        toolCard = new ToolCard7();
        assertEquals(7, toolCard.getId());
        toolCard = new ToolCard8();
        assertEquals(8, toolCard.getId());
        toolCard = new ToolCard9();
        assertEquals(9, toolCard.getId());
        toolCard = new ToolCard10();
        assertEquals(10, toolCard.getId());
        toolCard = new ToolCard11();
        assertEquals(11, toolCard.getId());
        toolCard = new ToolCard12();
        assertEquals(12, toolCard.getId());
    }

    @Test
    public void getCost() {
        assertEquals(1, toolCard.getCost());

        toolCard.setUsed();

        assertEquals(2, toolCard.getCost());
    }

    @Test
    public void toStringTest() {
        assertEquals("ToolCard ---> Card ID: " + toolCard.getId() + ", Card cost: " + toolCard.getCost() +
                ", Card name: Grozing Pliers, Action: After drafting, increase or decrease the value of " +
                "the drafted die by 1 (1 may not change to 6, or 6 to 1)", toolCard.toString()  );
    }

}