package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rostik.andrusiv.analyzer.BaseTest;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzerTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AverageTest extends BaseTest {

     @Test
    public void printResult(){
        AverageOperationResult operationResult = new AverageOperationResult(123L);
        operationResult.printResult();
        assertTrue(baos.toString().contains("123"));
    }
}