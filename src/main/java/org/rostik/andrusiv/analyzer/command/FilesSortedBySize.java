package org.rostik.andrusiv.analyzer.command;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.rostik.andrusiv.analyzer.command.operationResult.impl.FilesSortedBySizeOperationResult;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzer;

import java.util.Optional;

@NoArgsConstructor
public class FilesSortedBySize implements DiskAnalyzerOperation<FilesSortedBySizeOperationResult>{

    @Override
    public Optional<FilesSortedBySizeOperationResult> execute(Command command) {
        return Optional.ofNullable(DiskAnalyzer.getFilesSortedBySize(command.getPath()))
                .map(FilesSortedBySizeOperationResult::new);
    }
}
