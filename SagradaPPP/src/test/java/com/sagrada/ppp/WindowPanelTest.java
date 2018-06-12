package com.sagrada.ppp;

import com.sagrada.ppp.model.Cell;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static java.lang.Boolean.TRUE;

public class WindowPanelTest {
    public WindowPanelTest() {
//        testDicePositioning();
        testGetLegalPostion();
    }

    public WindowPanel generateBlankPanel(){
        ArrayList<Cell> cells = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            cells.add(new Cell());
        }
        return new WindowPanel("blank", 100, 0,  cells);
    }

    @Test
    public void testDicePositioning() {

        WindowPanel panel = generateBlankPanel();
        assertTrue(panel.addDice(4, new Dice()));
        panel = generateBlankPanel();
        assertTrue(panel.addDice(9, new Dice()));
        panel = generateBlankPanel();
        assertTrue(panel.addDice(14, new Dice()));
        panel = generateBlankPanel();
        assertTrue(panel.addDice(19, new Dice()));




        panel = new WindowPanel(1,1);

        //first dice in a non border position
        assertFalse(panel.addDice(8, new Dice()));

        //first dice in a border position
        assertTrue(panel.addDice(17, new Dice(Color.BLUE, 1)));

        //putting another dice without any other dice near
        assertFalse(panel.addDice(8, new Dice()));

        //putting a dice near another with the same color
        assertFalse(panel.addDice(16, new Dice(Color.BLUE)));

        //putting a dice near another with the same value
        assertFalse(panel.addDice(16, new Dice(1)));

        //putting a dice over another
        assertFalse(panel.addDice(17, new Dice()));

        //putting a near dice with different value and color
        assertTrue(panel.addDice(16, new Dice(Color.YELLOW, 3)));

        //putting a near dice with correct value and color but violating cell value restriction
        assertFalse(panel.addDice(15, new Dice(Color.GREEN, 5)));

        //same as the previous one but with cell color restriction
        assertFalse(panel.addDice(12, new Dice(Color.PURPLE, 5)));

        //previous but respecting restriction value and color
        assertTrue(panel.addDice(15, new Dice(Color.GREEN, 2)));
        assertTrue(panel.addDice(12, new Dice(Color.RED, 5)));

        //testing if the repo can't be returned
        WindowPanel panelCopy = new WindowPanel(panel);
        assertEquals(panelCopy, panel);
        assertNotEquals(panelCopy.hashCode(), panel.hashCode());

    }

    @Test
    public void testGetLegalPostion(){
        WindowPanel panel = generateBlankPanel();
        ArrayList<Integer> pos = panel.getLegalPosition(new Dice());
        assertEquals(14, pos.size());
        pos.forEach(System.out::println);
    }


    @Test
    public void testPanelComposition()  {

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
                        currentCell = panel.getCell(absIndex);

                        //System.out.println("currentCell =" + currentCell.getValue() + currentCell.getColor() + currentCell.hasDiceOn());
                        //System.out.println("panelCell =" + panel.getCell(k,l).getValue() + panel.getCell(k,l).getColor() + panel.getCell(k,l).hasDiceOn());

                        assertEquals(TRUE, currentCell.equals(panel.getCell(k,l)));

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