package com.sagrada.ppp;

import com.sagrada.ppp.network.server.ServerThread;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.controller.Controller;
import com.sagrada.ppp.view.gui.Lobby;

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
            //Socket connection
            ServerThread serverThread = new ServerThread(this);
            serverThread.start();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param multiplayer bool to indicate game mode
     * @param username username of the player
     * @return the hashcode of the game right after the creation
     */
    public int createGame(boolean multiplayer, String username, LobbyObserver lobbyObserver, GameObserver gameObserver){
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
        game.attachLobbyObserver(lobbyObserver);
        game.attachGameObserver(gameObserver);
        return game.hashCode();
    }

    public ArrayList<Player> getPlayers(int gameHashCode){
        return  games.get(gameHashCode).getPlayers();
    }

    public synchronized LeaveGameResult leaveLobby(int gameHashCode, String username,LobbyObserver observer){
        System.out.println(username + " is trying to leave....");
        Game game = games.get(gameHashCode);
        LeaveGameResult leaveGameResult = new LeaveGameResult(gameHashCode,LeaveGameResultStatus.SUCCESS);
        System.out.println(username + " has left.");
        detachLobbyObserver(gameHashCode,observer);
        if(game != null){
            game.leaveLobby(username, observer);
            if(game.getPlayers().isEmpty()){
                leaveGameResult.setStatus(LeaveGameResultStatus.GAME_DELETED);
                games.remove(gameHashCode);
                System.out.println(username + " has left. Game: "+gameHashCode+ " has been deleted.");
            }
        }else {
            leaveGameResult.setStatus(LeaveGameResultStatus.FAIL);
        }

        notifyAll();
        return leaveGameResult;
    }


    /**
     * @param username player username
     * @return game hashcode, player hashcode and player username
     */
    public synchronized JoinGameResult joinGame(String username, LobbyObserver lobbyObserver, GameObserver gameObserver){
        System.out.println(username + "is trying to connect..");
        Game game = null;
        JoinGameResult joinGameResult = new JoinGameResult(-1,-1, null, null);
        for(Game x : games.values()){
            if (x.isJoinable()) {
                    joinGameResult.setPlayerHashCode(x.joinGame(username, lobbyObserver, gameObserver));
                    joinGameResult.setGameHashCode(x.hashCode());
                    joinGameResult.setUsername(games.get(joinGameResult.getGameHashCode()).getPlayerUsername(joinGameResult.getPlayerHashCode()));
                    joinGameResult.setTimerStart(games.get(joinGameResult.getGameHashCode()).getLobbyTimerStartTime());
                    joinGameResult.setPlayersUsername(games.get(joinGameResult.getGameHashCode()).getUsernames());
            }
            if(joinGameResult.getPlayerHashCode() != -1) break;
        }
        if(joinGameResult.getPlayerHashCode() == -1){
            //no joinable game, let's create a new one
            joinGameResult.setGameHashCode(createGame(true, username, lobbyObserver, gameObserver));
            joinGameResult.setPlayerHashCode(games.get(joinGameResult.getGameHashCode()).getPlayerHashCode(username));
            joinGameResult.setUsername(games.get(joinGameResult.getGameHashCode()).getPlayerUsername(joinGameResult.getPlayerHashCode()));
            joinGameResult.setTimerStart(games.get(joinGameResult.getGameHashCode()).getLobbyTimerStartTime());
            joinGameResult.setPlayersUsername(games.get(joinGameResult.getGameHashCode()).getUsernames());
        }
        notifyAll();
        return joinGameResult;
    }
    public void attachGameObserver(int gameHashCode,GameObserver observer){
        Game game = games.get(gameHashCode);
        if (game!= null){
            game.attachGameObserver(observer);
        }
    }

    public void attachLobbyObserver(int gameHashCode, LobbyObserver observer){
        Game game = games.get(gameHashCode);
        if(game != null){
            game.attachLobbyObserver(observer);
        }
    }

    public void detachLobbyObserver(int gameHashCode, LobbyObserver observer){
        Game game = games.get(gameHashCode);
        if(game != null){
            game.detachLobbyObserver(observer);
        }
    }

    public String getUsername(int playerHashCode, int gameHashCode){
        return games.get(gameHashCode).getPlayerUsername(playerHashCode);
    }


    public int getNumPlayers(int gameHashCode){
        Game game = games.get(gameHashCode);
        if(game != null){
            return  game.getPlayers().size();
        }
        return -1;
    }


    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex){
        System.out.println("Recived choice for " + playerHashCode);
        games.get(gameHashCode).pairPanelToPlayer(playerHashCode,panelIndex);
        games.get(gameHashCode).waitingForPanelChoice = false;
    }

    public boolean disconnect(int gameHashCode, int playerHashCode, LobbyObserver lobbyObserver, GameObserver gameObserver){
        return games.get(gameHashCode).disconnect(playerHashCode, lobbyObserver, gameObserver);
    }

    public PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col){
        System.out.println("Chiamata al service");
        return games.get(gameHashCode).placeDice(playerHashCode, diceIndex, row , col);
    }

}
