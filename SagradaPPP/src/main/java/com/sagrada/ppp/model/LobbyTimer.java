package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;

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
            if(game.pingAllLobbyObservers()) {
                game.notifyTimerChanges(TimerStatus.FINISH);
                Runnable myrunnable = () -> {
                    game.init();
                };
                new Thread(myrunnable).start();
            }
        }
    }
}
