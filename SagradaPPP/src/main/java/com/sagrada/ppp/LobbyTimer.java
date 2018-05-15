package com.sagrada.ppp;

import com.sagrada.ppp.utils.StaticValues;

import java.util.ArrayList;

public class LobbyTimer extends Thread {

    long startTime;
    Game game;

    public LobbyTimer(long startTime, Game game){
        this.startTime = startTime;
        this.game = game;
    }

    public void run(){
        while ((System.currentTimeMillis() < startTime + StaticValues.getLobbyTimer()) && !isInterrupted()){

        }
        if(!isInterrupted()) {
            game.notifyTimerChanges(TimerStatus.FINISH);
            Runnable myrunnable = () -> {
                game.init();
            };
            new Thread(myrunnable).start();

        }
    }
}
