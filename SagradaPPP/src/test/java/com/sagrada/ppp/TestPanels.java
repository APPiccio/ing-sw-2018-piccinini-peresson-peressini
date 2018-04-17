package com.sagrada.ppp;

import java.io.FileNotFoundException;

public class TestPanels {

    public static WindowPanel panel50() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(1, new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.PURPLE, 1));
        panel.addDiceOnCellWithIndex(3, new Dice(Color.RED, 2));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.YELLOW, 1));

        panel.addDiceOnCellWithIndex(5, new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(6, new Dice(Color.PURPLE, 1));
        panel.addDiceOnCellWithIndex(7, new Dice(Color.RED, 2));
        panel.addDiceOnCellWithIndex(8, new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(9, new Dice(Color.BLUE, 2));

        panel.addDiceOnCellWithIndex(10, new Dice(Color.PURPLE, 1));
        panel.addDiceOnCellWithIndex(11, new Dice(Color.RED, 2));
        panel.addDiceOnCellWithIndex(12, new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(13, new Dice(Color.BLUE, 2));
        panel.addDiceOnCellWithIndex(14, new Dice(Color.GREEN, 1));

        panel.addDiceOnCellWithIndex(15, new Dice(Color.RED, 2));
        panel.addDiceOnCellWithIndex(16, new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(17, new Dice(Color.BLUE, 2));
        panel.addDiceOnCellWithIndex(18, new Dice(Color.GREEN, 1));
        panel.addDiceOnCellWithIndex(19, new Dice(Color.PURPLE, 2));

        return panel;

    }

    public static WindowPanel panel60() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 3));
        panel.addDiceOnCellWithIndex(1, new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.PURPLE, 3));
        panel.addDiceOnCellWithIndex(3, new Dice(Color.RED, 4));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.YELLOW, 3));

        panel.addDiceOnCellWithIndex(5, new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(6, new Dice(Color.PURPLE, 3));
        panel.addDiceOnCellWithIndex(7, new Dice(Color.RED, 4));
        panel.addDiceOnCellWithIndex(8, new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(9, new Dice(Color.BLUE, 4));

        panel.addDiceOnCellWithIndex(10, new Dice(Color.PURPLE, 3));
        panel.addDiceOnCellWithIndex(11, new Dice(Color.RED, 4));
        panel.addDiceOnCellWithIndex(12, new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(13, new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(14, new Dice(Color.GREEN, 3));

        panel.addDiceOnCellWithIndex(15, new Dice(Color.RED, 4));
        panel.addDiceOnCellWithIndex(16, new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(17, new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(18, new Dice(Color.GREEN, 3));
        panel.addDiceOnCellWithIndex(19, new Dice(Color.PURPLE, 4));

        return panel;

    }

    public static WindowPanel panel70() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(1, new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.PURPLE, 5));
        panel.addDiceOnCellWithIndex(3, new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.YELLOW, 5));

        panel.addDiceOnCellWithIndex(5, new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(6, new Dice(Color.PURPLE, 5));
        panel.addDiceOnCellWithIndex(7, new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(8, new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(9, new Dice(Color.BLUE, 6));

        panel.addDiceOnCellWithIndex(10, new Dice(Color.PURPLE, 5));
        panel.addDiceOnCellWithIndex(11, new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(12, new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(13, new Dice(Color.BLUE, 6));
        panel.addDiceOnCellWithIndex(14, new Dice(Color.GREEN, 5));

        panel.addDiceOnCellWithIndex(15, new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(16, new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(17, new Dice(Color.BLUE, 6));
        panel.addDiceOnCellWithIndex(18, new Dice(Color.GREEN, 5));
        panel.addDiceOnCellWithIndex(19, new Dice(Color.PURPLE, 6));

        return panel;

    }

    public static WindowPanel panel200() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0,new Dice(Color.values()[0], 1));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.values()[1], 2));
        panel.addDiceOnCellWithIndex(2,new Dice(Color.values()[2], 1));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.values()[3], 2));
        panel.addDiceOnCellWithIndex(4,new Dice(Color.values()[4], 1));

        panel.addDiceOnCellWithIndex(5,new Dice(Color.values()[1], 3));
        panel.addDiceOnCellWithIndex(6,new Dice(Color.values()[2], 4));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.values()[3], 3));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.values()[4], 4));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.values()[0], 3));

        panel.addDiceOnCellWithIndex(10,new Dice(Color.values()[2], 1));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.values()[3], 2));
        panel.addDiceOnCellWithIndex(12,new Dice(Color.values()[4], 1));
        panel.addDiceOnCellWithIndex(13,new Dice(Color.values()[0], 2));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.values()[1], 1));

        panel.addDiceOnCellWithIndex(15,new Dice(Color.values()[3], 3));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.values()[4], 4));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.values()[0], 3));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.values()[1], 4));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.values()[2], 3));

        return panel;

    }

}