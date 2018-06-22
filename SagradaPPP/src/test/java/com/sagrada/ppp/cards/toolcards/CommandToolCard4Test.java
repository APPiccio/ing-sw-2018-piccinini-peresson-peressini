package com.sagrada.ppp.cards.toolcards;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import org.junit.*;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class CommandToolCard4Test {

    private ToolCard toolCard4;

    @Before
    public void setUp() {
        toolCard4 = new ToolCard4();
    }

    @Test
    public void useCard() {
        WindowPanel windowPanel = TestPanels.panel_2_2();
        WindowPanel windowPanelCopy = new WindowPanel(windowPanel);
        LinkedHashMap<Integer, Integer> positions = new LinkedHashMap<>();
        positions.put(2, 9);
        positions.put(12, 11);

        //toolCard4.use(new CommandToolCard4(positions, windowPanelCopy));

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
        //toolCard4.use(new CommandToolCard4(positions, windowPanelCopy));

        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            assertEquals(windowPanel.getCell(i), windowPanelCopy.getCell(i));
        }
    }

}