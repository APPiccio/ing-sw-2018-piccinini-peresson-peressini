package com.sagrada.ppp.controller;

import com.sagrada.ppp.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteController extends Remote {

    void createGame(boolean multiplayer) throws RemoteException;
    int login(String username) throws RemoteException;
    String getUsername(int hashCode) throws RemoteException;
    ArrayList<Player> getPlayers() throws RemoteException;

}
