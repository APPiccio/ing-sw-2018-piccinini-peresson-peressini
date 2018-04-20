package com.sagrada.ppp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.FileNotFoundException;

/**
 * Unit test for simple App.
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

    public void testApp() throws IllegalDiceValueException, FileNotFoundException {

        CellTest.cellTest();
        DiceBagTest.diceBagTest();
        WindowPanelTest.testPanelComposition();
        WindowPanelTest.testDicePositioning();

        RoundTrackTest.testRoundTrack();
        RoundTrackTest.testRoundTrackBehavior();
        RoundTrackTest.testRemove();
        RoundTrackTest.testSetDiceMethods();
        RoundTrackTest.testGetters();


        GameTest.joiningTest();


        assertTrue( true);

    }

    public void testCards() {

        PublicObjectiveCardTest.card1();
        PublicObjectiveCardTest.card2();
        PublicObjectiveCardTest.card3();
        PublicObjectiveCardTest.card4();
        PublicObjectiveCardTest.card5();
        PublicObjectiveCardTest.card6();
        PublicObjectiveCardTest.card7();
        PublicObjectiveCardTest.card8();
        PublicObjectiveCardTest.card9();
        PublicObjectiveCardTest.card10();

    }

}