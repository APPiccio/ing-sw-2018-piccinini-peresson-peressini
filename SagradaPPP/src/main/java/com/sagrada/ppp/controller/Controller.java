package com.sagrada.ppp.controller;

import com.sagrada.ppp.Observer;
import com.sagrada.ppp.Player;
import com.sagrada.ppp.Service;

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

    public void leaveLobby(int gameHashCode, String username) throws RemoteException{
        service.leaveLobby(gameHashCode,username);
    }

    public void attachLobbyObserver(int gameHashCode, Observer observer) throws  RemoteException{
        service.attachLobbyObserver(gameHashCode, observer);
    }

    public int joinGame(String username) throws RemoteException{
        return service.joinGame(username);
    }

    public String getUsername(int playerHashCode, int gameHashCode) throws RemoteException{
        return service.getUsername(playerHashCode, gameHashCode);
    }
}
