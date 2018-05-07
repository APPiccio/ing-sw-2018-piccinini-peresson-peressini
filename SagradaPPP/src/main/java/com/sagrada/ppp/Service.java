package com.sagrada.ppp;

import com.sagrada.ppp.network.server.ServerThread;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.controller.Controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;


public class Service {

    HashMap<Integer , Game> games;

    public Service(){
        games = new HashMap<>();
        try {
            Controller controller = new Controller(this);
            Registry registry = LocateRegistry.createRegistry(StaticValues.RMI_PORT);
            registry.rebind(StaticValues.REGISTRY_NAME, controller);
            System.out.println("--> controller exported");
            ServerThread serverThread = new ServerThread(this);
            serverThread.start();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int createGame(boolean multiplayer, String username){
        Game game;
        if(multiplayer) {
            game = new Game(username);
        }
        else{
            //TO DO space intentionally left for single player implementation

            //line to be removed in case of singleplayer implementation
            game = new Game(username);
        }
        games.put(game.hashCode(), game);
        return game.hashCode();
    }

    public ArrayList<Player> getPlayers(int gameHashCode){
        return  games.get(gameHashCode).getPlayers();
    }

    public void leaveLobby(int gameHashCode, String username){
        Game game = games.get(gameHashCode);
        if(game != null){
            game.leaveLobby(username);
            if(game.getPlayers().size() == 0){
                games.remove(gameHashCode);
            }
        }
    }


    public int joinGame(String username){
        Game game = null;
        int playerHashCode = -1;
        for(Game x : games.values()){
                if (x.isJoinable()) {
                    playerHashCode = x.joinGame(username);

                }
                notifyAll();
            if(playerHashCode != -1) break;
        }
        if(playerHashCode == -1){
            //no joinable game, let's create a new one
            int gameHashCode = this.createGame(true, username);
            playerHashCode = games.get(gameHashCode).getPlayerHashCode(username);
        }
        return playerHashCode;
    }

    public void attachLobbyObserver(int gameHashCode, Observer observer){
        Game game = games.get(gameHashCode);
        if(game != null){
            game.attach(observer);
        }
    }

    public void detachLobbyObserver(int gameHashCode, Observer observer){
        Game game = games.get(gameHashCode);
        if(game != null){
            game.detach(observer);
        }
    }

    public String getUsername(int playerHashCode, int gameHashCode){
        return games.get(gameHashCode).getPlayerUsername(playerHashCode);
    }

}
