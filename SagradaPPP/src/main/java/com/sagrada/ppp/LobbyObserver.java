package com.sagrada.ppp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LobbyObsever extends Remote {
    void onPlayerJoined(String username, ArrayList<String> Players, int numOfPlayers) throws RemoteException;
    void onPlayerLeave(String username, ArrayList<String> Players,int numOfPlayers) throws RemoteException;
}
