package com.sagrada.ppp.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ToolCardNotificationMessageTest {

    @Test
    public void testToolCardNotificationMessage(){
        Player player = new Player("test");
        ArrayList<Dice> draftPool = new ArrayList<>();
        RoundTrack roundTrack = new RoundTrack();


        ToolCardNotificationMessage toolCardNotificationMessage = new ToolCardNotificationMessage(1,player,draftPool,roundTrack,1);
        assertEquals(1,toolCardNotificationMessage.toolCardID);
        assertEquals(player,toolCardNotificationMessage.player);
        assertEquals(roundTrack,toolCardNotificationMessage.roundTrack);
        assertEquals(draftPool,toolCardNotificationMessage.draftPool);
        assertEquals(1,toolCardNotificationMessage.toolCardCost);

    }

}