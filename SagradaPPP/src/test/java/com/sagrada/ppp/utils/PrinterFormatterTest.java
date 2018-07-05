package com.sagrada.ppp.utils;

import com.sagrada.ppp.cards.TestPanels;
import com.sagrada.ppp.model.WindowPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrinterFormatterTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPrint() {
        WindowPanel windowPanel = TestPanels.panel_2_2();
        System.out.println(PrinterFormatter.printWindowPanelContent(windowPanel));
        assertEquals( "This WindowPanel is Null!\n" ,PrinterFormatter.printWindowPanelContent(null));
    }
}