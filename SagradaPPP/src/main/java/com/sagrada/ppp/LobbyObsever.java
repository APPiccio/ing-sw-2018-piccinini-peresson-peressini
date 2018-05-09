package com.sagrada.ppp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LobbyObsever extends Remote {
    void onPlayerJoined(String username, int numOfPlayers) throws RemoteException;
}
