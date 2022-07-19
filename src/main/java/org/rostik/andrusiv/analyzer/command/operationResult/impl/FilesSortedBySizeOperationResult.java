package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.rostik.andrusiv.analyzer.command.operationResult.OperationResult;

import java.util.List;

@Slf4j
@EqualsAndHashCode
public class FilesSortedBySizeOperationResult implements OperationResult {

    private final List<String> filesSortedBySize;

    public FilesSortedBySizeOperationResult(List<String> filesSortedBySize) {
        this.filesSortedBySize = filesSortedBySize;
    }

    @Override
    public void printResult() {
        log.info("files Sorted By Size: " + filesSortedBySize);
    }
}
