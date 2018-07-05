package com.sagrada.ppp.utils;

import com.sagrada.ppp.model.Cell;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;

import java.util.ArrayList;

import static com.sagrada.ppp.utils.StaticValues.*;

public class PrinterFormatter {
    private PrinterFormatter(){
        //do nothing here
    }

    private static final char TOP_RIGHT = (int) '┐';
    private static final char TOP_LEFT = (int) '┌';
    private static final char TOP_CENTER = (int) '┬';
    private static final char MID_RIGHT = (int) '┤';
    private static final char MID_CENTER = (int) '┼';
    private static final char MID_LEFT = (int) '├';
    private static final char BOTTOM_RIGHT = (int) '┘';
    private static final char BOTTOM_CENTER = (int) '┴';
    private static final char BOTTOM_LEFT = (int) '└';
    private static final String HORIZONTAL = "─────────";
    private static final char VERTICAL = (int) '│';

    /**
     * @param panel: panel from witch this function creates it's visual representation
     * @return string containing a visual representation of a panel.
     */
    public static String printWindowPanelContent(WindowPanel panel){
        StringBuilder result = new StringBuilder();
        if(panel == null){
            return "This WindowPanel is Null!\n";
        }else {
            final String verticalDivider = BLACK_BOLD_BRIGHT + VERTICAL + RESET;
            final String top = BLACK_BOLD_BRIGHT + TOP_LEFT + "%1$9s" + TOP_CENTER + "%2$9s"+ TOP_CENTER + "%3$9s" + TOP_CENTER + "%4$9s" + TOP_CENTER + "%5$9s"+TOP_RIGHT+ RESET +"\n";
            final String mid = BLACK_BOLD_BRIGHT + MID_LEFT + "%1$9s" + MID_CENTER + "%2$9s"+ MID_CENTER + "%3$9s" + MID_CENTER + "%4$9s" + MID_CENTER + "%5$9s"+MID_RIGHT+ RESET +"\n";
            final String bottom = BLACK_BOLD_BRIGHT + BOTTOM_LEFT + "%1$9s" + BOTTOM_CENTER + "%2$9s"+ BOTTOM_CENTER + "%3$9s" + BOTTOM_CENTER + "%4$9s" + BOTTOM_CENTER + "%5$9s"+ BOTTOM_RIGHT+RESET +"\n";
            final String format = verticalDivider+"%1$9s"+verticalDivider+"%2$9s"+verticalDivider+"%3$9s"+verticalDivider+"%4$9s"+verticalDivider+"%5$9s"+verticalDivider+"\n";

            //insert title
            result.append("Panel Name: ");
            result.append(panel.getPanelName());
            result.append("\n");
            result.append("Favor Tokens: ");
            result.append(panel.getFavorTokens());
            result.append("\n");
            result.append("Card ID: ");
            result.append(panel.getCardID());
            result.append("\n");


            String[] rowValue = new String[5];
            String[] rowColor = new String[5];
            final String[] horizontalRow = {
                    HORIZONTAL, HORIZONTAL, HORIZONTAL, HORIZONTAL, HORIZONTAL
            };

            //add top table row
            result.append(String.format(top, (Object[]) horizontalRow));
            ArrayList<Cell> cells = panel.getCells();

            int i = 0;
            int rowCounter = 0;

            for (Cell c:cells) {
                if(c.hasDiceOn()){
                    Dice d = c.getDiceOn();
                    rowValue[i] = getColoredString(d.getColor(),center(" " + d.getValue(),9,' '));
                    rowColor[i] = getColoredString(d.getColor(),center(d.getColor().toString(),9,' '));
                }else if(c.hasColorRestriction()){
                    rowValue[i] =getColoredBkgrnd(c.getColor(),center(c.getColor().toString(),9,' '));
                    rowColor[i] = getColoredBkgrnd(c.getColor(),center("CELL",9,' '));
                }else if(c.hasValueRestriction()){
                    rowValue[i] = WHITE_BOLD_BRIGHT + center(Integer.toString(c.getValue()),9, ' ') + RESET;
                    rowColor[i] = WHITE_BOLD_BRIGHT +center( "CELL" ,9, ' ')+ RESET;
                }else {

                    rowValue[i] = WHITE_BACKGROUND_BRIGHT + center("BLANK" ,9, ' ')+ RESET;
                    rowColor[i] = WHITE_BACKGROUND_BRIGHT + center("CELL" ,9, ' ')+ RESET;

                }
                if(i < PATTERN_COL - 1) i++;
                else {
                    rowCounter++;
                    result.append(String.format(format, (Object[]) rowValue));
                    result.append(String.format(format, (Object[]) rowColor));
                    if(rowCounter < PATTERN_ROW)result.append(String .format(mid, (Object[]) horizontalRow));
                    i = 0;
                }
            }
            result.append(String.format(bottom, (Object[]) horizontalRow));

        }
        result.append(RESET);
        return result.toString();
    }

    private static String getColoredString(Color color, String msg){
        return getCommonString(color, msg, GREEN_BOLD_BRIGHT, RED_BOLD_BRIGHT, BLUE_BOLD_BRIGHT, YELLOW_BOLD_BRIGHT, PURPLE_BOLD_BRIGHT);

    }
    private static String getColoredBkgrnd(Color color,String msg){
        return getCommonString(color, msg, GREEN_BACKGROUND_BRIGHT, RED_BACKGROUND_BRIGHT, BLUE_BACKGROUND_BRIGHT, YELLOW_BACKGROUND_BRIGHT, PURPLE_BACKGROUND_BRIGHT);

    }

    /**
     * @param color chosen color for the output.
     * @param msg message to color
     * @param greenBackgroundBright green ASCII escape code.
     * @param redBackgroundBright red ASCII escape code.
     * @param blueBackgroundBright blue ASCII escape code.
     * @param yellowBackgroundBright yellow ASCII escape code.
     * @param purpleBackgroundBright purple ASCII escape code.
     * @return colored string.
     */
    private static String getCommonString(Color color, String msg, String greenBackgroundBright, String redBackgroundBright, String blueBackgroundBright, String yellowBackgroundBright, String purpleBackgroundBright) {
        switch (color){
            case GREEN:
                return greenBackgroundBright + msg + RESET;
            case RED:
                return redBackgroundBright + msg + RESET;
            case BLUE:
                return blueBackgroundBright + msg + RESET;
            case YELLOW:
                return yellowBackgroundBright + msg + RESET;
            case PURPLE:
                return purpleBackgroundBright + msg + RESET;
            default:
                return RESET;
        }
    }

    /**
     * @param msg message to center
     * @param length length of the final string
     * @param paddingChar char to use to center the message.
     * @return centered string
     */
    private static String center(String msg, int length, char paddingChar){
        int pad = length-msg.length();

        StringBuilder pBuilder = new StringBuilder();
        for (int i = 0; i<pad/2; i++)
            pBuilder.append(paddingChar);
        String p = pBuilder.toString();

        /* If s.length is odd */
        if (pad%2 == 1)
            msg = paddingChar + msg;
        return (p+msg+p);
    }

}
