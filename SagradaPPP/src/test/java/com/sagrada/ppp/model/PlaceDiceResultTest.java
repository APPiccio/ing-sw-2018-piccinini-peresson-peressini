package com.sagrada.ppp.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlaceDiceResultTest {


    @Test
    public void testPlaceDiceResult(){
        ArrayList<Dice> draftPool = new ArrayList<>();
        for (int i = 0; i <10;i++) {
            draftPool.add(new Dice());
        }
        PlaceDiceResult placeDiceResult = new PlaceDiceResult("test_message",true,new WindowPanel(1,1),draftPool);
        assertEquals("test_message",placeDiceResult.message);
        assertEquals(new WindowPanel(1,1),placeDiceResult.panel);
        assertTrue(placeDiceResult.status);
        assertEquals(draftPool,placeDiceResult.draftPool);
    }
}