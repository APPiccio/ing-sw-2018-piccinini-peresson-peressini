package com.sagrada.ppp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LobbyObserver extends Remote {
    void onPlayerJoined(String username, int numOfPlayers) throws RemoteException;

    void onTimerChanges(long timerStart, TimerStatus timerStatus) throws RemoteException;
}
