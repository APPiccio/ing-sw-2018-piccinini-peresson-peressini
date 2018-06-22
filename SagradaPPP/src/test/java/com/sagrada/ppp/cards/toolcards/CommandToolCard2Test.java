package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import javafx.util.Pair;
import org.junit.*;

import static org.junit.Assert.*;

public class CommandToolCard2Test {

    private ToolCard toolCard2;

    @Before
    public void setUp() {
        toolCard2 = new ToolCard2();
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.toolCardPanel();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        Pair<Integer, Integer> positions = new Pair<>(13, 6);

        //toolCard2.use(new CommandToolCard2(positions, windowPanelCopy));

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

}