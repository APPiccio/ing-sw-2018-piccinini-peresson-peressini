package com.sagrada.ppp.cards.publicobjectivecards;

import org.junit.Test;

import static org.junit.Assert.*;

public class PublicObjectiveCardTest {

    private PublicObjectiveCard publicObjectiveCard;

    @Test
    public void getId() {
        publicObjectiveCard = new PublicObjectiveCard1();
        assertEquals(1, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard2();
        assertEquals(2, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard3();
        assertEquals(3, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard4();
        assertEquals(4, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard5();
        assertEquals(5, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard6();
        assertEquals(6, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard7();
        assertEquals(7, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard8();
        assertEquals(8, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard9();
        assertEquals(9, publicObjectiveCard.getId());
        publicObjectiveCard = new PublicObjectiveCard10();
        assertEquals(10, publicObjectiveCard.getId());
    }

    @Test
    public void getName() {
        publicObjectiveCard = new PublicObjectiveCard1();
        assertEquals("Row Color Variety", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard2();
        assertEquals("Column Color Variety", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard3();
        assertEquals("Row Shade Variety", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard4();
        assertEquals("Column Shade Variety", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard5();
        assertEquals("Light Shades", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard6();
        assertEquals("Medium Shades", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard7();
        assertEquals("Deep Shades", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard8();
        assertEquals("Shade Variety", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard9();
        assertEquals("Color Diagonals", publicObjectiveCard.getName());
        publicObjectiveCard = new PublicObjectiveCard10();
        assertEquals("Color Variety", publicObjectiveCard.getName());
    }

    @Test
    public void toStringTest() {
        publicObjectiveCard = new PublicObjectiveCard1();
        assertEquals("Public objective card ---> Card ID = " + publicObjectiveCard.getId() +
                ", Card name: " + publicObjectiveCard.getName() +
                ", Description: 6pts x Row with no repeated colors", publicObjectiveCard.toString());
    }

}