package com.sagrada.ppp;

import com.sagrada.ppp.cards.publicobjectivecards.*;
import com.sagrada.ppp.model.WindowPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class PublicObjectiveCardTest {

    @Test
    public void testAllPublicObjectiveCard() {
        card1();
        card2();
        card3();
        card4();
        card5();
        card6();
        card7();
        card8();
        card9();
        card10();
    }

    @Test
    public void card1()  {
        PublicObjectiveCard card = new PublicObjectiveCard1();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(24, card.getScore(TestPanels.panel_210()));
        assertEquals(12, card.getScore(TestPanels.panel_211()));
        assertEquals(0, card.getScore(TestPanels.panel_212()));
    }

    @Test
    public void card2() {
        PublicObjectiveCard card = new PublicObjectiveCard2();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(25, card.getScore(TestPanels.panel_220()));
        assertEquals(15, card.getScore(TestPanels.panel_221()));
        assertEquals(0, card.getScore(TestPanels.panel_222()));
    }

    @Test
    public void card3() {
        PublicObjectiveCard card = new PublicObjectiveCard3();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(20, card.getScore(TestPanels.panel_230()));
        assertEquals(10, card.getScore(TestPanels.panel_231()));
        assertEquals(0, card.getScore(TestPanels.panel_232()));
    }

    @Test
    public void card4()  {
        PublicObjectiveCard card = new PublicObjectiveCard4();
        assertEquals(0, card.getScore(new WindowPanel(0,0)));
        assertEquals(20, card.getScore(TestPanels.panel_240()));
        assertEquals(8, card.getScore(TestPanels.panel_241()));
        assertEquals(0, card.getScore(TestPanels.panel_242()));
    }

    @Test
    public void card5()  {
        PublicObjectiveCard card = new PublicObjectiveCard5();
        assertEquals(0, card.getScore(new WindowPanel(0,0))); //empty panel
        assertEquals(0, card.getScore(TestPanels.panel_60()));
        assertEquals(0, card.getScore(TestPanels.panel_61()));
        assertEquals(0, card.getScore(TestPanels.panel_70()));
        assertEquals(0, card.getScore(TestPanels.panel_71()));
        assertEquals(10*2, card.getScore(TestPanels.panel_50())); //master panel
        assertEquals(5*2, card.getScore(TestPanels.panel_51()));
        assertEquals(9*2, card.getScore(TestPanels.panel_52()));
    }

    @Test
    public void card6()  {
        PublicObjectiveCard card = new PublicObjectiveCard6();
        assertEquals(0, card.getScore(new WindowPanel(0,0))); //empty panel
        assertEquals(0, card.getScore(TestPanels.panel_50()));
        assertEquals(0, card.getScore(TestPanels.panel_51()));
        assertEquals(0, card.getScore(TestPanels.panel_70()));
        assertEquals(0, card.getScore(TestPanels.panel_71()));
        assertEquals(10*2, card.getScore(TestPanels.panel_60())); //master panel
        assertEquals(5*2, card.getScore(TestPanels.panel_61()));
        assertEquals(9*2, card.getScore(TestPanels.panel_62()));
    }

    @Test
    public void card7()  {
        PublicObjectiveCard card = new PublicObjectiveCard7();
        assertEquals(0, card.getScore(new WindowPanel(0,0))); //empty panel
        assertEquals(0, card.getScore(TestPanels.panel_50()));
        assertEquals(0, card.getScore(TestPanels.panel_51()));
        assertEquals(0, card.getScore(TestPanels.panel_60()));
        assertEquals(0, card.getScore(TestPanels.panel_61()));
        assertEquals(10*2, card.getScore(TestPanels.panel_70())); //master panel
        assertEquals(5*2, card.getScore(TestPanels.panel_71()));
        assertEquals(9*2, card.getScore(TestPanels.panel_72()));
    }

    @Test
    public void card8()  {
        PublicObjectiveCard card = new PublicObjectiveCard8();

        assertEquals(0, card.getScore(TestPanels.panel_180()));
        assertEquals(15, card.getScore(TestPanels.panel_181()));
        assertEquals(0, card.getScore(new WindowPanel(0,0)));

        assertEquals(10,card.getScore(TestPanels.panel_240()));
        assertEquals(0,card.getScore(TestPanels.panel_193()));
        assertEquals(0,card.getScore(TestPanels.panel_70()));
        assertEquals(5,card.getScore(TestPanels.panel_232()));
    }

    /**
     * This getScore() method doesn't check any dice value
     */
    @Test
    public void card9()  {

        PublicObjectiveCard card = new PublicObjectiveCard9();

        assertEquals(11, card.getScore(TestPanels.panel_190()));
        assertEquals(20, card.getScore(TestPanels.panel_191()));
        assertEquals(10, card.getScore(TestPanels.panel_192()));
        assertEquals(0, card.getScore(new WindowPanel(0,0)));

        assertEquals(10,card.getScore(TestPanels.panel_212()));
        assertEquals(6,card.getScore(TestPanels.panel_222()));
        assertEquals(12,card.getScore(TestPanels.panel_241()));
        assertEquals(13,card.getScore(TestPanels.panel_240()));
        assertEquals(0,card.getScore(TestPanels.panel_193()));


    }

    @Test
    public void card10()  {
        PublicObjectiveCard card = new PublicObjectiveCard10();

        assertEquals(12, card.getScore(TestPanels.panel_1100()));
        assertEquals(16, card.getScore(TestPanels.panel_1101()));
        assertEquals(0, card.getScore(new WindowPanel(0,0)));

        assertEquals(12,card.getScore(TestPanels.panel_240()));
        assertEquals(16,card.getScore(TestPanels.panel_70()));
        assertEquals(0,card.getScore(TestPanels.panel_71()));
        assertEquals(8,card.getScore(TestPanels.panel_232()));



    }

}