package com.sagrada.ppp;

import org.junit.Test;
import static org.junit.Assert.*;
import static java.lang.Boolean.TRUE;

public class WindowPanelTest {

    @Test
    public static void testDicePositioning() {

        WindowPanel panel = new WindowPanel(1,1);

        //first dice in a non border position
        assertFalse(panel.addDiceOnCellWithIndex(8, new Dice()));

        //first dice in a border position
        assertTrue(panel.addDiceOnCellWithIndex(17, new Dice(Color.BLUE, 1)));

        //putting another dice without any other dice near
        assertFalse(panel.addDiceOnCellWithIndex(8, new Dice()));

        //putting a dice near another with the same color
        assertFalse(panel.addDiceOnCellWithIndex(16, new Dice(Color.BLUE)));

        //putting a dice near another with the same value
        assertFalse(panel.addDiceOnCellWithIndex(16, new Dice(1)));

        //putting a dice over another
        assertFalse(panel.addDiceOnCellWithIndex(17, new Dice()));

        //putting a near dice with different value and color
        assertTrue(panel.addDiceOnCellWithIndex(16, new Dice(Color.YELLOW, 3)));

        //putting a near dice with correct value and color but violating cell value restriction
        assertFalse(panel.addDiceOnCellWithIndex(15, new Dice(Color.GREEN, 5)));

        //same as the previous one but with cell color restriction
        assertFalse(panel.addDiceOnCellWithIndex(12, new Dice(Color.PURPLE, 5)));

        //previous but respecting restriction value and color
        assertTrue(panel.addDiceOnCellWithIndex(15, new Dice(Color.GREEN, 2)));
        assertTrue(panel.addDiceOnCellWithIndex(12, new Dice(Color.RED, 5)));

        //testing if the repo can't be returned
        WindowPanel panelCopy = new WindowPanel(panel);
        assertEquals(panelCopy, panel);
        assertNotEquals(panelCopy.hashCode(), panel.hashCode());

    }

    @Test
    public static void testPanelComposition()  {

        WindowPanel panel;
        int absIndex;
        Cell currentCell;
        StringBuilder myString;

        for(int i = 1; i < 13; i++){
            for(int j = 0; j < 2; j++){
                panel = new WindowPanel(i,1-j);
                absIndex = 0;
                myString = new StringBuilder();
                myString.append("PanelName = " + panel.getPanelName().toUpperCase() + "\n" );
                myString.append("Favor tokens = " + panel.getFavorTokens() + "\n" );
                myString.append("CardID = " + panel.getCardID() + "\n\n" );

                for(int k = 0; k < StaticValues.PATTERN_ROW; k++){
                    for(int l = 0; l < StaticValues.PATTERN_COL; l++){
                        currentCell = panel.getCellWithIndex(absIndex);

                        //System.out.println("currentCell =" + currentCell.getValue() + currentCell.getColor() + currentCell.hasDiceOn());
                        //System.out.println("panelCell =" + panel.getCellWithPosition(k,l).getValue() + panel.getCellWithPosition(k,l).getColor() + panel.getCellWithPosition(k,l).hasDiceOn());

                        assertEquals(TRUE, currentCell.equals(panel.getCellWithPosition(k,l)));

                        if(currentCell.hasColorRestriction()){
                            myString.append(currentCell.getColor().toString() + "|\t\t ");
                        }
                        else{
                            if(currentCell.hasValueRestriction()){
                                myString.append(currentCell.getValue() + "|\t\t ");
                            }
                            else{
                                myString.append("BLANK |\t\t");
                            }
                        }
                        absIndex++;
                    }
                    myString.append("\n____________________________________\n");
                }
                //System.out.println(myString.toString());
            }

        }



    }
}