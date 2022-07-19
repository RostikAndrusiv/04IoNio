package org.rostik.andrusiv.analyzer.command;

import org.rostik.andrusiv.analyzer.command.operationResult.OperationResult;

import java.util.Optional;

@FunctionalInterface
public interface DiskAnalyzerOperation<O extends OperationResult> {
        Optional<O> execute(Command command);
}
