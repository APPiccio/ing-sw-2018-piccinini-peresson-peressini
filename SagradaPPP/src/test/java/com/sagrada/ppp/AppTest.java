package com.sagrada.ppp;

import com.sagrada.ppp.model.CellTest;
import com.sagrada.ppp.model.ColorTest;
import com.sagrada.ppp.model.DiceBagTest;
import com.sagrada.ppp.model.DiceTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    public void testApp(){

        new WindowPanelTest();
        new RoundTrackTest();
        //new GameTest();

    }

    public void testCell() {
        CellTest cellTest = new CellTest();
        cellTest.testCell();
    }

    public void testColor() {
        ColorTest colorTest = new ColorTest();
        colorTest.testAll();
    }

    public void testDiceBag() {
        DiceBagTest diceBagTest = new DiceBagTest();
        diceBagTest.testAll();
    }

    public void testDice() {
        DiceTest diceTest = new DiceTest();
        diceTest.testAll();
    }

    public void testPublicObjectiveCards() {
        PublicObjectiveCardTest publicObjectiveCardTest = new PublicObjectiveCardTest();
        publicObjectiveCardTest.testAllPublicObjectiveCard();
    }

    public void testToolCards(){
        ToolCardTest toolCardTest = new ToolCardTest();
        toolCardTest.testAllToolCards();
    }

}