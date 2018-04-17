package com.sagrada.ppp;

import java.io.FileNotFoundException;

public class TestPanels {





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
