package com.sagrada.ppp.model;

import java.util.Random;

public enum Color {

    BLUE,
    GREEN,
    PURPLE,
    RED,
    YELLOW;

    /**
     * @return a random Color from the enumeration
     */
    public static Color getRandomColor() {
        return values()[new Random().nextInt(values().length)];
    }

    /**
     * @param colorName String containing the color name
     * @return          Color by the colorName String given
     */
    public static Color getColor(String colorName) {
        switch (colorName) {
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "purple":
                return Color.PURPLE;
            case "red":
                return Color.RED;
            case "yellow":
                return Color.YELLOW;
            default:
                return null;
        }
    }

}