package com.sagrada.ppp.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UseToolCardResultTest {
    @Test
    public void testUseToolCardResult(){
        ArrayList<Dice> draftPool = new ArrayList<>();
        RoundTrack roundTrack = new RoundTrack();
        ArrayList<Player> players = new ArrayList<>();
        Dice dice = new Dice();
        UseToolCardResult useToolCardResult = new UseToolCardResult(true,2,3,draftPool,roundTrack,players,dice,"test");
    }

}