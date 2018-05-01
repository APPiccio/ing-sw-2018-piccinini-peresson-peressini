package com.sagrada.ppp.utils;

import com.sagrada.ppp.*;

import java.util.ArrayList;

import static com.sagrada.ppp.utils.StaticValues.*;

public class PrinterFormatter {

    private static final char TOP_RIGHT = (int) '┐';
    private static final char TOP_LEFT = (int) '┌';
    private static final char TOP_CENTER = (int) '┬';
    private static final char MID_RIGHT = (int) '┤';
    private static final char MID_CENTER = (int) '┼';
    private static final char MID_LEFT = (int) '├';
    private static final char BOTTOM_RIGHT = (int) '┘';
    private static final char BOTTOM_CENTER = (int) '┴';
    private static final char BOTTOM_LEFT = (int) '└';
    private static final char HORIZZONTAL = (int) '─';
    private static final char VERTICAL = (int) '│';


    public static String printWindowPanelContent(WindowPanel panel){
        StringBuilder result = new StringBuilder();
        if(panel == null){
            return "This WindowPanel is Null!\n";
        }else {
            String verticalDivider = BLACK_BOLD_BRIGHT + VERTICAL + RESET;
            String top = BLACK_BOLD_BRIGHT + TOP_LEFT + "%1$9s" + TOP_CENTER + "%2$9s"+ TOP_CENTER + "%3$9s" + TOP_CENTER + "%4$9s" + TOP_CENTER + "%5$9s"+TOP_RIGHT+ RESET +"\n";
            String mid = BLACK_BOLD_BRIGHT + MID_LEFT + "%1$9s" + MID_CENTER + "%2$9s"+ MID_CENTER + "%3$9s" + MID_CENTER + "%4$9s" + MID_CENTER + "%5$9s"+MID_RIGHT+ RESET +"\n";
            String bottom = BLACK_BOLD_BRIGHT + BOTTOM_LEFT + "%1$9s" + BOTTOM_CENTER + "%2$9s"+ BOTTOM_CENTER + "%3$9s" + BOTTOM_CENTER + "%4$9s" + BOTTOM_CENTER + "%5$9s"+ BOTTOM_RIGHT+RESET +"\n";
            String format = verticalDivider+"%1$20s"+verticalDivider+"%2$20s"+verticalDivider+"%3$20s"+verticalDivider+"%4$20s"+verticalDivider+"%5$20s"+verticalDivider+"\n";

            //insert title
            result.append("Panel Name: " + panel.getPanelName() + "\n");
            result.append("Favor Tokens: " + panel.getFavorTokens() + "\n");
            result.append("Card ID: " + panel.getCardID() + "\n");


            String[] rowValue = new String[5];
            String[] rowColor = new String[5];
            String[] horizontalRow = {
                    "─────────","─────────","─────────","─────────","─────────",
            };

            //add top table row
            result.append(String.format(top,horizontalRow));
            ArrayList<Cell> cells = panel.getCells();

            int i = 0;
            int rowCounter = 0;

            for (Cell c:cells) {
                if(c.hasDiceOn()){
                    Dice d = c.getDiceOn();
                    rowValue[i] = center(getColoredString(d.getColor(),"" + d.getValue()),20,' ',true);
                    rowColor[i] = center(getColoredString(d.getColor(),d.getColor().toString()),20,' ',true);
                }else if(c.hasColorRestriction()){
                    rowValue[i] =getColoredBkgrnd(c.getColor(),center(c.getColor().toString(),9,' ',true));
                    rowColor[i] = getColoredBkgrnd(c.getColor(),center("CELL",9,' ',true));
                }else if(c.hasValueRestriction()){
                    rowValue[i] = center(WHITE_UNDERLINED + c.getValue() + RESET,20, ' ',true);
                    rowColor[i] = center(WHITE_UNDERLINED + "CELL" + RESET,20, ' ',true);
                }else {

                    rowValue[i] = center(WHITE_UNDERLINED + "BLANK" + RESET,20, ' ',true);
                    rowColor[i] = center(WHITE_UNDERLINED + "CELL" + RESET,20, ' ',true);

                }
                if(i < PATTERN_COL - 1) i++;
                else {
                    rowCounter++;
                    result.append(String.format(format,rowValue));
                    result.append(String.format(format,rowColor));
                    if(rowCounter < PATTERN_ROW)result.append(String .format(mid,horizontalRow));
                    i = 0;
                }
            }
            result.append(String.format(bottom,horizontalRow));

        }
        result.append(RESET);
        return result.toString();
    }




    public static void main(String[] args) {
        WindowPanel panel = new WindowPanel(3,0);
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
        System.out.println(printWindowPanelContent(panel));

    }
    private static String getColoredString(Color color,String msg){
        switch (color){
            case GREEN:
                return GREEN_BOLD + msg + RESET;
            case RED:
                return RED_BOLD + msg + RESET;
            case BLUE:
                return BLUE_BOLD + msg + RESET;
            case YELLOW:
                return YELLOW_BOLD + msg + RESET;
            case PURPLE:
                return PURPLE_BOLD + msg + RESET;
                default:
                    return RESET;
        }

    }
    private static String getColoredBkgrnd(Color color,String msg){
        switch (color){
            case GREEN:
                return GREEN_BACKGROUND_BRIGHT + msg + RESET;
            case RED:
                return RED_BACKGROUND_BRIGHT + msg + RESET;
            case BLUE:
                return BLUE_BACKGROUND_BRIGHT + msg + RESET;
            case YELLOW:
                return YELLOW_BACKGROUND_BRIGHT + msg + RESET;
            case PURPLE:
                return PURPLE_BACKGROUND_BRIGHT + msg + RESET;
            default:
                return RESET;
        }

    }

    private static String center(String msg, int lenght, char paddinChar, boolean pr){
        int pad = lenght-msg.length();

        StringBuilder pBuilder = new StringBuilder();
        for (int i = 0; i<pad/2; i++)
            pBuilder.append(paddinChar);
        String p = pBuilder.toString();

        /* If s.length is odd */
        if (pad%2 == 1)
            /* Pad one extra either right or left */
            if (pr) msg = msg + paddinChar;
            else msg = paddinChar + msg;
        return (p+msg+p);
    }

}
