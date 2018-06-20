package com.sagrada.ppp.model;

import com.sagrada.ppp.network.server.Service;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.CliView;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
        try {
            CliView observer = new CliView(null, null);
            game.attachGameObserver(observer, hash2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        assertFalse(game.isJoinable());
        assertEquals("cicco(1)", game.getPlayerUsername(hash2));

        game = new Game("cicca", mockService);
        hash1 = game.joinGame("cicco", null);
        hash2 = game.joinGame("cicche", null);
        hash3 = game.joinGame("next", null);
        assertFalse(game.isJoinable());
        game.pairPanelToPlayer(hash1, 2);
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
        CliView cliView = null;
        try {
            cliView = new CliView(null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        game.attachGameObserver(cliView, 123);
        game.attachLobbyObserver(cliView, 123);
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
        CliView cliView1 = null;
        CliView cliView2 = null;
        CliView cliView3 = null;
        try{
            cliView1 = new CliView(null, null);
            cliView2 = new CliView(null, null);
            cliView3 = new CliView(null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


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
        game.leaveLobby("ciaaas", null);
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
    public void placeDice_test() {
        StaticValues.TURN_DURATION = 5000;
        Game game = new Game("ciao", null);
        int hash1 = game.joinGame("cicco", null);
        int hash2 = game.joinGame("cicche", null);
        int hash3 = game.joinGame("next", null);
        int hash4 = game.getPlayerHashCode("ciao");
        try {
            sleep(5 * StaticValues.TURN_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean result = false;
        PlaceDiceResult placeDiceResult;
        for(Player player : game.getPlayers()) {
            for (int row = 0; row < StaticValues.PATTERN_ROW; row++) {
                for (int col = 0; col < StaticValues.PATTERN_COL; col++) {
                    placeDiceResult = game.placeDice(player.getHashCode(), 0, row, col);
                    if (placeDiceResult.status) result = true;
                }
            }
        }
        assertTrue(result);
    }
}