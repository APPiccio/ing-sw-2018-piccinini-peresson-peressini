package com.sagrada.ppp.controller;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.LeaveGameResult;
import com.sagrada.ppp.LobbyObsever;
import com.sagrada.ppp.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteController extends Remote {

    ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException;

    LeaveGameResult leaveLobby(int gameHashCode, String username,LobbyObsever obsever) throws RemoteException;

    JoinGameResult joinGame(String username, LobbyObsever observer) throws RemoteException;

    String getUsername(int playerHashCode, int gameHashCode) throws RemoteException;

    int getNumPlayers(int gameHashCode) throws RemoteException;
}
