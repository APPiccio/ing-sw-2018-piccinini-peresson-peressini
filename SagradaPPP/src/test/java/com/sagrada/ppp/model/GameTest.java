package com.sagrada.ppp.model;

import com.sagrada.ppp.network.server.Service;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class GameTest {

    @Before
    public void setUp() throws Exception {
        StaticValues.readConstants();
        StaticValues.TURN_DURATION = 1000;
        StaticValues.LOBBY_TIMER = 1000;
    }

    @Test
    public void init_test() {
        Service mockService = Service.getInstance();
        Game game = new Game("cicca",null);
        int hash1 = game.joinGame("cicco", null);
        int hash2 = game.joinGame("cicco", null);
        int hash3 = game.joinGame("next", null);
        GameObserver gameObserver = new GameObserver() {
            @Override
            public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException {

            }

            @Override
            public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {

            }

            @Override
            public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {

            }

            @Override
            public void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) throws RemoteException {

            }

            @Override
            public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {

            }

            @Override
            public void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {

            }

            @Override
            public void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException {

            }

            @Override
            public void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException {

            }

            @Override
            public void onPlayerAFK(Player playerAFK, boolean isLastPlayer, Player lastPlayer) throws RemoteException {

            }

            @Override
            public void rmiPing() throws RemoteException {

            }
        };
        game.attachGameObserver(gameObserver, hash2);
        assertFalse(game.isJoinable());
        assertEquals("cicco(1)", game.getPlayerUsername(hash2));

        game = new Game("cicca", mockService);
        hash1 = game.joinGame("cicco", null);
        hash2 = game.joinGame("cicche", null);
        hash3 = game.joinGame("next", null);
        assertFalse(game.isJoinable());
        game.pairPanelToPlayer(2);
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ping_test(){
        //useless test because is on network code
        Game game = new Game("ciao", null);
        LobbyObserver lobbyObserver = new LobbyObserver() {
            @Override
            public void onPlayerJoined(String username, ArrayList<String> Players, int numOfPlayers) throws RemoteException {

            }

            @Override
            public void onPlayerLeave(String username, ArrayList<String> Players, int numOfPlayers) throws RemoteException {

            }

            @Override
            public void onTimerChanges(long timerStart, TimerStatus timerStatus) throws RemoteException {

            }

            @Override
            public void rmiPing() throws RemoteException {

            }
        };
        GameObserver gameObserver = new GameObserver() {
            @Override
            public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException {

            }

            @Override
            public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {

            }

            @Override
            public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {

            }

            @Override
            public void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) throws RemoteException {

            }

            @Override
            public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {

            }

            @Override
            public void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {

            }

            @Override
            public void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException {

            }

            @Override
            public void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException {

            }

            @Override
            public void onPlayerAFK(Player playerAFK, boolean isLastPlayer, Player lastPlayer) throws RemoteException {

            }

            @Override
            public void rmiPing() throws RemoteException {

            }
        };

        game.attachGameObserver(gameObserver, 123);
        game.attachLobbyObserver(lobbyObserver, 123);
        game.pingAllGameObservers();
        game.pingAllLobbyObservers();
        game.detachAllGameObservers(123);
        game.detachLobbyObserver(123);
    }

    @Test
    public void disableAFK_test() {
        Game game = new Game("ciao", null);
        int hash1 = game.joinGame("cicco", null);
        int hash2 = game.joinGame("cicche", null);
        int hash3 = game.joinGame("next", null);
        try {
            sleep(StaticValues.TURN_DURATION * 6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.disableAFK(hash1);
        game.disableAFK(hash3);
        game.disableAFK(hash2);
        assertTrue(game.getActivePlayersNumber() >= 3);
    }

    @Test
    public void leaveLobby_test() {
        StaticValues.LOBBY_TIMER = 20000;
        Game game = new Game("ciao", null);
        int hash1 = game.joinGame("ciaaas" , null);
        game.leaveLobby("ciaaas");
        ArrayList<String> usernames = game.getUsernames();
        assertTrue(!usernames.contains("ciaaas"));
    }

    @Test
    public void getPlayerHashCode_test() {
        StaticValues.LOBBY_TIMER = 20000;
        Game game = new Game("ciao", null);
        int hash1 = game.joinGame("user" , null);
        assertEquals(hash1, game.getPlayerHashCode("user"));
        assertEquals(-1, game.getPlayerHashCode("asdasd"));
    }

    @Test
    public void disconnect_test() {
        Game game = new Game("cicca",null);
        int hash1 = game.joinGame("cicco", null);
        game.disconnect(hash1);
        hash1 = game.joinGame("cicco", null);
        int hash2 = game.joinGame("cicco", null);
        int hash3 = game.joinGame("next", null);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.disconnect(hash1);
        assertTrue(game.getActivePlayersNumber() < 4);
        game.disconnect(hash2);
        assertTrue(game.getActivePlayersNumber() < 3);
        game.disconnect(hash3);
        assertTrue(game.getActivePlayersNumber() < 2);
    }

    @Test
    public void userAction_test() {
        StaticValues.TURN_DURATION = 2000;
        Game game = new Game("ciao", null);
        int hash1 = game.joinGame("cicco", null);
        int hash2 = game.joinGame("cicche", null);
        int hash3 = game.joinGame("next", null);
        int hash4 = game.getPlayerHashCode("ciao");
        try {
            sleep(6 * StaticValues.TURN_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean result = false;
        PlaceDiceResult placeDiceResult;
        for(Player player : game.getPlayers()) {
            for (int row = 0; row < StaticValues.PATTERN_ROW; row++) {
                for (int col = 0; col < StaticValues.PATTERN_COL; col++) {
                    placeDiceResult = game.placeDice(player.getHashCode(), 0, row, col);
                    if (placeDiceResult.status) {
                        result = true;
                        game.setEndTurn(player.getHashCode());
                    }
                }
            }
        }
        assertTrue(result);
    }

    @Test
    public void completeGame_test() {
        StaticValues.TURN_DURATION = 1000;
        StaticValues.LOBBY_TIMER = 5000;
        Game game = new Game("ciao", null);
        ArrayList<Integer> hashcodes = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("ciccio");
        usernames.add("cicche");
        usernames.add("next");
        usernames.add("ciao");

        LobbyObserver lobbyObserver = new LobbyObserver() {
            @Override
            public void onPlayerJoined(String username, ArrayList<String> Players, int numOfPlayers) throws RemoteException {
                assertEquals(1, usernames.stream().filter(x -> x.equals(username)).count());
                assertTrue(numOfPlayers > 0 && numOfPlayers < 5);
            }

            @Override
            public void onPlayerLeave(String username, ArrayList<String> Players, int numOfPlayers) throws RemoteException {
                assertTrue(usernames.contains(username));
                assertTrue(numOfPlayers > 0 && numOfPlayers < 5);
            }

            @Override
            public void onTimerChanges(long timerStart, TimerStatus timerStatus) throws RemoteException {

            }

            @Override
            public void rmiPing() throws RemoteException {

            }
        };


        GameObserver gameObserver = new GameObserver() {
            @Override
            public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException {
                assertTrue(hashcodes.contains(playerHashCode));
                assertEquals(4, panels.size());

            }

            @Override
            public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {
                assertTrue(hashcodes.contains(gameStartMessage.currentPlayer.getHashCode()));
            }

            @Override
            public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
                assertTrue(usernames.contains(dicePlacedMessage.username));
            }

            @Override
            public void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) throws RemoteException {
                assertTrue(hashcodes.contains(toolCardUsedMessage.player.getHashCode()));
            }

            @Override
            public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {
                assertTrue(hashcodes.contains(endTurnMessage.currentPlayer.getHashCode()));
                assertTrue(hashcodes.contains(endTurnMessage.previousPlayer.getHashCode()));
            }

            @Override
            public void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {
                for(PlayerScore score : playersScore){
                    assertTrue(hashcodes.contains(score.getPlayerHashCode()));
                }
            }

            @Override
            public void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException {

            }

            @Override
            public void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException {

            }

            @Override
            public void onPlayerAFK(Player playerAFK, boolean isLastPlayer, Player lastPlayer) throws RemoteException {
                assertTrue(hashcodes.contains(playerAFK.getHashCode()));
                assertTrue(usernames.contains(playerAFK.getUsername()));
            }

            @Override
            public void rmiPing() throws RemoteException {

            }
        };
        int hash4 = game.getPlayerHashCode("ciao");
        game.attachGameObserver(gameObserver, hash4);
        game.attachLobbyObserver(lobbyObserver, hash4);
        game.joinGame("ciccio", null);
        game.leaveLobby("ciccio");
        int hash1 = game.joinGame("ciccio", null);
        int hash2 = game.joinGame("cicche", null);
        int hash3 = game.joinGame("next", null);
        long gameStartTime = System.currentTimeMillis();
        hashcodes.add(hash1);
        hashcodes.add(hash2);
        hashcodes.add(hash3);
        hashcodes.add(hash4);


        try {
            sleep(5 * StaticValues.TURN_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (System.currentTimeMillis() < gameStartTime + 10 * StaticValues.TURN_DURATION * 8 + 1000){
            for(Player player : game.getPlayers()){
                game.disableAFK(player.getHashCode());
            }
            try {
                sleep(StaticValues.TURN_DURATION * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}