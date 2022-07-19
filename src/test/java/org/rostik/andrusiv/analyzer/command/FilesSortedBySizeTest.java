package org.rostik.andrusiv.analyzer.command;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.FilesSortedBySizeOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.awt.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class FilesSortedBySizeTest {

    private final FilesSortedBySize filesSortedBySize = new FilesSortedBySize();

    @Test
    public void executeTest() {
        List<String> stub = List.of("dummy stub");
        FilesSortedBySizeOperationResult expectedResult = new FilesSortedBySizeOperationResult(stub);

        try (MockedStatic<DiskAnalyzer> analyzer = Mockito.mockStatic(DiskAnalyzer.class)) {
            analyzer.when(() -> DiskAnalyzer.getFilesSortedBySize(any())).thenReturn(stub);
            assertFalse(filesSortedBySize.execute(Mockito.mock(Command.class)).isEmpty());
            assertEquals(expectedResult, filesSortedBySize.execute(Mockito.mock(Command.class)).get());
        }
    }
}