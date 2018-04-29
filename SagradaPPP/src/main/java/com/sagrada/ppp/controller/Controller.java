package com.sagrada.ppp.controller;

import com.sagrada.ppp.Game;
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

    public int createGame(boolean multiplayer, String name, String username) throws RemoteException{
        return service.createGame(multiplayer,name,username);
    }

    public ArrayList<String> getJoinableGames() throws RemoteException{
        return service.getJoinableGames();
    }

    public int login(String username) throws RemoteException {
        return 0;
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

    public boolean joinGame(String gameName, String username) throws RemoteException{
        return service.joinGame(gameName,username);
    }
}
