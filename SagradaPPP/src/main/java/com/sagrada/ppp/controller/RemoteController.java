package com.sagrada.ppp.controller;

import com.sagrada.ppp.Observer;
import com.sagrada.ppp.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteController extends Remote {

    ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException;

    void leaveLobby(int gameHashCode, String username) throws RemoteException;

    void attachLobbyObserver(int gameHashCode, Observer observer) throws RemoteException;

    int joinGame(String username) throws RemoteException;

    String getUsername(int playerHashCode, int gameHashCode) throws RemoteException;


}
