package com.sagrada.ppp.controller;

import com.sagrada.ppp.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Controller extends UnicastRemoteObject implements RemoteController {

    private Service service;

    public Controller(Service service) throws RemoteException {
        this.service = service;
    }

    public ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException{
        return service.getPlayers(gameHashCode);
    }

    public LeaveGameResult leaveLobby(int gameHashCode, String username,LobbyObserver observer) throws RemoteException{
        return service.leaveLobby(gameHashCode,username,observer);
    }


    public JoinGameResult joinGame(String username, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException{
        return service.joinGame(username, lobbyObserver, gameObserver);
    }

    public String getUsername(int playerHashCode, int gameHashCode) throws RemoteException{
        return service.getUsername(playerHashCode, gameHashCode);
    }

    @Override
    public int getNumPlayers(int gameHashCode) throws RemoteException {
        return service.getNumPlayers(gameHashCode);
    }

    @Override
    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException {
        service.choosePanel(gameHashCode, playerHashCode, panelIndex);
    }
}
