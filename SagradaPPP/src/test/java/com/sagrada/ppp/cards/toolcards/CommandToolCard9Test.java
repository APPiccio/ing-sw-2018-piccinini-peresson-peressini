package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.Player;
import com.sagrada.ppp.model.WindowPanel;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard9Test {

    private ToolCard toolCard9;

    @Before
    public void setUp() {
        toolCard9 = new ToolCard9();
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.toolCardPanel();
        Player player = new Player("test");
        player.setPanel(windowPanel);

        assertEquals(17, player.getPanel().getEmptyCells());

        //toolCard9.use(new CommandToolCard9(player, 19, new Dice(Color.YELLOW, 4)));

        assertEquals(17, player.getPanel().getEmptyCells());

        //toolCard9.use(new CommandToolCard9(player, 0, new Dice(Color.YELLOW, 4)));

        assertEquals(16, player.getPanel().getEmptyCells());

        //toolCard9.use(new CommandToolCard9(player, 15, new Dice(2)));

        assertEquals(16, player.getPanel().getEmptyCells());
    }

}