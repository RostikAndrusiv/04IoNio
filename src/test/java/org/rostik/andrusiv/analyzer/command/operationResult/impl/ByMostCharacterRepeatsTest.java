package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import org.junit.Test;
import org.rostik.andrusiv.analyzer.BaseTest;

import static org.junit.Assert.*;

public class ByMostCharacterRepeatsTest extends BaseTest {

    @Test
    public void printResult() {
        ByMostCharacterRepeatsOperationResult operationResult =
                new ByMostCharacterRepeatsOperationResult("dummy stub");
        operationResult.printResult();
        assertTrue(baos.toString().contains("dummy stub"));
    }
}