package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;

/**
 * As is said in the name, this Thread is used to handle lobby timer
 */
public class LobbyTimer extends Thread {

    long startTime;
    Game game;

    /**
     * @param startTime the starting time of the thread
     * @param game reference to the creator and runner of this object, needed to notify the game when the timer will be elapsed
     */
    public LobbyTimer(long startTime, Game game){
        this.startTime = startTime;
        this.game = game;
    }

    /**
     * The thread, if not interrupted, will wait the specified time and after that it will tell the game that it's time
     * to proceed. It also checks for undetected disconnection and stops the process if there aren't enough players
     */
    public void run(){
        while ((System.currentTimeMillis() < startTime + StaticValues.LOBBY_TIMER) && !isInterrupted()){

        }
        if(!isInterrupted()) {
            if(game.pingAllLobbyObservers()) {
                game.notifyTimerChanges(TimerStatus.FINISH);
                Runnable myrRunnable = () -> {
                    this.setName("gameThread");
                    game.init(); };
                new Thread(myrRunnable).start();
            }
        }
    }
}
