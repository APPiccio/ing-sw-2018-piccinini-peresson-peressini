package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.*;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class CommandToolCard12Test {

    private ToolCard toolCard12;

    @Before
    public void setUp() {
        toolCard12 = new ToolCard12();
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.panel_2_2();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);

        LinkedHashMap<Integer, Integer> positions = new LinkedHashMap<>();
        positions.put(12,11);
        //toolCard12.use(new CommandToolCard12(positions, windowPanelCopy));

        //Testing that non touched cells are equals to the original panel
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            if (i != 12 && i != 11) {
                assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
            }
        }

        //Testing change
        assertEquals(windowPanel.getCell(12), windowPanelCopy.getCell(11));

        windowPanelCopy = new WindowPanel(windowPanel);

        positions.put(11,12);
        //toolCard12.use(new CommandToolCard12(positions, windowPanelCopy));

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
        }
    }

}