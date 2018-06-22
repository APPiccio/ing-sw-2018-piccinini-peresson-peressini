package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import javafx.util.Pair;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard3Test {

    private ToolCard toolCard3;

    @Before
    public void setUp() {
        toolCard3 = new ToolCard3();
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.toolCardPanel();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        Pair<Integer, Integer> positions = new Pair<>(13, 9);

        //toolCard3.use(new CommandToolCard3(positions, windowPanelCopy));

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

}