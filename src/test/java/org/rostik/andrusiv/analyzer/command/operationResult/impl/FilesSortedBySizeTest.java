package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import org.junit.Test;
import org.rostik.andrusiv.analyzer.BaseTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class FilesSortedBySizeTest extends BaseTest {

    @Test
    public void printResult() {
        List<String> stub = List.of("dummy stub");
        FilesSortedBySizeOperationResult operationResult = new FilesSortedBySizeOperationResult(stub);
        operationResult.printResult();
        assertTrue(baos.toString().contains("dummy stub"));
    }
}