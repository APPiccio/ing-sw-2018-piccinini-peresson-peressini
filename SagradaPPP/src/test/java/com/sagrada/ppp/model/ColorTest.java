package com.sagrada.ppp.model;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ColorTest {

    private HashMap<String, Color> colors;

    public ColorTest() {
        colors = new HashMap<>();
        colors.put("blue", Color.BLUE);
        colors.put("green", Color.GREEN);
        colors.put("purple", Color.PURPLE);
        colors.put("red", Color.RED);
        colors.put("yellow", Color.YELLOW);
        colors.put("blellow", null);
    }

    @Test
    public void testAll() {
        getRandomColor();
        getColor();
    }

    @Test
    public void getRandomColor() {
        Color color = Color.getRandomColor();
        assertTrue(colors.values().contains(color));
    }

    @Test
    public void getColor() {
       for (String color : colors.keySet()) {
           assertEquals(colors.get(color), Color.getColor(color));
       }
    }

}