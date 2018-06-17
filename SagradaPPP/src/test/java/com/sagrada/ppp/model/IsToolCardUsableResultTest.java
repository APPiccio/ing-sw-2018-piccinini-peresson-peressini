package com.sagrada.ppp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsToolCardUsableResultTest {

    IsToolCardUsableResult isToolCardUsableResult;
    boolean result;
    int toolCardID;

    @Before
    public void setUp() throws Exception {
        result = true;
        toolCardID = 12;
        isToolCardUsableResult = new IsToolCardUsableResult(result, toolCardID);
    }

    @Test
    public void testContent() {
        assertEquals(toolCardID, isToolCardUsableResult.toolCardID);
        assertEquals(result, isToolCardUsableResult.result);
    }
}