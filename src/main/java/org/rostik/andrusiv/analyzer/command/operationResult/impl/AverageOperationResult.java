package org.rostik.andrusiv.analyzer.command.operationResult.impl;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.rostik.andrusiv.analyzer.command.operationResult.OperationResult;

@Slf4j
@EqualsAndHashCode
public class AverageOperationResult implements OperationResult {

    private final double avg;

    public AverageOperationResult(double avg){
        this.avg = avg;
    }

    @Override
    public void printResult() {
        log.info("avg file size: " + avg);
    }
}
