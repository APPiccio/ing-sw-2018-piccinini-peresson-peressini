package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;

/**
 * Timer used while waiting for user's panel choice
 */
public class PanelChoiceTimer extends Thread {
    private long startTime;
    Game game;

    PanelChoiceTimer(long startTime, Game game){
        this.startTime = startTime;
        this.game = game;
        this.setName("panelChoiceThread");
    }

    @Override
    public void run(){
        while ((System.currentTimeMillis() < startTime + StaticValues.TURN_DURATION) && !isInterrupted()){
            //wait
        }
        if(!isInterrupted()) {
            game.panelChoiceTimerExpired = true;
        }
    }
}
