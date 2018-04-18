package com.sagrada.ppp;

import java.io.FileNotFoundException;

public class TestPanels {

    public static WindowPanel panel_50() throws FileNotFoundException {

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

    //zig-zag panel
    public static WindowPanel panel_51() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(6, new Dice(Color.PURPLE, 2));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.PURPLE, 2));
        panel.addDiceOnCellWithIndex(8, new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.YELLOW, 1));

        panel.addDiceOnCellWithIndex(10, new Dice(Color.PURPLE, 2));
        panel.addDiceOnCellWithIndex(16, new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(12, new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(18, new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(14, new Dice(Color.GREEN, 2));

        return panel;

    }

    public static WindowPanel panel_60() throws FileNotFoundException {

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

    //zig-zag panel
    public static WindowPanel panel_61() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 3));
        panel.addDiceOnCellWithIndex(6, new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(8, new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.YELLOW, 3));

        panel.addDiceOnCellWithIndex(10, new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(16, new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(12, new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(18, new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(14, new Dice(Color.GREEN, 4));

        return panel;

    }

    public static WindowPanel panel_70() throws FileNotFoundException {

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

    //zig-zag panel
    public static WindowPanel panel_71() throws FileNotFoundException {

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(6, new Dice(Color.PURPLE, 6));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.PURPLE, 6));
        panel.addDiceOnCellWithIndex(8, new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.YELLOW, 5));

        panel.addDiceOnCellWithIndex(10, new Dice(Color.PURPLE, 6));
        panel.addDiceOnCellWithIndex(16, new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(12, new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(18, new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(14, new Dice(Color.GREEN, 6));

        return panel;

    }

    //NO color duplication in the same row, every color used, no 5 and 6 value
    public static WindowPanel panel_210() throws FileNotFoundException {

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

    //1 ROW with hole, 1 ROW with color duplication
    public static WindowPanel panel_211() throws FileNotFoundException{

        WindowPanel panel = new WindowPanel(0, 0);

        //Line with doubled color
        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.BLUE, 5));

        //row with hole
        panel.addDiceOnCellWithIndex(6,new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 2));

        //valid row
        panel.addDiceOnCellWithIndex(10,new Dice(Color.GREEN, 5));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.BLUE, 6));
        panel.addDiceOnCellWithIndex(12,new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(13,new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.PURPLE, 5));

        //valid row
        panel.addDiceOnCellWithIndex(15,new Dice(Color.PURPLE, 3));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.BLUE, 3));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.YELLOW, 4));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.RED, 3));

        return panel;
    }

    //no row with 5 different colors
    public static WindowPanel panel_212() throws FileNotFoundException{

        WindowPanel panel = new WindowPanel(0, 0);

        //GREEN duplicated
        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.BLUE, 5));

        //PURPLE DUPLICATED
        panel.addDiceOnCellWithIndex(5,new Dice(Color.PURPLE, 2));
        panel.addDiceOnCellWithIndex(6,new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.PURPLE, 1));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 2));

        //YELLOW DUPL
        panel.addDiceOnCellWithIndex(10,new Dice(Color.GREEN, 5));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.BLUE, 6));
        panel.addDiceOnCellWithIndex(12,new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(13,new Dice(Color.GREEN, 6));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.YELLOW, 5));

        //RED
        panel.addDiceOnCellWithIndex(15,new Dice(Color.PURPLE, 3));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.RED, 3));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.YELLOW, 4));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.RED, 3));

        return panel;
    }

    //no value duplication in the same row
    public static WindowPanel panel_310() throws FileNotFoundException{

        WindowPanel panel = new WindowPanel(0, 0);

        //Line with doubled color
        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.BLUE, 3));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.BLUE, 5));

        //row with hole
        panel.addDiceOnCellWithIndex(5,new Dice(Color.RED, 2));
        panel.addDiceOnCellWithIndex(6,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 6));

        //valid row
        panel.addDiceOnCellWithIndex(10,new Dice(Color.GREEN, 3));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(12,new Dice(Color.YELLOW, 5));
        panel.addDiceOnCellWithIndex(13,new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.PURPLE, 1));

        //valid row
        panel.addDiceOnCellWithIndex(15,new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.GREEN, 5));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.BLUE, 6));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.RED, 2));

        return panel;
    }

    //1 ROW with hole, 1 ROW with color duplication
    public static WindowPanel panel_311() throws FileNotFoundException{

        WindowPanel panel = new WindowPanel(0, 0);


        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.BLUE, 3));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.BLUE, 5));

        //row with hole
        panel.addDiceOnCellWithIndex(6,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 6));

        //valid row
        panel.addDiceOnCellWithIndex(10,new Dice(Color.GREEN, 3));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(12,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(13,new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.PURPLE, 1));

        //valid row
        panel.addDiceOnCellWithIndex(15,new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(16,new Dice(Color.GREEN, 5));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.BLUE, 6));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.RED, 2));

        return panel;
    }

    //no row with 5 different value
    public static WindowPanel panel_312() throws FileNotFoundException{

        WindowPanel panel = new WindowPanel(0, 0);

        panel.addDiceOnCellWithIndex(0, new Dice(Color.BLUE, 1));
        panel.addDiceOnCellWithIndex(1,new Dice(Color.GREEN, 2));
        panel.addDiceOnCellWithIndex(2, new Dice(Color.BLUE, 3));
        panel.addDiceOnCellWithIndex(3,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(4, new Dice(Color.BLUE, 3));

        panel.addDiceOnCellWithIndex(6,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(7,new Dice(Color.GREEN, 4));
        panel.addDiceOnCellWithIndex(8,new Dice(Color.BLUE, 5));
        panel.addDiceOnCellWithIndex(9,new Dice(Color.RED, 6));

        panel.addDiceOnCellWithIndex(10,new Dice(Color.GREEN, 3));
        panel.addDiceOnCellWithIndex(11,new Dice(Color.BLUE, 4));
        panel.addDiceOnCellWithIndex(12,new Dice(Color.YELLOW, 3));
        panel.addDiceOnCellWithIndex(13,new Dice(Color.RED, 6));
        panel.addDiceOnCellWithIndex(14,new Dice(Color.PURPLE, 1));

        panel.addDiceOnCellWithIndex(15,new Dice(Color.PURPLE, 4));
        panel.addDiceOnCellWithIndex(17,new Dice(Color.BLUE, 6));
        panel.addDiceOnCellWithIndex(18,new Dice(Color.YELLOW, 1));
        panel.addDiceOnCellWithIndex(19,new Dice(Color.RED, 2));

        return panel;
    }

}