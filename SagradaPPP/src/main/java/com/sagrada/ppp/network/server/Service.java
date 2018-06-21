package com.sagrada.ppp.network.server;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.controller.Controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Singleton class
 * The main purpose of this class is to relay the correct calls from the controller to the correct game,
 * and in general manage multiple instance of Game!
 */
public class Service {

    private HashMap<Integer , Game> games;
    private static Service instance;
    private static final String NO_GAME_WITH_THIS_HASHCODE = "No game with this HashCode: ";


    /**
     * @return returns always the same instance of service
     */
    public static Service getInstance(){
        if(instance != null){
            return instance;
        }else {
            instance = new Service();
            return instance;
        }
    }

    /**
     * Construct an instance of Service, and creating the RMI registry
     */
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
    private Game createGame(String username, LobbyObserver lobbyObserver, int playerHashCode){
        Game game = new Game(username,this);
        games.put(game.hashCode(), game);
        game.attachLobbyObserver(lobbyObserver, playerHashCode);
        return game;
    }

    /**
     * Disconnects a player during lobby phase of the game!
     * @param gameHashCode identifies a game
     * @param username identifies a player in a game
     * @param observer LobbyObserver to detach, related to the client instance that wants to disconnect during lobby
     * @return returns a LeaveGameResult, a container that includes all the information regarding the player leaving the lobby, such as, SUCCESS in case of successful disconnection, GAME_DELETED in case of the last player leaving Lobby
     *          FAIL in case of non existing game.
     */
    public synchronized LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer){
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
                joinGameResult.setUsername(x.getPlayerUsername(joinGameResult.getPlayerHashCode()));
                joinGameResult.setTimerStart(x.getLobbyTimerStartTime());
                joinGameResult.setPlayersUsername(x.getUsernames());
            }
            if(joinGameResult.getPlayerHashCode() != -1) break;
        }
        if(joinGameResult.getPlayerHashCode() == -1){
            //no joinable game, let's create a new one
            Game game = createGame(username, lobbyObserver, joinGameResult.getPlayerHashCode());
            joinGameResult.setGameHashCode(game.hashCode());
            game.attachGameObserver(gameObserver, joinGameResult.getPlayerHashCode());
            joinGameResult.setPlayerHashCode(game.getPlayerHashCode(username));
            joinGameResult.setUsername(game.getPlayerUsername(joinGameResult.getPlayerHashCode()));
            joinGameResult.setTimerStart(game.getLobbyTimerStartTime());
            joinGameResult.setPlayersUsername(game.getUsernames());
        }
        notifyAll();
        return joinGameResult;
    }

    /**
     * @param gameHashCode game witch observer will observe
     * @param observer a game observer
     * @param playerHashCode indicates who is the client who wants to attach this observer
     */
    public void attachGameObserver(int gameHashCode,GameObserver observer, int playerHashCode) {
        Game game = getGame(gameHashCode);
        if (game != null) {
            game.attachGameObserver(observer, playerHashCode);
        }
    }
    /**
     * @param gameHashCode game witch observer will observe during lobby phase
     * @param observer a game observer
     * @param playerHashCode indicates who is the client who wants to attach this observer
     */
    void attachLobbyObserver(int gameHashCode, LobbyObserver observer, int playerHashCode){
        Game game = getGame(gameHashCode);
        if(game != null){
            game.attachLobbyObserver(observer, playerHashCode);
        }
    }

    /**
     * @param gameHashCode game to modify
     * @param playerHashCode indicates who is the client who wants to detach his observer
     */
    void detachLobbyObserver(int gameHashCode, int playerHashCode){
        Game game = getGame(gameHashCode);
        if(game != null){
            game.detachLobbyObserver(playerHashCode);
        }
    }

    /**
     * @param playerHashCode identifies a player
     * @param gameHashCode identifies a game
     * @return String containing the username relative to the playerHashCode's
     */
    public String getUsername(int playerHashCode, int gameHashCode){
        Game game = getGame(gameHashCode);
        if (game == null) return null;
        return game.getPlayerUsername(playerHashCode);
    }


    /**
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     * @param panelIndex identifies the choice of the panel, made by the player during "panel choice" phase
     */
    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) {
        System.out.println("Received choice for " + playerHashCode);
        Game game = getGame(gameHashCode);
        if (game == null) return;
        game.pairPanelToPlayer(playerHashCode, panelIndex);
        game.stopWaitingForPanelChoice();
    }

    /**
     * Allows a client to disconnect
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     * @return returns if the disconnection has had a positive or negative result
     */
    public boolean disconnect(int gameHashCode, int playerHashCode) {
        Game game = getGame(gameHashCode);
        if(game != null)
            return game.disconnect(playerHashCode);
        return false;
    }

    /**
     * Allows a player to place a dice during the Game phase
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     * @param diceIndex identifies a dice on the games's draft pool
     * @param row identifies a row of the player's panel
     * @param col identifies a column of the player's panel
     * @return returns all the information about this placement, and its result
     */
    public PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col){
        Game game = getGame(gameHashCode);
        if (game == null) return new PlaceDiceResult(NO_GAME_WITH_THIS_HASHCODE + gameHashCode,false,null,null);
        return game.placeDice(playerHashCode, diceIndex, row , col);
    }

    /**
     * Allows a player to skip his turn
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     */
    public void endTurn(int gameHashCode, int playerHashCode){
        Game game = getGame(gameHashCode);
        if (game == null) return;
        game.setEndTurn(playerHashCode);
    }
    /**
     * Allows a player to disconnect all the observers relative to its client
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     */
    public void detachAllGameObserver(int gameHashCode, int playerHashCode){
        Game game = getGame(gameHashCode);
        if (game == null) return;
        game.detachAllGameObservers(playerHashCode);
    }


    /**
     * Allows a client to know whether or not it's able to use a tool card.
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     * @param toolCardIndex identifies a tool card from the 3 present in the game
     * @return returns the tool card id(from 1 to 12) and the result of the operation inside a container class
     */
    public IsToolCardUsableResult isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex){
        Game game = getGame(gameHashCode);
        if(game != null){
            return new IsToolCardUsableResult(game.isToolCardUsable(playerHashCode, toolCardIndex), game.getToolCardID(toolCardIndex));
        }
        return new IsToolCardUsableResult(false, -1);
    }

    /**
     * Relays the information about the usage of a tool card to the game
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     * @param toolCardParameters parameter that the Game instance will be using to perform the required action
     * @return returns the result given by the game about the tool card usage, or a negative result if the game doesn't exist.
     */
    public UseToolCardResult useToolCard(int gameHashCode, int playerHashCode, ToolCardParameters toolCardParameters){
        Game game = getGame(gameHashCode);
        if (game == null) return new UseToolCardResult(false,0,0,null,
                null,null,null,NO_GAME_WITH_THIS_HASHCODE + gameHashCode);
        return game.useToolCard(playerHashCode, toolCardParameters);
    }

    /**
     * Same as dicePlacement() but only used by tool cards.
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     * @param cellIndex identifies a cell on the player's panel
     * @param dice identifies the dice that the player wants to place
     * @return returns the result given by the game or a negative result if the game doesn't exist.
     */
    public PlaceDiceResult specialDicePlacement(int gameHashCode, int playerHashCode, int cellIndex, Dice dice){
        Game game = getGame(gameHashCode);
        if (game == null) return new PlaceDiceResult(NO_GAME_WITH_THIS_HASHCODE + gameHashCode,false,null,null);
        return game.specialDicePlacement(playerHashCode, cellIndex, dice);
    }

    /**
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     * @param dice identifies the dice that the player wants to place
     * @return all legal position for that dice on the player's panel
     */
    public ArrayList<Integer> getLegalPositions(int gameHashCode, int playerHashCode, Dice dice){
        Game game = getGame(gameHashCode);
        if (game == null) return new ArrayList<>();
        return game.getLegalPositions(playerHashCode, dice);
    }

    /**
     * Self Explanatory
     * @param gameHashCode identifies a game
     * @param dice identifies the dice that the client wants to put inside the draft pool
     */
    public void putDiceInDraftPool(int gameHashCode, Dice dice){

        Game game = getGame(gameHashCode);
        if (game == null) return;
        game.putDiceInDraftPool(dice);
    }

    /**
     * Allows a client to reconnect to an existing game, from which it previously disconnected.
     * @param playerHashCode identifies a player
     * @param gameHashCode identifies a game
     * @param gameObserver identifies the client's observer that will observe the game
     * @return return the result given by the game or a negative result if the game doesn't exist.
     */
    public ReconnectionResult reconnection(int playerHashCode, int gameHashCode, GameObserver gameObserver) {
        Game game = getGame(gameHashCode);
        if (game == null) return new ReconnectionResult(false,
                "Unable to reconnect: game does not exist.", null);
        return game.reconnection(playerHashCode, gameObserver);
    }

    /**
     * Re enables a player went afk
     * @param gameHashCode identifies a game
     * @param playerHashCode identifies a player
     */
    public void disableAFK(int gameHashCode, int playerHashCode){

        Game game = getGame(gameHashCode);
        if (game == null) return ;
        game.disableAFK(playerHashCode);
    }

    /**
     * @param gameHashCode identifies a game
     * @return returns the instance of a previously created game whit the given hash code
     */
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

    /**
     * Deletes a game with a given hash code
     * @param gameHashCode identifies a game
     */
    public void deleteGame(int gameHashCode){
        System.out.println("CLOSING GAME --> " + gameHashCode);
        games.remove(gameHashCode);
    }

}