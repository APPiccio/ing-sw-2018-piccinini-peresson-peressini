package com.sagrada.ppp.controller;

import com.sagrada.ppp.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteController extends Remote {

    int login(String username) throws RemoteException;

    int createGame(boolean multiplayer, String name, String username) throws RemoteException;

    ArrayList<String> getJoinableGames() throws RemoteException;
}
