package org.rostik.andrusiv.analyzer.command;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.AverageOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.util.OptionalDouble;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class AvgFilesSizeTest {
    private final AvgFilesSize avgFilesSize = new AvgFilesSize();


    @Test
    public void executeTest() {
        long stub = 123L;
        AverageOperationResult expectedResult = new AverageOperationResult(stub);

        try (MockedStatic<DiskAnalyzer> analyzer = Mockito.mockStatic(DiskAnalyzer.class)) {
            analyzer.when(() -> DiskAnalyzer.getAvgFilesSize(any())).thenReturn(OptionalDouble.of(stub));
            assertFalse(avgFilesSize.execute(Mockito.mock(Command.class)).isEmpty());
            assertEquals(expectedResult, avgFilesSize.execute(Mockito.mock(Command.class)).get());
        }
    }

    @Test
    public void executeEmptyTest() {
        try (MockedStatic<DiskAnalyzer> analyzer = Mockito.mockStatic(DiskAnalyzer.class)) {
            analyzer.when(() -> DiskAnalyzer.getAvgFilesSize(any())).thenReturn(OptionalDouble.empty());
            assertTrue(avgFilesSize.execute(Mockito.mock(Command.class)).isEmpty());
        }
    }
}