package org.rostik.andrusiv.analyzer.command;

import lombok.NoArgsConstructor;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.AverageOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.util.Optional;
import java.util.OptionalDouble;

@NoArgsConstructor
public class AvgFilesSize implements DiskAnalyzerOperation<AverageOperationResult>{

    @Override
    public Optional<AverageOperationResult> execute(Command command) {
        return convert(DiskAnalyzer.getAvgFilesSize(command.getPath()))
                .map(AverageOperationResult::new);
    }

    private static Optional<Double> convert(OptionalDouble od) {
        return od.isPresent() ?
                Optional.of(od.getAsDouble()) : Optional.empty();
    }
}
