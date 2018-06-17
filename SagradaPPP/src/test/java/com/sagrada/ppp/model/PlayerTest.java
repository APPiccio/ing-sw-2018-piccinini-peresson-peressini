package com.sagrada.ppp.model;

import org.junit.*;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player("test");
    }

    @Test(expected = NullPointerException.class)
    public void Player() {
        new Player(player);
    }

    @Test
    public void getUsername() {
        assertEquals("test", player.getUsername());

        player = new Player("test2");

        assertNotEquals("test", player.getUsername());
        assertEquals("test2", player.getUsername());
    }

    @Test
    public void getPanel() {
    }

    @Test
    public void setPanel() {
    }

    @Test
    public void getFavorTokens() {
    }

    @Test
    public void setFavorTokens() {
    }

    @Test
    public void getPrivateColor() {
    }

    @Test
    public void setPrivateColor() {
    }

    @Test
    public void getHashCode() {
        assertEquals(player.hashCode(), player.getHashCode());
    }

    @Test
    public void hasToSkipSecondTurn() {
    }

    @Test
    public void setSkipSecondTurn() {
    }

    @Test
    public void getPlayerStatus() {
    }

    @Test
    public void setPlayerStatus() {
    }

}