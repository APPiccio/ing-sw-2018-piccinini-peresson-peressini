package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.server.Service;

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

    public LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer) throws RemoteException{
        return service.leaveLobby(gameHashCode,username,observer,null);
    }


    public JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException{
        return service.joinGame(username, lobbyObserver,null);
    }

    public String getUsername(int playerHashCode, int gameHashCode) throws RemoteException{
        return service.getUsername(playerHashCode, gameHashCode);
    }

    @Override
    public void attachGameObserver(int gameHashCode,GameObserver gameObserver) throws RemoteException {
        service.attachGameObserver(gameHashCode, gameObserver);
    }

    @Override
    public int getNumPlayers(int gameHashCode) throws RemoteException {
        return service.getNumPlayers(gameHashCode);
    }

    @Override
    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException {
        service.choosePanel(gameHashCode, playerHashCode, panelIndex);
    }

    @Override
    public boolean disconnect(int gameHashCode, int playerHashCode, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException {
        return service.disconnect(gameHashCode, playerHashCode, lobbyObserver, gameObserver);
    }

    @Override
    public PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col) throws RemoteException {
        System.out.println("chiamata passa al controller");
        return service.placeDice(gameHashCode, playerHashCode, diceIndex, row, col);
    }

    @Override
    public void endTurn(int gameHashCode, int playerHashCode) throws RemoteException {
        service.endTurn(gameHashCode,playerHashCode);
    }
}