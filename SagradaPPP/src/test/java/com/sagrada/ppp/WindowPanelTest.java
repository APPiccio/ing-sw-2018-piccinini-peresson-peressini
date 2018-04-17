package com.sagrada.ppp;

import org.junit.Test;

import java.io.FileNotFoundException;

import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;

public class WindowPanelTest {

    @Test
    public static void testDiceOn() throws FileNotFoundException {
        WindowPanel panel = new WindowPanel(1,1);

        Dice dice = new Dice(Color.YELLOW);

        assertEquals(true, panel.addDiceOnCellWithIndex(0,dice));
        //mettere dado dove c'è già
        assertEquals(false, panel.addDiceOnCellWithIndex(0,dice));
        //mettere dado dove c'è restizione
        dice = new Dice(3);
        assertEquals(false, panel.addDiceOnCellWithPosition(0,4,dice));

    }




    @Test
    public static void testPanelComposition() throws FileNotFoundException {

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

                        //System.out.println("currentCell =" + currentCell.getValue() + currentCell.getColor() + currentCell.hasDiceon());
                        //System.out.println("panelCell =" + panel.getCellWithPosition(k,l).getValue() + panel.getCellWithPosition(k,l).getColor() + panel.getCellWithPosition(k,l).hasDiceon());

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
                System.out.println(myString.toString());
            }

        }

        

    }
}
