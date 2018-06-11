package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;

public class
PanelChoiceTimer extends Thread {
    long startTime;
    Game game;

    public PanelChoiceTimer(long startTime, Game game){
        this.startTime = startTime;
        this.game = game;
        this.setName("panelChoiceThread");
    }

    public void run(){
        while ((System.currentTimeMillis() < startTime + StaticValues.TURN_DURATION) && !isInterrupted()){

        }
        if(!isInterrupted()) {
            game.panelChoiceTimerExpired = true;
        }
    }
}
