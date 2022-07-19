package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.rostik.andrusiv.analyzer.command.operationResult.OperationResult;

import java.util.Map;

@Slf4j
@EqualsAndHashCode
public class DividedByFirstLettersOperationResult implements OperationResult {

    private final Map<Character, Long> divideByFirstLetters;

    public DividedByFirstLettersOperationResult(Map<Character, Long> divideByFirstLetters) {
        this.divideByFirstLetters = divideByFirstLetters;
    }

    @Override
    public void printResult() {
        log.info("divided by first letters: " + divideByFirstLetters);
    }
}
