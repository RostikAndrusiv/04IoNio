package org.rostik.andrusiv.analyzer.command;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.DividedByFirstLettersOperationResult;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.FilesSortedBySizeOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class DividedByFirstLettersTest {

    private final DividedByFirstLetters dividedByFirstLetters= new DividedByFirstLetters();

    @Test
    public void executeTest() {
        Map<Character, Long>  stub = Map.of('d', 1L);
        DividedByFirstLettersOperationResult expectedResult = new DividedByFirstLettersOperationResult(stub);

        try (MockedStatic<DiskAnalyzer> analyzer = Mockito.mockStatic(DiskAnalyzer.class)) {
            analyzer.when(() -> DiskAnalyzer.divideByFirstLetters(any())).thenReturn(stub);
            assertFalse(dividedByFirstLetters.execute(Mockito.mock(Command.class)).isEmpty());
            assertEquals(expectedResult, dividedByFirstLetters.execute(Mockito.mock(Command.class)).get());
        }
    }
}