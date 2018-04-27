package com.sagrada.ppp.controller;

import com.sagrada.ppp.Game;
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

}
