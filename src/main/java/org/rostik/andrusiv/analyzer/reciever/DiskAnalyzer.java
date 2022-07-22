package org.rostik.andrusiv.analyzer.reciever;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.rostik.andrusiv.analyzer.exception.PathIsNullException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class DiskAnalyzer {

    private static final String PATH_IS_NOT_VALID = "path is not valid: ";

    private DiskAnalyzer() {
    }

    public static Map<Character, Long> divideByFirstLetters(Path path) {
        if (Objects.isNull(path)){
            throw new PathIsNullException("path is null");
        }
        try (Stream<Path> stream = Files.walk(path)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(p -> p.toString().substring(0, 1))
                    .flatMapToInt(String::chars)
                    .mapToObj(c -> (char) c)
                    .collect(groupingBy(a -> a, counting()));
        } catch (IOException e) {
            log.info(PATH_IS_NOT_VALID + e.getMessage());
            return Collections.emptyMap();
        }
    }

    public static List<String> getFilesSortedBySize(Path path) {
        if (Objects.isNull(path)){
            throw new PathIsNullException("path is null");
        }
        try (Stream<Path> stream = Files.walk(path)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toFile)
                    .sorted(Comparator.comparingLong(File::length).reversed())
                    .limit(5)
                    .map(File::getName)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.info(PATH_IS_NOT_VALID + e.getMessage());
            return Collections.emptyList();
        }
    }

    @SneakyThrows
    public static OptionalDouble getAvgFilesSize(Path path) {
        if (Objects.isNull(path)){
            throw new PathIsNullException("path is null");
        }
        try (Stream<Path> stream = Files.walk(path)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toFile)
                    .mapToDouble(File::length)
                    .average();
        } catch (IOException ex) {
            log.info(PATH_IS_NOT_VALID + ex.getMessage());
            return OptionalDouble.empty();
        }
    }

    public static Optional<String> findPathToFileByMostSCharacterRepeat(Path path) {
        if (Objects.isNull(path)){
            throw new PathIsNullException("path is null");
        }
        try (Stream<Path> stream = Files.walk(path)) {
            TreeMap<Long, Path> filesMap = stream
                    .filter(file -> !Files.isDirectory(file))
                    .collect(Collectors.toMap(
                            p -> p.getFileName().toString().chars().filter(c -> 's' == (char) c).count(),
                            p -> p, (m1, m2) -> m1, TreeMap::new));
            return Optional.ofNullable(filesMap.lastEntry())
                    .map(Map.Entry::getValue)
                    .map(Path::toString);
        } catch (IOException ex) {
            log.info(PATH_IS_NOT_VALID + ex.getMessage());
            return Optional.empty();
        }
    }
}
