package org.rostik.andrusiv.analyzer.client;

import lombok.extern.slf4j.Slf4j;
import org.rostik.andrusiv.analyzer.command.*;
import org.rostik.andrusiv.analyzer.command.operationResult.OperationResult;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

@Slf4j
//TODO exception, nullcheck.... DONE
//TODO FASFILEMOVER optimization nio2... didnt found
//TODO externalizable read about... DONE
//TODO serialization/de singletons, ENUM read about DONE
//TODO serialization cyclic dependencies
//TODO spy in tests instead redirecting sout
public class Main {
    static final Map<Integer, DiskAnalyzerOperation<? extends OperationResult>> COMMANDS = Map.of(
            1, new ByMostCharacterRepeats(),
            2, new DividedByFirstLetters(),
            3, new FilesSortedBySize(),
            4, new AvgFilesSize()
    );

    public static void main(String[] args) {
        Command command = validateCommand(args);
        Optional.ofNullable(COMMANDS.get(command.getNumber()))
                .ifPresentOrElse(
                        operation -> operation.execute(command)
                                .ifPresent(OperationResult::printResult),
                        ()->log.info("command was not found"));
    }

    static Command validateCommand(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Should be exactly two arguments");
        }
        try{
            int commandNumber = Integer.parseInt(args[0]);
            Path path = Path.of(args[1]);
            return new Command(commandNumber, path);
        } catch (Exception e){
            throw new IllegalArgumentException("Exception while parsing arguments" + e.getMessage());
        }
    }
}
