package org.rostik.andrusiv.analyzer.command;

import lombok.NoArgsConstructor;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.ByMostCharacterRepeatsOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.util.Optional;

@NoArgsConstructor
public class ByMostCharacterRepeats implements DiskAnalyzerOperation<ByMostCharacterRepeatsOperationResult> {

    @Override
    public Optional<ByMostCharacterRepeatsOperationResult> execute(Command command) {
        return DiskAnalyzer.findPathToFileByMostSCharacterRepeat(command.getPath())
                .map(ByMostCharacterRepeatsOperationResult::new);
    }
}
