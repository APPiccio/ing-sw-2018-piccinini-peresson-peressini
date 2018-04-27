package com.sagrada.ppp;

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
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    //
    public ArrayList<String> getJoinableGames(){
        ArrayList<String> gamesName = new ArrayList<>();
        for(Game game : games.values()){
            if(game.getGameStatus() == GameStatus.INIT && game instanceof Game){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(game.getName() + " - Players in lobby: {");
                ArrayList<Player> players = game.getPlayers();
                for(int i = 0; i < players.size(); i++){
                    if (i == 0){
                        stringBuilder.append(players.get(i).getUsername());
                    }
                    else {
                        stringBuilder.append(" , " + players.get(i).getUsername());
                    }
                }
                stringBuilder.append("}");
                gamesName.add(stringBuilder.toString());
            }
        }
        return gamesName;
    }


    public int createGame(boolean multiplayer, String name, String username){
        Game game;
        if(multiplayer) {
            game = new Game(name, username);
        }
        else{
            //TO DO space intentionally left for single player implementation

            //line to be removed in case of singleplayer implementation
            game = new Game(name, username);
        }
        games.put(game.hashCode(), game);
        return game.hashCode();
    }

    public boolean joinGame(int gameHashCode, String username){
        Game game = games.get(gameHashCode);
        if(game != null) {
            game.joinGame(username);
        }
        return false;
    }

}
