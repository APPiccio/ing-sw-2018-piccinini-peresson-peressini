package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteController extends Remote {

    ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException;

    LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer) throws RemoteException;

    JoinGameResult joinGame(String username, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException;

    void attachGameObserver(int gameHashCode, GameObserver gameObserver) throws RemoteException;

    String getUsername(int playerHashCode, int gameHashCode) throws RemoteException;

    int getNumPlayers(int gameHashCode) throws RemoteException;

    void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException;

    boolean disconnect(int gameHashCode, int playerHashCode, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException;

    PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col) throws RemoteException;

    void endTurn(int gameHashCode, int playerHashCode) throws RemoteException;
}
