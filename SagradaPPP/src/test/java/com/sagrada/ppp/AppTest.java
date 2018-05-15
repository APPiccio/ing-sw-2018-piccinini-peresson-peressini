package com.sagrada.ppp;

import com.sagrada.ppp.utils.IllegalDiceValueException;
import com.sagrada.ppp.utils.PrinterFormatter;
import com.sagrada.ppp.utils.StaticValues;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Unit test for simple Client.
 */
public class AppTest
        extends TestCase
{
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
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */

    public void testApp(){

        new CellTest();
        new DiceBagTest();
        new DiceTest();
        new WindowPanelTest();
        new RoundTrackTest();
        //new GameTest();
        assertEquals(50000, StaticValues.getLobbyTimer());
        assertTrue( true);


    }

    public void testToolCard(){
        ToolCardTest toolCardTest = new ToolCardTest();
        toolCardTest.testAllToolCards();
    }

    public void testCards() {

        new PublicObjectiveCardTest();

    }

}