package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.CliView;
import org.junit.*;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class GameTestNOBUONO {
    private Game game;
    boolean isInit;

    @Before
    public void init(){
        isInit = true;
        StaticValues.TURN_DURATION = 3000;
        game = new Game("user1", null);
        game.joinGame("user2", null);
        game.joinGame("user3" , null);
        game.joinGame("user1" , null);
        assertFalse(game.isJoinable());
    }

/*
    @Test
    public void playersTest() {
        ArrayList<Player> players = game.getPlayers();
        assertEquals(players.stream().filter(x -> x.getPlayerStatus().equals(PlayerStatus.ACTIVE)).count(), game.getActivePlayersNumber());
        for (Player player : players){
            assertEquals(player.getUsername(), game.getPlayerUsername(player.getHashCode()));
            assertEquals(player.getHashCode(), game.getPlayerHashCode(player.getUsername()));
        }
    }

*/


    @Before
    public void setUp() throws Exception {
        if(!isInit){
            init();
        }
    }

    @Test
    public void getLobbyTimerStartTime() {
    }

    @Test
    public void joinGame() {
    }

    @Test
    public void isJoinable() {
    }

    @Test
    public void getPlayerUsername() {
    }

    @Test
    public void getPlayers() {
    }

    @Test
    public void getActivePlayersNumber() {
    }

    @Test
    public void getUsernames() {
    }

    @Test
    public void leaveLobby() {
    }

    @Test
    public void getPlayerHashCode() {
    }

    @Test
    public void attachGameObserver() {
    }

    @Test
    public void detachGameObserver() {
    }

    @Test
    public void detachAllGameObservers() {
    }

    @Test
    public void attachLobbyObserver() {
    }

    @Test
    public void detachLobbyObserver() {
    }

    @Test
    public void notifyTimerChanges() {
    }

    @Test
    public void extractPanels() {
    }

    @Test
    public void getPrivateObjectiveColors() {
    }

    @Test
    public void pairPanelToPlayer() {
    }

    @Test
    public void disconnect() {
    }

    @Test
    public void setTurn() {
    }

    @Test
    public void getCurrentPlayerIndex() {
    }

    @Test
    public void placeDice() {
    }

    @Test
    public void setEndTurn() {
    }

    @Test
    public void isToolCardUsable() {
    }

    @Test
    public void getToolCardID() {
    }

    @Test
    public void useToolCard() {
    }

    @Test
    public void specialDicePlacement() {
    }

    @Test
    public void getLegalPositions() {
    }

    @Test
    public void putDiceInDraftPool() {
    }

    @Test
    public void reconnection() {
    }

    @Test
    public void pingAllLobbyObservers() {
    }

    @Test
    public void stopWaitingForPanelChoice() {
    }
}
