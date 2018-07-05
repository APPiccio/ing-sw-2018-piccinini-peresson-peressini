package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.cards.toolcards.*;
import com.sagrada.ppp.network.server.Service;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.MAX_PRIORITY;
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
            public void onTimerChanges(long timerStart, TimerStatus timerStatus, long duration) throws RemoteException {

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

        GameObserver gameObserver = new GameObserver() {
            @Override
            public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException {

            }

            @Override
            public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {

            }

            @Override
            public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
                ArrayList<String> usernames = new ArrayList<>();
                usernames.add("cicco");
                usernames.add("ciao");
                usernames.add("cicche");
                usernames.add("next");
                assertTrue(usernames.contains(dicePlacedMessage.username));
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

        game.attachGameObserver(gameObserver, hash1);
        game.attachGameObserver(gameObserver, hash2);
        game.attachGameObserver(gameObserver, hash3);
        game.attachGameObserver(gameObserver, hash4);
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
            public void onTimerChanges(long timerStart, TimerStatus timerStatus, long duration) throws RemoteException {

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
                game.setDicePlaced(true);
                game.setUsedToolCard(true);
            }
            try {
                sleep(StaticValues.TURN_DURATION * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void disconnection_reconnection_test() {
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("ald");
        player1.setPlayerStatus(PlayerStatus.ACTIVE);
        Player player2 = new Player("john");
        player2.setPlayerStatus(PlayerStatus.ACTIVE);
        Player player3 = new Player("jack");
        player3.setPlayerStatus(PlayerStatus.ACTIVE);
        players.add(player1);
        players.add(player2);
        players.add(player3);

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
                assertEquals(player2.getHashCode(), reconnectingPlayer.getHashCode());
            }

            @Override
            public void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException {
                assertTrue(disconnectingPlayer.getHashCode() == player2.getHashCode() || disconnectingPlayer.getHashCode() == player3.getHashCode());
                if(disconnectingPlayer.getHashCode() == player3.getHashCode()){
                    assertTrue(isLastPlayer);
                }
                else {
                    assertFalse(isLastPlayer);
                }
            }

            @Override
            public void onPlayerAFK(Player playerAFK, boolean isLastPlayer, Player lastPlayer) throws RemoteException {

            }

            @Override
            public void rmiPing() throws RemoteException {

            }
        };

        HashMap<Integer, ArrayList<GameObserver>> gameObservers = new HashMap<>();
        ArrayList<GameObserver> go = new ArrayList<>();
        go.add(gameObserver);
        gameObservers.put(player1.getHashCode(), go);

        Game game = new Game(players, gameObservers, null);
        game.disconnect(player2.getHashCode());
        assertEquals(2, game.getActivePlayersNumber());

        game.reconnection(player2.getHashCode(), null);
        assertEquals(3,game.getActivePlayersNumber());

        game.disconnect(player2.getHashCode());
        assertEquals(2, game.getActivePlayersNumber());
        game.disconnect(player3.getHashCode());
    }

    @Test
    public void toolCardGame_test() {
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("dottor_pinciarnalli");
        Player player2 = new Player("dottor_bypartisan");
        player1.setPanel(TestPanels.toolCardPanel());
        player2.setPanel(TestPanels.toolCardPanel());
        players.add(player1);
        players.add(player2);

        HashMap<Integer, ArrayList<GameObserver>> gameObservers = new HashMap<>();

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
                ArrayList<Integer> possibleIDs = new ArrayList<>();
                possibleIDs.add(11);
                possibleIDs.add(9);
                possibleIDs.add(8);
                assertTrue(possibleIDs.contains(toolCardUsedMessage.toolCardID));
                assertEquals(player2.getHashCode(), toolCardUsedMessage.player.getHashCode());
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

        ArrayList<GameObserver> h = new ArrayList<>();
        h.add(gameObserver);
        gameObservers.put(player1.getHashCode(), h);

        ArrayList<ToolCard> toolCards = new ArrayList<>();
        toolCards.add(new ToolCard1());
        toolCards.add(new ToolCard2());
        toolCards.add(new ToolCard3());

        Game game = new Game(players, gameObservers, new ArrayList<>());
        assertFalse(game.isToolCardUsable(player1.getHashCode(), 1));

        game = new Game(players, gameObservers, toolCards);
        assertEquals(1, game.getToolCardID(0));
        assertEquals(2, game.getToolCardID(1));
        assertEquals(3, game.getToolCardID(2));

        assertFalse(game.isToolCardUsable(player1.getHashCode(),4));
        assertFalse(game.isToolCardUsable(1111, 2));
        assertFalse(game.isToolCardUsable(player1.getHashCode(), 1));

        assertTrue(game.isToolCardUsable(player2.getHashCode() , 0));
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 1));
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 2));

        player2.setPanel(TestPanels.createBlankPanel());

        assertFalse(game.isToolCardUsable(player2.getHashCode(), 1));
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 2));

        toolCards.set(0, new ToolCard4());
        toolCards.set(1, new ToolCard5());
        toolCards.set(2, new ToolCard6());

        player2.setPanel(TestPanels.toolCardPanel());
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 0));
        player2.setPanel(TestPanels.createBlankPanel());
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 0));

        assertFalse(game.isToolCardUsable(player2.getHashCode(), 1));
        game.setRound(2);
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 1));
        game.setRound(1);

        assertTrue(game.isToolCardUsable(player2.getHashCode(), 2));
        game.setDicePlaced(true);
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 2));
        game.setDicePlaced(false);

        toolCards.set(0, new ToolCard7());
        toolCards.set(1, new ToolCard8());
        toolCards.set(2, new ToolCard9());

        assertFalse(game.isToolCardUsable(player2.getHashCode(), 0));
        game.setTurn(3);
        game.setDicePlaced(true);
        assertFalse(game.isToolCardUsable(player2.getHashCode() , 0));
        game.setDicePlaced(false);
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 0));
        game.setTurn(2);

        game.setTurn(3);
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 1));
        game.setTurn(2);
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 1));
        game.setDicePlaced(true);
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 1));

        toolCards.set(0, new ToolCard9());
        toolCards.set(1, new ToolCard11());
        toolCards.set(2, new ToolCard12());

        game.setDicePlaced(true);
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 0));
        game.setDicePlaced(false);
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 0));

        game.setDicePlaced(true);
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 1));
        game.setDicePlaced(false);
        assertTrue(game.isToolCardUsable(player2.getHashCode(), 1));

        game.setRound(2);
        assertTrue(game.isToolCardUsable(player2.getHashCode() , 2));
        game.setRound(1);
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 2));

        player2.setFavorTokens(0);
        assertFalse(game.isToolCardUsable(player2.getHashCode(), 2));
        player2.setFavorTokens(player2.getPanel().getFavorTokens());

        UseToolCardResult useToolCardResult;
        useToolCardResult = game.useToolCard(player1.getHashCode(), null);
        assertFalse(useToolCardResult.result);
        ToolCardParameters toolCardParameters = new ToolCardParameters();
        toolCardParameters.toolCardID = 123;

        useToolCardResult = game.useToolCard(player2.getHashCode(),toolCardParameters);
        assertFalse(useToolCardResult.result);

        toolCardParameters.toolCardID = 11;
        DiceBag diceBag = new DiceBag();
        diceBag.extractDices(diceBag.size());
        game.setDiceBag(diceBag);
        useToolCardResult = game.useToolCard(player2.getHashCode(), toolCardParameters);
        assertFalse(useToolCardResult.result);
        game.setDiceBag(new DiceBag());
        ArrayList<Dice> dices = new ArrayList<>();
        dices.add(new Dice());
        game.setDraftPool(dices);
        toolCardParameters.draftPoolDiceIndex = 0;
        useToolCardResult = game.useToolCard(player2.getHashCode(), toolCardParameters);
        assertTrue(useToolCardResult.result);

        toolCards.set(1, new ToolCard8());

        toolCardParameters.toolCardID = 9;
        player2.setPanel(TestPanels.toolCardPanel());
        ArrayList<Dice> draftPool = new ArrayList<>();
        draftPool.add(new Dice(Color.YELLOW, 4));
        draftPool.add(new Dice(Color.YELLOW, 4));
        draftPool.add(new Dice(2));
        game.setDraftPool(draftPool);
        toolCardParameters.draftPoolDiceIndex = 0;
        toolCardParameters.panelCellIndex = 19;
        useToolCardResult = game.useToolCard(player2.getHashCode(), toolCardParameters);
        assertTrue(useToolCardResult.result);

        toolCardParameters.toolCardID = 8;

        player2.setPanel(TestPanels.toolCardPanel());
        draftPool = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            draftPool.add(new Dice(Color.PURPLE, 1));
        }
        toolCardParameters.draftPoolDiceIndex = 5;
        toolCardParameters.panelCellIndex = 1;
        game.setDraftPool(draftPool);
        useToolCardResult = game.useToolCard(player2.getHashCode(), toolCardParameters);
        assertTrue(useToolCardResult.result);

        game.putDiceInDraftPool(new Dice());
        player2.setPanel(TestPanels.createBlankPanel());
        assertEquals(14, game.getLegalPositions(player2.getHashCode(), new Dice()).size());

    }

    @Test
    public void minor_condition() {
        Game game = new Game(null, null);
        assertTrue(game.getPlayers().isEmpty());
        game.reorderPlayers();
        game.setRound(10);
        game.toNextRound();
        assertEquals(GameStatus.SCORE, game.getGameStatus());
    }
}