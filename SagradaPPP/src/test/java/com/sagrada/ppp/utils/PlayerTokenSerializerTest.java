package com.sagrada.ppp.utils;

import com.sagrada.ppp.model.JoinGameResult;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class PlayerTokenSerializerTest {

    JoinGameResult joinGameResult;

    @Before
    public void setUp(){
        joinGameResult = new JoinGameResult(123,456,"cadrega", null);
    }


    @Test
    public void isTokenPresent() {
        PlayerTokenSerializer.deleteToken();
        assertFalse(PlayerTokenSerializer.isTokenPresent());
    }

    @Test
    public void serialize() {
        PlayerTokenSerializer.serialize(joinGameResult);
        PlayerTokenSerializer.serialize(joinGameResult);
        assertTrue(PlayerTokenSerializer.isTokenPresent());
    }

    @Test
    public void deserialize() {
        JoinGameResult j2 = PlayerTokenSerializer.deserialize();
        assertEquals(joinGameResult.getPlayerHashCode(), j2.getPlayerHashCode());
        assertEquals(joinGameResult.getGameHashCode(), j2.getGameHashCode());
        assertEquals(joinGameResult.getUsername(), j2.getUsername());
    }

}