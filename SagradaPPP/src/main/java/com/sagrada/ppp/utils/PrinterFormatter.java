package com.sagrada.ppp.utils;

import com.sagrada.ppp.Cell;
import com.sagrada.ppp.Color;
import com.sagrada.ppp.Dice;
import com.sagrada.ppp.WindowPanel;

import java.util.ArrayList;

public class PrinterFormatter {

    private final static String bottomCell = "------------";
    private static final char TOP_RIGHT = (int) '┐';
    private static final char TOP_LEFT = (int) '┌';
    private static final char TOP_CENTER = (int) '┬';
    private static final char MID_RIGHT = (int) '┤';
    private static final char MID_CENTER = (int) '┼';
    private static final char MID_LEFT = (int) '├';
    private static final char BOTTOM_RIGHT = (int) '┐';
    private static final char BOTTOM_CENTER = (int) '┴';
    private static final char BOTTOM_LEFT = (int) '└';
    private static final char HORIZZONTAL = (int) '─';
    private static final char VERTICAL = (int) '│';


    //width and height in chars
    private static final int CELL_WIDTH = 20;
    private static final int CELL_HEIGHT = 20;




    /*public static String printWindowPanel(WindowPanel panel){
        StringBuilder string = new StringBuilder();

        //generating top board
        string.append("+");
        for(int i = 0; i < StaticValues.PATTERN_COL; i++){
            string.append(bottomCell+"+");
        }
        string.append("\n");

        for(int row = 0; row < StaticValues.PATTERN_ROW; row++) {
            string.append("|");
            for(int i = 0; i < 3; i++) {

                switch (i){
                    case 0:
                        for (int col = 0; col < StaticValues.PATTERN_COL; col++) {
                            Cell cell = panel.getCell(row,col);
                            if(cell.hasDiceOn()) {
                                string.append(String.format("%" + 4 + "s", "_"));
                                string.append(String.format("%" + 9 + "s", "|"));
                            }
                            else{
                                string.append(String.format("%" + 13 + "s","|"));
                            }
                        }
                        string.append("\n|");
                        break;
                    case 1:
                        for (int col = 0; col < StaticValues.PATTERN_COL; col++) {
                            Cell cell = panel.getCell(row,col);
                            String h;
                            if(cell.hasDiceOn()) {
                                h = "|"+cell.getDiceOn().getValue() + "| " + cell.getDiceOn().getColor();
                                string.append(String.format("%" + 3 + "s",h));

                            }

                        }
                        string.append("\n");
                        break;
                    case 2:
                        break;
                    default:
                        break;

                }

            }
        }
        return  string.toString();
    }*/


    //Modified version of APPiccio's code. It's neater
    public static String printWindowPanelContent(WindowPanel panel){
        StringBuilder result = new StringBuilder();
        if(panel == null){
            return "This WindowPanel is Null!\n";
        }else {
            String format = "│%1$20s│%2$20s│%3$20s│%4$20s│%5$20s│\n";
            result.append("Panel Name: " + panel.getPanelName() + "\n");
            ArrayList<Cell> cells = panel.getCells();
            String[] row = new String[5];
            int i = 0;
            for (Cell c:cells) {
                if(c.hasDiceOn()){
                    row[i] = center(getColoredString(c.getDiceOn().getColor(),"" + c.getDiceOn().getValue()),20,' ',true);
                }else {

                    row[i] = center(getColoredString(Color.NULL,"NULL"),20, ' ',true);
                }
                if(i < StaticValues.PATTERN_COL - 1) i++;
                else {

                    result.append(String.format(format,row));
                    i = 0;
                }
            }
        }
        result.append(StaticValues.RESET);
        return result.toString();
    }

    //Modified version of APPiccio's code. It's neater
    public static String printWindowPanelLayout(WindowPanel panel){
        StringBuilder result = new StringBuilder();
        if(panel == null){
            return "This WindowPanel is Null!\n";
        }else {

            result.append("Panel Name: " + panel.getPanelName() + "\n");
            ArrayList<Cell> cells = panel.getCells();
            int i = 0;
            int row = 0;
            for (Cell c:cells) {
                if(c.hasColorRestriction()){
                    result.append("| "+c.getColor()+"  |\t");
                }else if(c.hasValueRestriction()){
                    result.append("| "+c.getValue()+" |\t\t");
                }
                else {
                    result.append("| BLANK |\t");
                }
                if(i < StaticValues.PATTERN_COL - 1) i++;
                else {
                    result.append("\n");
                    i = 0;
                }
            }
        }
        return result.toString();
    }



    public static void main(String[] args) {
        WindowPanel panel = new WindowPanel(0,0);
        panel.addDice(0, new Dice());
        panel.addDice(1, new Dice());
        panel.addDice(2, new Dice());
        panel.addDice(3, new Dice());
        panel.addDice(4, new Dice());
        panel.addDice(5, new Dice());
        panel.addDice(6, new Dice());
        panel.addDice(7, new Dice());
        panel.addDice(8, new Dice());

        System.out.println(printWindowPanelContent(panel));


        panel = new WindowPanel(4,1);
        System.out.println(printWindowPanelLayout(panel));

    }
    private static String getColoredString(Color color,String msg){
        switch (color){
            case GREEN:
                return StaticValues.GREEN_BOLD + msg + StaticValues.RESET;
            case RED:
                return StaticValues.RED_BOLD + msg + StaticValues.RESET;
            case BLUE:
                return StaticValues.BLUE_BOLD + msg + StaticValues.RESET;
            case YELLOW:
                return StaticValues.YELLOW_BOLD + msg + StaticValues.RESET;
            case PURPLE:
                return StaticValues.PURPLE_BOLD + msg + StaticValues.RESET;
            case NULL:
                return StaticValues.WHITE_BOLD + msg + StaticValues.RESET;
                default:
                    return StaticValues.RESET;
        }

    }

    private static String center(String msg, int lenght, char paddinChar, boolean pr){
        int pad = lenght-msg.length();
        String p = "";

        for (int i=0; i<pad/2; i++)
            p = p + paddinChar;

        /* If s.length is odd */
        if (pad%2 == 1)
            /* Pad one extra either right or left */
            if (pr) msg = msg + paddinChar;
            else msg = paddinChar + msg;
        return (p+msg+p);
    }

}
