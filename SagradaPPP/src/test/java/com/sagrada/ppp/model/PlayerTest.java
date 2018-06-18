package com.sagrada.ppp.model;

import org.junit.*;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player("test");
    }

    @Test
    public void PlayerException() {
        new Player(player);
    }

    @Test
    public void Player() {
        assertEquals("test", player.getUsername());
        assertNull(player.getPanel());
        assertEquals(0, player.getFavorTokens());
        assertNull(player.getPrivateColor());
        assertEquals(player.hashCode(), player.getHashCode());
        assertFalse(player.hasToSkipSecondTurn());
        assertEquals(PlayerStatus.ACTIVE, player.getPlayerStatus());

        player.setPanel(new WindowPanel(1, 1));

        assertEquals("Kaleidoscopic Dream", player.getPanel().getPanelName());
        assertEquals(1, player.getPanel().getCardID());
        assertEquals(player.getFavorTokens(), player.getPanel().getFavorTokens());

        player.setFavorTokens(0);
        assertEquals(0, player.getFavorTokens());
        assertNotEquals(player.getFavorTokens(), player.getPanel().getFavorTokens());

        player.setPrivateColor(Color.GREEN);
        assertEquals(Color.GREEN, player.getPrivateColor());

        player.setSkipSecondTurn(true);
        assertTrue(player.hasToSkipSecondTurn());

        player.setPlayerStatus(PlayerStatus.INACTIVE);
        assertEquals(PlayerStatus.INACTIVE, player.getPlayerStatus());

        Player tempPlayer = new Player(player);

        assertNotEquals(tempPlayer.hashCode(), player.hashCode());
        assertEquals(tempPlayer.getHashCode(), player.getHashCode());

        assertEquals(player.hashCode(), player.getHashCode());
        assertNotEquals(tempPlayer.hashCode(), tempPlayer.getHashCode());
    }

}