package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import org.junit.Test;
import org.rostik.andrusiv.analyzer.BaseTest;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class DividedByFirstLettersTest extends BaseTest {

    @Test
    public void printResult() {
        Map<Character, Long> stub = Map.of('d', 1L);
        DividedByFirstLettersOperationResult operationResult = new DividedByFirstLettersOperationResult(stub);
        operationResult.printResult();
        assertTrue(baos.toString().contains("d=1"));
    }
}