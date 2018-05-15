package com.sagrada.ppp;

import com.sagrada.ppp.cards.ToolCards.*;
import com.sagrada.ppp.utils.PrinterFormatter;
import com.sagrada.ppp.utils.StaticValues;
import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ToolCardTest {

    @Test
    public void testAllToolCards() {

        card1();
        card2();
        card3();
        card4();
        card5();
        card6();
        card7();
        card8();
        card9();
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
    public void card2() {

        WindowPanel windowPanel = TestPanels.toolCardPanel_X();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        Pair<Integer, Integer> positions = new Pair<>(13, 6);

        ToolCard toolCard2 = new ToolCard2();
        toolCard2.use(new CommandToolCard2(positions, windowPanelCopy));

        //Testing non-touched cells
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 6 && i != 13) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing changes
        assertEquals(windowPanel.getCell(13).getDiceOn().getValue(),
                windowPanelCopy.getCell(6).getDiceOn().getValue());
        assertEquals(windowPanel.getCell(13).getDiceOn().getColor(),
                windowPanelCopy.getCell(6).getDiceOn().getColor());

    }

    @Test
    public void card3() {

        WindowPanel windowPanel = TestPanels.toolCardPanel_X();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        Pair<Integer, Integer> positions = new Pair<>(13, 9);

        ToolCard toolCard3 = new ToolCard3();
        toolCard3.use(new CommandToolCard3(positions, windowPanelCopy));

        //Testing non-touched cells
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 9 && i != 13) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing changes
        assertEquals(windowPanel.getCell(13).getDiceOn().getValue(),
                windowPanelCopy.getCell(9).getDiceOn().getValue());
        assertEquals(windowPanel.getCell(13).getDiceOn().getColor(),
                windowPanelCopy.getCell(9).getDiceOn().getColor());

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
            if (i != 2 && i != 9 && i != 11 && i != 12) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing changes
        assertEquals(windowPanel.getCell(12), windowPanelCopy.getCell(11));
        assertEquals(windowPanel.getCell(2), windowPanelCopy.getCell(9));

        positions.remove(2);
        positions.remove(12);
        positions.put(9, 2);
        positions.put(11, 12);
        toolCard4.use(new CommandToolCard4(positions, windowPanelCopy));

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
        }

    }

    @Test
    public void card5() {

        Dice draftPoolDice = new Dice(Color.BLUE, 5);
        Dice roundTrackDice = new Dice(Color.GREEN, 1);
        Dice draftPoolDiceCopy = new Dice(draftPoolDice);
        Dice roundTrackDiceCopy = new Dice(roundTrackDice);

        assertEquals(draftPoolDice.getValue(), draftPoolDiceCopy.getValue());
        assertEquals(draftPoolDice.getColor(), draftPoolDiceCopy.getColor());
        assertEquals(roundTrackDice.getValue(), roundTrackDiceCopy.getValue());
        assertEquals(roundTrackDice.getColor(), roundTrackDiceCopy.getColor());

        ToolCard toolCard5 = new ToolCard5();
        toolCard5.use(new CommandToolCard5(roundTrackDiceCopy, draftPoolDiceCopy));

        assertEquals(draftPoolDice.getValue(), roundTrackDiceCopy.getValue());
        assertEquals(draftPoolDice.getColor(), roundTrackDiceCopy.getColor());
        assertEquals(roundTrackDice.getValue(), draftPoolDiceCopy.getValue());
        assertEquals(roundTrackDice.getColor(), draftPoolDiceCopy.getColor());
        assertNotEquals(draftPoolDice.getValue(), draftPoolDiceCopy.getValue());
        assertNotEquals(draftPoolDice.getColor(), draftPoolDiceCopy.getColor());
        assertNotEquals(roundTrackDice.getValue(), roundTrackDiceCopy.getValue());
        assertNotEquals(roundTrackDice.getColor(), roundTrackDiceCopy.getColor());

    }

    /*@Test
    public void card5v2() {

        //Game Class emulation

        RoundTrack roundTrack = new RoundTrack(10);

        //supposed to be at the seventh round, 4 players participating at the game
        for (int i = 1; i <= 7; i++) { //i indicate the round number
            ArrayList<Dice> dices = new ArrayList<>();
            Random rand = new Random();
            int remainingDices = rand.nextInt(9) + 1; //random number between 1 and 9
            for (int j = 0; j < remainingDices; j++) {
                dices.add(new Dice());
            }
            roundTrack.setDicesOnTurn(i, dices);
        }

        ArrayList<Dice> dices = new ArrayList<>(); //extracted dices
        for (int i = 0; i < 9; i++) {
            dices.add(new Dice());
        }

        //Getting the two dices that will be swapped
        int  draftPoolDiceIndex = new Random().nextInt(dices.size());
        Dice draftPoolDice = dices.get(draftPoolDiceIndex);
        int round = new Random().nextInt(7) + 1;
        int roundIndex = new Random().nextInt(roundTrack.dicesOnRound(round));
        Dice roundTrackDice = roundTrack.getDice(round, roundIndex);

        //Starting real Game Class emulation

        Dice draftPoolDiceCopy = new Dice(draftPoolDice);
        Dice roundTrackDiceCopy = new Dice(roundTrackDice);

        ToolCard toolCard5 = new ToolCard5();
        toolCard5.use(new CommandToolCard5(roundTrackDiceCopy, draftPoolDiceCopy));

        assertEquals(draftPoolDice.getValue(), roundTrackDiceCopy.getValue());
        assertEquals(draftPoolDice.getColor(), roundTrackDiceCopy.getColor());
        assertEquals(roundTrackDice.getValue(), draftPoolDiceCopy.getValue());
        assertEquals(roundTrackDice.getColor(), draftPoolDiceCopy.getColor());
        assertNotEquals(draftPoolDice.getValue(), draftPoolDiceCopy.getValue());
        assertNotEquals(draftPoolDice.getColor(), draftPoolDiceCopy.getColor());
        assertNotEquals(roundTrackDice.getValue(), roundTrackDiceCopy.getValue());
        assertNotEquals(roundTrackDice.getColor(), roundTrackDiceCopy.getColor());

        ArrayList<Dice> dicesCopy = new ArrayList<>(dices);
        RoundTrack roundTrackCopy = new RoundTrack(roundTrack);

        dicesCopy.set(draftPoolDiceIndex, draftPoolDiceCopy);
        roundTrackCopy.setDice(round, roundIndex, roundTrackDiceCopy);

        assertNotEquals(dices, dicesCopy);
        assertNotEquals(roundTrack, roundTrackCopy);
        assertNotEquals(dices.get(draftPoolDiceIndex), dicesCopy.get(draftPoolDiceIndex));

    }*/

    @Test
    public void card6(){
    Player player =new Player("test");
    assertNull(player.getActiveDice());
    Dice dice = new Dice();
    CommandToolCard commandToolCard = new CommandToolCard6(dice,player);
    commandToolCard.useCard();
    assertNotNull(player.getActiveDice());

    }

    @Test
    public void card7 (){
        ArrayList<Dice> draftPool = new ArrayList<>();
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.add(new Dice());
        draftPool.stream().forEach(x -> System.out.println(x));
        CommandToolCard commandToolCard = new CommandToolCard7(draftPool);
        commandToolCard.useCard();
        draftPool.stream().forEach(x -> System.out.println(x));

        //no actual test performed
    }
    @Test
    public void card8(){
        Dice dice = new Dice();
        Player player = new Player("test");
        CommandToolCard commandToolCard = new CommandToolCard8(player,dice);
        assertNull(player.getActiveDice());
        commandToolCard.useCard();
        assertEquals(player.getActiveDice(),dice);

    }
    CommandToolCard commandToolCard9;
    @Test
    public void card9(){
        Player player = new Player("test");
        int index = 0;

        WindowPanel panel = new WindowPanel(10, StaticValues.FRONT_SIDE);

        panel.addDice(2, new Dice(Color.GREEN, 3));

        panel.addDice(8,new Dice(Color.BLUE, 6));


        commandToolCard9 = new CommandToolCard9(player,index);
        Dice dice = new Dice();
        player.setActiveDice(dice);
        player.setPanel(panel);

        commandToolCard9.useCard();

        assertEquals(player.getPanel().getCell(index).getDiceOn(),dice);

        testIllegalStateExceptionToolCard9_1();
        player.setActiveDice(new Dice());
        testIllegalStateExceptionToolCard9_2();




    }
    @Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionToolCard9_1(){
        Player player = new Player("test");

        new CommandToolCard9(player,19).useCard();
    }
    @Test(expected = IllegalStateException.class)
    public void testIllegalStateExceptionToolCard9_2(){
        commandToolCard9.useCard();
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
                assertTrue(windowPanel.getCell(i).equals(windowPanelCopy.getCell(i)));
            }
        }
        //testing change
        assertTrue(windowPanel.getCell(12).equals(windowPanelCopy.getCell(11)));

        windowPanelCopy = new WindowPanel(windowPanel);

        positions.put(11,12);
        toolCard12.use(new CommandToolCard12(positions, windowPanelCopy));

        for(int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++){
            assertTrue(windowPanel.getCell(i).equals(windowPanelCopy.getCell(i)));
        }

    }

}