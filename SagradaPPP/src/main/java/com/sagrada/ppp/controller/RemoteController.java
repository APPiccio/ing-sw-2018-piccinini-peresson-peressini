package com.sagrada.ppp.controller;

import com.sagrada.ppp.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteController extends Remote {

    ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException;

    LeaveGameResult leaveLobby(int gameHashCode, String username,LobbyObserver observer) throws RemoteException;

    JoinGameResult joinGame(String username, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException;

    String getUsername(int playerHashCode, int gameHashCode) throws RemoteException;

    int getNumPlayers(int gameHashCode) throws RemoteException;

    void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException;
}
