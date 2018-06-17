package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.publicobjectivecards.*;
import com.sagrada.ppp.cards.toolcards.ToolCard;
import com.sagrada.ppp.cards.toolcards.ToolCard1;
import com.sagrada.ppp.cards.toolcards.ToolCard12;
import com.sagrada.ppp.cards.toolcards.ToolCard5;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameStartMessageTest {

    private GameStartMessage gameStartMessage;
    private ArrayList<Dice> draftPool;
    private ArrayList<ToolCard> toolCards;
    private ArrayList<PublicObjectiveCard> publicObjectiveCards;
    private ArrayList<Player> players;
    private RoundTrack roundTrack;
    private Player currentPlayer;

    @Before
    public void setUp() throws Exception {
        draftPool = new ArrayList<>();
        toolCards = new ArrayList<>();
        publicObjectiveCards = new ArrayList<>();
        players = new ArrayList<>();
        roundTrack = new RoundTrack();
        currentPlayer = new Player("current");
        //loading data
        players.add(currentPlayer);
        players.add(new Player("pippo_2"));
        toolCards.add(new ToolCard1());
        toolCards.add(new ToolCard5());
        toolCards.add(new ToolCard12());
        publicObjectiveCards.add(new PublicObjectiveCard1());
        publicObjectiveCards.add(new PublicObjectiveCard5());
        publicObjectiveCards.add(new PublicObjectiveCard8());
        gameStartMessage = new GameStartMessage(draftPool, toolCards, publicObjectiveCards, players, roundTrack, currentPlayer);
    }

    @Test
    public void testContent() {
        assertEquals(draftPool, gameStartMessage.draftpool);
        assertEquals(toolCards, gameStartMessage.toolCards);
        assertEquals(publicObjectiveCards, gameStartMessage.publicObjectiveCards);
        assertEquals(players, gameStartMessage.players);
        assertEquals(currentPlayer, gameStartMessage.currentPlayer);
        assertEquals(roundTrack, gameStartMessage.roundTrack);
    }
}