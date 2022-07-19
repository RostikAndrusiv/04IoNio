package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.rostik.andrusiv.analyzer.command.operationResult.OperationResult;

@Slf4j
@EqualsAndHashCode
public class ByMostCharacterRepeatsOperationResult implements OperationResult {

    private final String pathToFileByMostSCharacterRepeat;

    public ByMostCharacterRepeatsOperationResult(String pathToFileByMostSCharacterRepeat) {
        this.pathToFileByMostSCharacterRepeat = pathToFileByMostSCharacterRepeat;
    }

    @Override
    public void printResult() {
        log.info("Path To File By Most S Character Repeat: " + pathToFileByMostSCharacterRepeat);
    }
}
