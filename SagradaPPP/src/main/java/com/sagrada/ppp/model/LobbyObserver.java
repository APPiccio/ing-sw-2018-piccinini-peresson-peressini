package com.sagrada.ppp.model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Used to notify the view of changes of the model, during the creation of a game.
 * Socket:
 *  Model --> LobbyObserver ...network... LobbyObserver --> View
 * Rmi(Using UnicastRemoteObject):
 *  Model --> ...LobbyObserver(on network)... --> View
 */
public interface LobbyObserver extends Remote {
    void onPlayerJoined(String username, ArrayList<String> Players, int numOfPlayers) throws RemoteException;
    void onPlayerLeave(String username, ArrayList<String> Players,int numOfPlayers) throws RemoteException;
    void onTimerChanges(long timerStart, TimerStatus timerStatus, long duration) throws RemoteException;
    void rmiPing() throws RemoteException;
}
