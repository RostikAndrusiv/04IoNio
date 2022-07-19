package org.rostik.andrusiv.analyzer.command;

import lombok.NoArgsConstructor;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.DividedByFirstLettersOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.util.Optional;

@NoArgsConstructor
public class DividedByFirstLetters implements DiskAnalyzerOperation<DividedByFirstLettersOperationResult>{

    @Override
    public Optional<DividedByFirstLettersOperationResult> execute(Command command) {
        return Optional.ofNullable(DiskAnalyzer.divideByFirstLetters(command.getPath()))
                .map(DividedByFirstLettersOperationResult::new);
    }
}
