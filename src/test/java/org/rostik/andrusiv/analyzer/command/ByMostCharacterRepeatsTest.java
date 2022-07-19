package org.rostik.andrusiv.analyzer.command;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.ByMostCharacterRepeatsOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class ByMostCharacterRepeatsTest {

    private final ByMostCharacterRepeats byMostCharacterRepeats = new ByMostCharacterRepeats();

    @Test
    public void executeTest() {
        String stub = "dummy stub";
        ByMostCharacterRepeatsOperationResult expectedResult = new ByMostCharacterRepeatsOperationResult(stub);

        try (MockedStatic<DiskAnalyzer> analyzer = Mockito.mockStatic(DiskAnalyzer.class)) {
            analyzer.when(() -> DiskAnalyzer.findPathToFileByMostSCharacterRepeat(any())).thenReturn(Optional.ofNullable(stub));
            assertFalse(byMostCharacterRepeats.execute(Mockito.mock(Command.class)).isEmpty());
            assertEquals(expectedResult, byMostCharacterRepeats.execute(Mockito.mock(Command.class)).get());
        }
    }

    @Test
    public void executeEmptyTest() {
        try (MockedStatic<DiskAnalyzer> analyzer = Mockito.mockStatic(DiskAnalyzer.class)) {
            analyzer.when(() -> DiskAnalyzer.findPathToFileByMostSCharacterRepeat(any())).thenReturn(Optional.empty());
            assertTrue(byMostCharacterRepeats.execute(Mockito.mock(Command.class)).isEmpty());
        }
    }
}