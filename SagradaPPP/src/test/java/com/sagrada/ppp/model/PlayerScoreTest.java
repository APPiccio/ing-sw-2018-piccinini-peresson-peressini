package com.sagrada.ppp.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerScoreTest {

    private ArrayList<PlayerScore> playersScore;
    private PlayerScore pl1;
    private PlayerScore pl2;
    private PlayerScore pl3;
    private int expectedTot;

    @Before
    public void setUp() throws Exception {
         playersScore = new ArrayList<>();

         expectedTot = 5-4+3+2+1+5;
         pl1 = new PlayerScore("pippo", 123, Color.GREEN, new WindowPanel(1,1), 5, 4, 3, 2, 1, 5 );
         pl2 = new PlayerScore("pluto", 354, Color.GREEN, new WindowPanel(1,1), 2, -4, 15, 14, 0, 10 );
         pl3 = new PlayerScore("paperino", 567, Color.GREEN, new WindowPanel(1,1), 0, 1, 22, 12, 7, 3 );

         playersScore.add(pl1);
         playersScore.add(pl2);
         playersScore.add(pl3);


    }

    @Test
    public void test() {
        PlayerScore h = new PlayerScore();
        h.setFavorTokenPoints(pl1.getFavorTokenPoints());
        h.setEmptyCellsPoints(pl1.getEmptyCellsPoints());
        h.setPlayerHashCode(pl1.getPlayerHashCode());
        h.setWindowPanel(pl1.getWindowPanel());
        h.setPrivateColor(pl1.getPrivateColor());
        h.setUsername(pl1.getUsername());
        h.setPrivateObjectiveCardPoints(pl1.getPrivateObjectiveCardPoints());
        h.setPublicObjectiveCard1Points(pl1.getPublicObjectiveCard1Points());
        h.setPublicObjectiveCard2Points(pl1.getPublicObjectiveCard2Points());
        h.setPublicObjectiveCard3Points(pl1.getPublicObjectiveCard3Points());

        assertEquals(h.getTotalPoints(), pl1.getTotalPoints());
        assertEquals(h.getUsername(), pl1.getUsername());
        assertEquals(h.getEmptyCellsPoints(), pl1.getEmptyCellsPoints());
        assertEquals(h.getPrivateObjectiveCardPoints(), pl1.getPrivateObjectiveCardPoints());
        assertEquals(h.getPublicObjectiveCard1Points(), pl1.getPublicObjectiveCard1Points());
        assertEquals(h.getPublicObjectiveCard2Points(), pl1.getPublicObjectiveCard2Points());
        assertEquals(h.getPublicObjectiveCard3Points(), pl1.getPublicObjectiveCard3Points());
        assertEquals(h.getPlayerHashCode(), pl1.getPlayerHashCode());
        assertEquals(h.getTotalPoints(), pl1.getTotalPoints());
        assertEquals(expectedTot, h.getTotalPoints());

    }

    @Test
    public void printTest() {
        String format = "| %-15s | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d |%n";
        System.out.format("+-----------------+-------+-------+-------+-------+-------+-------+-------+%n");
        System.out.format("| USERNAME        | TOT   | TOKEN | EMPTY | PRIV  | PUB1  | PUB2  | PUB3  |%n");
        System.out.format("+-----------------+-------+-------+-------+-------+-------+-------+-------+%n");

        for(PlayerScore score : playersScore){
            System.out.format(format, score.getUsername(), score.getTotalPoints(), score.getFavorTokenPoints(), score.getEmptyCellsPoints(), score.getPrivateObjectiveCardPoints(), score.getPublicObjectiveCard1Points(), score.getPublicObjectiveCard2Points(), score.getPublicObjectiveCard3Points());
            System.out.format("+-----------------+-------+-------+-------+-------+-------+-------+-------+%n");

        }
    }
}