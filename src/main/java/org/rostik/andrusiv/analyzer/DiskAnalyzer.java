package org.rostik.andrusiv.analyzer;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
//TODO getFilesWithSize1, findFilePathByFileName return path and print in printPathFileWithMostS
public class DiskAnalyzer {

    private DiskAnalyzer() {
    }

    public static void printPathFileWithMostS(String dir) {
        Optional.ofNullable(dir)
                .flatMap(DiskAnalyzer::findFileNameByNumberOfSChars)
                .map(fileName -> findFilePathByFileName(dir, fileName))
                .ifPresent(System.out::println);
    }

    public static void printFilesBySize(String dir) {
        var list = getFilesWithSize(dir);
        System.out.println(list);
    }

    public static void printAvgFilesSize(String dir) {
        var avgSize = getAvgFilesSize(dir).orElseThrow(RuntimeException::new);
        System.out.println(avgSize);
    }

    public static List<String> getFilesWithSize(String dir) {
        try (Stream<Path> stream = Files.walk(Path.of(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toFile)
                    .sorted(Comparator.comparingLong(File::length).reversed())
                    .limit(5)
                    .map(File::getName)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.info("path is not valid " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @SneakyThrows
    public static List<String> getFilesWithSize1(String dir) {
        try (Stream<Path> stream = Files.walk(Path.of(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .sorted(Comparator.comparingLong(DiskAnalyzer::getSize).reversed())
                    .limit(5)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }
    }

    @SneakyThrows
    private static long getSize(Path path) {
        return Files.size(path);
    }

    @SneakyThrows
    public static OptionalDouble getAvgFilesSize(String dir) {
        try (Stream<Path> stream = Files.walk(Path.of(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toFile)
                    .mapToDouble(File::length)
                    .average();
        }
    }

    public static void printDividedByFirstLetters(String dir) {
        try (Stream<Path> stream = Files.walk(Path.of(dir))) {
            var paths = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(p -> p.toString().substring(0, 1))
                    .flatMapToInt(String::chars)
                    .mapToObj(c -> (char) c)
                    .collect(groupingBy(a -> a, counting()));
            System.out.println(paths.toString());
        } catch (IOException e) {
            log.info("path is not valid" + e.getMessage());
        }
    }

    private static Optional<String> findFileNameByNumberOfSChars(String dir) {
        try (Stream<Path> stream = Files.walk(Path.of(dir))) {
            Map<String, Long> filesMap = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .distinct()
                    .collect(Collectors.toMap(Function.identity(),
                            s -> s.chars()
                                    .filter(c -> 's' == (char) c)
                                    .count()));
            var opt = filesMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue());
            return opt.map(Map.Entry::getKey);
        } catch (IOException ex) {
            log.info("path is not valid");
            return Optional.empty();
        }
    }

    private static Optional<String> findFilePathByFileName(String dir, String name) {
        try (Stream<Path> stream = Files.walk(Path.of(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .filter(p -> p.getFileName().toString().equals(name))
                    .map(Path::toString)
                    .findFirst();
        } catch (IOException e) {
            log.info("path is not valid");
            return Optional.empty();
        }
    }
}
