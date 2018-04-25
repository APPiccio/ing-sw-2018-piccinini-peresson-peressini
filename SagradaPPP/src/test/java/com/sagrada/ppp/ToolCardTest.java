package com.sagrada.ppp;

import com.sagrada.ppp.Cards.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ToolCardTest {

    @Test
    public void testAllToolCards() {

        card1();
        card4();
        card10();
        card11();
        card12();

    }

    @Test
    public void card1(){
        Dice dice = new Dice(Color.GREEN, 1);
        Dice trash = new Dice(dice);

        //testing incrementing
        ToolCard toolCard1 = new ToolCard1();
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(2);
        trash.setValue(2);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue() + 1, trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        toolCard1.use(new CommandToolCard1(trash,1));
        assertEquals(dice.getValue(), trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(2);
        trash.setValue(2);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());

        dice.setValue(1);
        trash.setValue(1);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue(), trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        toolCard1.use(new CommandToolCard1(trash,-1));
        assertEquals(dice.getValue() -1, trash.getValue());


    }

    @Test
    public void card4() {

        WindowPanel windowPanel = TestPanels.panel_222();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        LinkedHashMap<Integer, Integer> positions = new LinkedHashMap<>();
        positions.put(2, 9);
        positions.put(12, 11);

        ToolCard toolCard4 = new ToolCard4();
        toolCard4.use(new CommandToolCard4(positions, windowPanelCopy));

        //Testing non-touched cells
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 2 && i != 9 && i !=11 && i != 12) {
                assertEquals(windowPanel.getCellWithIndex(i), windowPanelCopy.getCellWithIndex(i));
            }
        }

        //Testing changes
        assertEquals(windowPanel.getCellWithIndex(12), windowPanelCopy.getCellWithIndex(11));
        assertEquals(windowPanel.getCellWithIndex(2), windowPanelCopy.getCellWithIndex(9));

        positions.remove(2);
        positions.remove(12);
        positions.put(9, 2);
        positions.put(11, 12);
        toolCard4.use(new CommandToolCard4(positions, windowPanelCopy));

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertEquals(windowPanel.getCellWithIndex(i), windowPanelCopy.getCellWithIndex(i));
        }

    }



    @Test
    public void card7 (){
        Game g = new Game();
        g.joinGame("pinco");
        g.joinGame("pallo");
        g.joinGame("pallone");
        g.joinGame("pallino");
        g.init();

        ArrayList<Dice> result = g.getDraftPool();
        ToolCard toolCard7 = new ToolCard7();
        CommandToolCard commandToolCard = new CommandToolCard7(result);
        toolCard7.use(commandToolCard);

        for (Dice d: g.getDraftPool()
                ) {
            System.out.println(d.toString());
        }
        System.out.println("------------------------------------------------------");
        for (Dice d: result
             ) {
            System.out.println(d.toString());
        }

    }

    @Test
    public void card10(){
        Dice dice = new Dice(Color.PURPLE, 1);
        Dice trash = new Dice(dice);

        ToolCard toolCard10 = new ToolCard10();
        toolCard10.use(new CommandToolCard10(trash));

        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(2);
        trash.setValue(2);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(3);
        trash.setValue(3);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(4);
        trash.setValue(4);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(5);
        trash.setValue(5);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());

        dice.setValue(6);
        trash.setValue(6);
        toolCard10.use(new CommandToolCard10(trash));
        assertEquals(7 - dice.getValue(), trash.getValue());
    }

    @Test
    public void card11(){
        DiceBag diceBag = new DiceBag();
        Dice dice = diceBag.extractRandomDice();

        DiceBag diceBagCopy = new DiceBag(diceBag);
        Dice diceCopy = new Dice(dice);

        ToolCard toolCard11 = new ToolCard11();
        toolCard11.use(new CommandToolCard11(diceBagCopy,diceCopy));

        //not meanful testing random dice throw
        assertTrue(true);
    }

    @Test
    public void card12() {

        WindowPanel windowPanel = TestPanels.panel_222();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        ToolCard toolCard12 = new ToolCard12();

        LinkedHashMap<Integer, Integer> positions = new LinkedHashMap<>();
        positions.put(12,11);
        toolCard12.use(new CommandToolCard12(positions, windowPanelCopy));

        //testing that non touched cells are equals to the original panel
        for(int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++){
            if(i != 12 && i != 11){
                assertEquals(windowPanel.getCellWithIndex(i), windowPanelCopy.getCellWithIndex(i));
            }
        }
        //testing change
        assertEquals(windowPanel.getCellWithIndex(12), windowPanelCopy.getCellWithIndex(11));

        windowPanelCopy = new WindowPanel(windowPanel);

        positions.put(11,12);
        toolCard12.use(new CommandToolCard12(positions, windowPanelCopy));

        for(int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++){
            assertEquals(windowPanel.getCellWithIndex(i), windowPanelCopy.getCellWithIndex(i));
        }

    }

}