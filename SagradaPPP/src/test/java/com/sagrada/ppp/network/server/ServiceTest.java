package com.sagrada.ppp.network.server;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ServiceTest {

    private Service service;
    JoinGameResult joinGameResultTest;
    LobbyObserver lobbyObserver;
    GameObserver gameObserver;
    JoinGameResult joinGameResult2;
    JoinGameResult joinGameResult3;
    JoinGameResult joinGameResult4;
    private static boolean doOnce = true;
    @Before
    public void setUp() throws Exception {
        StaticValues.readConstants();
        if (service == null)
            service = Service.getInstance();
        //test Game

        joinGameResultTest = service.joinGame("test", lobbyObserver, gameObserver);
        joinGameResult2 = service.joinGame("test", lobbyObserver, gameObserver);
        joinGameResult3 = service.joinGame("test", lobbyObserver, gameObserver);
        joinGameResult4 = service.joinGame("test", lobbyObserver, gameObserver);

        assertEquals(joinGameResultTest.getGameHashCode(), joinGameResult2.getGameHashCode());
        assertEquals(joinGameResultTest.getGameHashCode(), joinGameResult3.getGameHashCode());
        assertEquals(joinGameResultTest.getGameHashCode(), joinGameResult4.getGameHashCode());
        assertEquals(joinGameResult2.getGameHashCode(), joinGameResultTest.getGameHashCode());
        assertEquals(joinGameResult2.getGameHashCode(), joinGameResult3.getGameHashCode());
        assertEquals(joinGameResult2.getGameHashCode(), joinGameResult4.getGameHashCode());
        assertEquals(joinGameResult3.getGameHashCode(), joinGameResult2.getGameHashCode());
        assertEquals(joinGameResult3.getGameHashCode(), joinGameResultTest.getGameHashCode());
        assertEquals(joinGameResult3.getGameHashCode(), joinGameResult4.getGameHashCode());
        assertEquals(joinGameResult4.getGameHashCode(), joinGameResultTest.getGameHashCode());
        assertEquals(joinGameResult4.getGameHashCode(), joinGameResult2.getGameHashCode());
        assertEquals(joinGameResult4.getGameHashCode(), joinGameResult3.getGameHashCode());
        doOnce = false;

        lobbyObserver = new LobbyObserver() {
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
        gameObserver = new GameObserver() {
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
    }

    @After
    public void tearDown() throws Exception {
        service.deleteGame(joinGameResultTest.getGameHashCode());

    }


    @Test
    public void leaveLobby() {
        JoinGameResult joinGameResult1 = service.joinGame("test",lobbyObserver,gameObserver);
        service.joinGame("test1",lobbyObserver,gameObserver);
        service.joinGame("test2",lobbyObserver,gameObserver);

        LeaveGameResult leaveGameResult1 = service.leaveLobby(joinGameResult1.getGameHashCode(),joinGameResult1.getUsername(),lobbyObserver,gameObserver);
        assertEquals(joinGameResult1.getGameHashCode(),leaveGameResult1.getGameHashCode());
        assertEquals(LeaveGameResultStatus.SUCCESS,leaveGameResult1.getStatus());



        LeaveGameResult leaveGameResult2 = service.leaveLobby(joinGameResult1.getGameHashCode(),"test1",lobbyObserver,gameObserver);
        assertEquals(joinGameResult1.getGameHashCode(),leaveGameResult2.getGameHashCode());
        assertEquals(LeaveGameResultStatus.SUCCESS,leaveGameResult2.getStatus());

        LeaveGameResult leaveGameResult3 = service.leaveLobby(joinGameResult1.getGameHashCode(),"test2",lobbyObserver,gameObserver);
        assertEquals(joinGameResult1.getGameHashCode(),leaveGameResult3.getGameHashCode());
        assertEquals(LeaveGameResultStatus.GAME_DELETED,leaveGameResult3.getStatus());

    }
    @Test
    public void leaveLobbyException() {
        LeaveGameResult leaveGameResultFail = service.leaveLobby(123,"test1",lobbyObserver,gameObserver);
        assertEquals(123,leaveGameResultFail.getGameHashCode());
        assertEquals(LeaveGameResultStatus.FAIL,leaveGameResultFail.getStatus());
    }



    /**
     * Testing Multiple Game Creation
     */
    @Test
    public void joinGame() {

        JoinGameResult joinGameResult5 = service.joinGame("test",lobbyObserver,gameObserver);
        JoinGameResult joinGameResult6 = service.joinGame("test",lobbyObserver,gameObserver);
        JoinGameResult joinGameResult7 = service.joinGame("test",lobbyObserver,gameObserver);
        JoinGameResult joinGameResult8 = service.joinGame("test",lobbyObserver,gameObserver);

        assertEquals(joinGameResult5.getGameHashCode(),joinGameResult6.getGameHashCode());
        assertEquals(joinGameResult5.getGameHashCode(),joinGameResult7.getGameHashCode());
        assertEquals(joinGameResult5.getGameHashCode(),joinGameResult8.getGameHashCode());
        assertEquals(joinGameResult6.getGameHashCode(),joinGameResult5.getGameHashCode());
        assertEquals(joinGameResult6.getGameHashCode(),joinGameResult7.getGameHashCode());
        assertEquals(joinGameResult6.getGameHashCode(),joinGameResult8.getGameHashCode());
        assertEquals(joinGameResult7.getGameHashCode(),joinGameResult5.getGameHashCode());
        assertEquals(joinGameResult7.getGameHashCode(),joinGameResult6.getGameHashCode());
        assertEquals(joinGameResult7.getGameHashCode(),joinGameResult8.getGameHashCode());
        assertEquals(joinGameResult8.getGameHashCode(),joinGameResult5.getGameHashCode());
        assertEquals(joinGameResult8.getGameHashCode(),joinGameResult6.getGameHashCode());
        assertEquals(joinGameResult8.getGameHashCode(),joinGameResult7.getGameHashCode());


        assertNotEquals(joinGameResultTest.getGameHashCode(),joinGameResult5.getGameHashCode());
    }

    @Test
    public void attachGameObserver() {

        service.attachGameObserver(joinGameResultTest.getGameHashCode(),gameObserver,joinGameResultTest.getPlayerHashCode());
    }
    @Test
    public void attachGameObserverException() throws Exception {
        service.attachGameObserver(1,gameObserver,10);
    }

    @Test
    public void attachLobbyObserver() {
        service.attachLobbyObserver(joinGameResultTest.getGameHashCode(),lobbyObserver,joinGameResultTest.getPlayerHashCode());
    }
    @Test
    public void attachGameLobbyException() {
        service.attachLobbyObserver(1,lobbyObserver,10);
    }

    @Test
    public void detachLobbyObserver() {
        service.detachLobbyObserver(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode());
    }
    @Test
    public void detachLobbyObserverException() {

        service.detachLobbyObserver(123,456);
    }

    @Test
    public void getUsername() {
        assertEquals("test",service.getUsername(joinGameResultTest.getPlayerHashCode(),joinGameResultTest.getGameHashCode()));
    }

    @Test
    public void choosePanel() {
        service.choosePanel(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode(),1);
    }

    @Test
    public void disconnect() {
        assertTrue(service.disconnect(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode()));
        assertFalse(service.disconnect(123,456));
    }

    @Test
    public void placeDice() {
        service.placeDice(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode(),1,1,1);
    }

    @Test
    public void endTurn() {
        service.endTurn(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode());
    }

    @Test
    public void detachAllGameObserver() {
        service.detachAllGameObserver(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode());
    }

    @Test
    public void isToolCardUsable() {
        service.isToolCardUsable(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode(),1);
        assertFalse(service.isToolCardUsable(123,456,1).result);
    }

    @Test
    public void useToolCard() {
        UseToolCardResult useToolCardResult = service.useToolCard(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode(),new ToolCardParameters());
        assertFalse(useToolCardResult.result);
    }

    @Test
    public void specialDicePlacement() {
        service.specialDicePlacement(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode(),1,new Dice());
    }

    @Test
    public void getLegalPositions() {
        service.getLegalPositions(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode(),new Dice());
    }

    @Test
    public void putDiceInDraftPool() {
        service.putDiceInDraftPool(joinGameResultTest.getGameHashCode(),new Dice());
    }


    @Test
    public void reconnection() {

        ReconnectionResult reconnectionResult1 = service.reconnection(joinGameResultTest.getPlayerHashCode(),joinGameResultTest.getGameHashCode(),gameObserver);
        assertNotEquals("Unable to reconnect: game does not exist.", reconnectionResult1.message);
    }

    @Test
    public void reconnectionException() {
        ReconnectionResult reconnectionResult = service.reconnection(12,45,gameObserver);
        assertFalse(reconnectionResult.result);
        assertEquals("Unable to reconnect: game does not exist.", reconnectionResult.message);
    }

    @Test
    public void disableAFK() {
        service.disableAFK(joinGameResultTest.getGameHashCode(),joinGameResultTest.getPlayerHashCode());

    }


}