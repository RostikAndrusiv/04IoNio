package org.rostik.andrusiv.analyzer.invoker;

import org.rostik.andrusiv.analyzer.command.Command;
import org.rostik.andrusiv.analyzer.command.DiskAnalyzerOperation;

import java.util.ArrayList;
import java.util.List;
//TODO do we actually need this?
public class DiskAnalyzerExecutor {
    private final List<DiskAnalyzerOperation> diskAnalyzerOperations = new ArrayList<>();

    public void executeOperation(DiskAnalyzerOperation diskAnalyzerOperation, Command command) {
        diskAnalyzerOperations.add(diskAnalyzerOperation);
        diskAnalyzerOperation.execute(command);
    }
}
