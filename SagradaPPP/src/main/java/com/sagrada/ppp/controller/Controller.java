package com.sagrada.ppp.controller;

import com.sagrada.ppp.Client;
import com.sagrada.ppp.Game;
import com.sagrada.ppp.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Controller extends UnicastRemoteObject implements RemoteController {

    private Game game;

    public Controller() throws RemoteException {
        super();
    }

    public void createGame(boolean multiplayer){
        if(multiplayer){
            game = new Game();
        }
    }

    public int login(String username){
        return game.joinGame(username);
    }

    public String getUsername(int hashCode){
        return game.getPlayerUsername(hashCode);
    }

    public ArrayList<Player> getPlayers() {
        return game.getPlayers();
    }
}
