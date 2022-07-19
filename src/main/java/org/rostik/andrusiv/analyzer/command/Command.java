package org.rostik.andrusiv.analyzer.command;

import lombok.Data;

import java.nio.file.Path;

@Data
public class Command {
    private final int number;
    private final Path path;
}
