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
                Runnable myrRunnable = () -> {
                    game.init();
                    this.setName("gameThread");
                };
                new Thread(myrRunnable).start();
            }

        }
    }
}
