package com.sagrada.ppp.network.server;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.controller.Controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;


public class Service {

    private HashMap<Integer , Game> games;
    private static Service instance;


    public static Service getInstance(){
        if(instance != null){
            return instance;
        }else {
            instance = new Service();
            return instance;
        }
    }

    private Service(){
        games = new HashMap<>();
        try {

            Controller controller = new Controller(this);
            Registry registry = LocateRegistry.createRegistry(StaticValues.RMI_PORT);
            registry.rebind(StaticValues.REGISTRY_NAME, controller);
            System.out.println("--> controller exported");
            //Socket connection
            ServerThread serverThread = new ServerThread(this);
            serverThread.setName("ServerThread");
            serverThread.start();


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param username username of the player
     * @return the hashcode of the game right after the creation
     */
    private int createGame(String username, LobbyObserver lobbyObserver, int playerHashCode){
        Game game = new Game(username,this);
        games.put(game.hashCode(), game);
        game.attachLobbyObserver(lobbyObserver, playerHashCode);
        return game.hashCode();
    }

    public synchronized LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer, GameObserver gameObserver){
        System.out.println(username + " is trying to leave....");
        LeaveGameResult leaveGameResult = new LeaveGameResult(gameHashCode,LeaveGameResultStatus.SUCCESS);
        Game game = getGame(gameHashCode);
        if(game != null){
            int playerHashCode = game.getPlayerHashCode(username);
            System.out.println(username + " has left.");
            detachLobbyObserver(gameHashCode,playerHashCode);
            game.detachAllGameObservers(playerHashCode);
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
        JoinGameResult joinGameResult = new JoinGameResult(-1,-1, null, null);
        for(Game x : games.values()){
            if (x.isJoinable()) {
                joinGameResult.setPlayerHashCode(x.joinGame(username, lobbyObserver));
                x.attachGameObserver(gameObserver, joinGameResult.getPlayerHashCode());
                joinGameResult.setGameHashCode(x.hashCode());
                joinGameResult.setUsername(getGame(joinGameResult.getGameHashCode()).getPlayerUsername(joinGameResult.getPlayerHashCode()));
                joinGameResult.setTimerStart(getGame(joinGameResult.getGameHashCode()).getLobbyTimerStartTime());
                joinGameResult.setPlayersUsername(getGame(joinGameResult.getGameHashCode()).getUsernames());
            }
            if(joinGameResult.getPlayerHashCode() != -1) break;
        }
        if(joinGameResult.getPlayerHashCode() == -1){
            //no joinable game, let's create a new one
            joinGameResult.setGameHashCode(createGame(username, lobbyObserver, joinGameResult.getPlayerHashCode()));
            getGame(joinGameResult.getGameHashCode()).attachGameObserver(gameObserver, joinGameResult.getPlayerHashCode());
            joinGameResult.setPlayerHashCode(getGame(joinGameResult.getGameHashCode()).getPlayerHashCode(username));
            joinGameResult.setUsername(getGame(joinGameResult.getGameHashCode()).getPlayerUsername(joinGameResult.getPlayerHashCode()));
            joinGameResult.setTimerStart(getGame(joinGameResult.getGameHashCode()).getLobbyTimerStartTime());
            joinGameResult.setPlayersUsername(getGame(joinGameResult.getGameHashCode()).getUsernames());
        }
        notifyAll();
        return joinGameResult;
    }
    public void attachGameObserver(int gameHashCode,GameObserver observer, int playerHashCode) {
        Game game = getGame(gameHashCode);
        if (game != null) {
            game.attachGameObserver(observer, playerHashCode);
        }
    }

    public void attachLobbyObserver(int gameHashCode, LobbyObserver observer, int playerHashCode){
        Game game = getGame(gameHashCode);
        if(game != null){
            game.attachLobbyObserver(observer, playerHashCode);
        }
    }

    public void detachLobbyObserver(int gameHashCode, int playerHashCode){
        Game game = getGame(gameHashCode);
        if(game != null){
            game.detachLobbyObserver(playerHashCode);
        }
    }

    public String getUsername(int playerHashCode, int gameHashCode){
        return getGame(gameHashCode).getPlayerUsername(playerHashCode);
    }


    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) {
        System.out.println("Received choice for " + playerHashCode);
        getGame(gameHashCode).pairPanelToPlayer(playerHashCode, panelIndex);
        getGame(gameHashCode).stopWaitingForPanelChoice();
    }

    public boolean disconnect(int gameHashCode, int playerHashCode) {
        Game game = getGame(gameHashCode);
        if(game != null)
            return game.disconnect(playerHashCode);
        return false;
    }

    public PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col){
        return getGame(gameHashCode).placeDice(playerHashCode, diceIndex, row , col);
    }

    public void endTurn(int gameHashCode, int playerHashCode){
        getGame(gameHashCode).setEndTurn(playerHashCode);
    }

    public void detachAllGameObserver(int gameHashCode, int playerHashCode){
        getGame(gameHashCode).detachAllGameObservers(playerHashCode);
    }

    public IsToolCardUsableResult isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex){
        Game game = getGame(gameHashCode);
        if(game != null){
            return new IsToolCardUsableResult(game.isToolCardUsable(playerHashCode, toolCardIndex), game.getToolCardID(toolCardIndex));
        }
        return new IsToolCardUsableResult(false, -1);
    }

    public UseToolCardResult useToolCard(int gameHashCode, int playerHashCode, ToolCardParameters toolCardParameters){
        return getGame(gameHashCode).useToolCard(playerHashCode, toolCardParameters);
    }

    public PlaceDiceResult specialDicePlacement(int gameHashCode, int playerHashCode, int cellIndex, Dice dice){
        return getGame(gameHashCode).specialDicePlacement(playerHashCode, cellIndex, dice);
    }

    public ArrayList<Integer> getLegalPositions(int gameHashCode, int playerHashCode, Dice dice){
        return getGame(gameHashCode).getLegalPositions(playerHashCode, dice);
    }

    public void putDiceInDraftPool(int gameHashCode, Dice dice){
        getGame(gameHashCode).putDiceInDraftPool(dice);
    }

    public ReconnectionResult reconnection(int playerHashCode, int gameHashCode, GameObserver gameObserver) {
        Game game = getGame(gameHashCode);
        if (game == null) return new ReconnectionResult(false,
                "Unable to reconnect: game does not exist.", null);
        return getGame(gameHashCode).reconnection(playerHashCode, gameObserver);
    }

    public void disableAFK(int gameHashCode, int playerHashCode){
        getGame(gameHashCode).disableAFK(playerHashCode);
    }
    private Game getGame(int gameHashCode){
        Game game = games.get(gameHashCode);
        if (game!= null) return game;
        else {
            try {
                throw new IllegalArgumentException("Invalid Game Hash Code");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void deleteGame(int gameHashCode){
        System.out.println("CLOSING GAME --> " + gameHashCode);
        games.remove(gameHashCode);
    }

}