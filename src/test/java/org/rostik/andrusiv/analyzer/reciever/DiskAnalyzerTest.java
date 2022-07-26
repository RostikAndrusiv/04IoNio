package org.rostik.andrusiv.analyzer.reciever;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.*;
import org.rostik.andrusiv.analyzer.BaseTest;
import org.rostik.andrusiv.analyzer.MemoryAppender;
import org.rostik.andrusiv.analyzer.exception.PathIsNullException;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import java.util.*;

import static org.junit.Assert.*;

public class DiskAnalyzerTest extends BaseTest {

    private static MemoryAppender memoryAppender;
    private static final String LOGGER_NAME = "org.rostik.andrusiv.analyzer.reciever";
    private static final String MSG = "path is not valid: ";

    @Before
    public void beforeTest() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

    }

    @After
    public void afterTest() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    @Test
    public void findPathToFileByMostSCharacterRepeatTest() {
        Optional<String> opt = DiskAnalyzer.findPathToFileByMostSCharacterRepeat(Path.of("test/"));
        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals("test\\4s\\4zssss.txt", opt.get());
    }

    @Test
    public void findPathToFileByMostSCharacterRepeatNotValidInputTest() {
        Optional<String> opt = DiskAnalyzer.findPathToFileByMostSCharacterRepeat(Path.of("bla-bla"));
        Assert.assertTrue(opt.isEmpty());
        assertEquals(1, memoryAppender.search(MSG, Level.INFO).size());
    }


    @Test(expected = PathIsNullException.class)
    public void findPathToFileByMostSCharacterRepeatNullTest() {
        Optional<String> opt = DiskAnalyzer.findPathToFileByMostSCharacterRepeat(null);
    }

    @Test
    public void getFilesSortedBySizeTest() {
        List<String> result = DiskAnalyzer.getFilesSortedBySize(Path.of("test/"));
        assertFalse(result.isEmpty());
        assertEquals(List.of("0pqdsrrrr.txt", "0rrrrr.txt", "4zssss.txt", "3bsss.txt", "2ass.txt"),
                result);
    }

    @Test
    public void getFilesSortedBySizeNotValidInputTest() {
        List<String> result = DiskAnalyzer.getFilesSortedBySize(Path.of("bla-bla"));
        Assert.assertTrue(result.isEmpty());
        assertEquals(1, memoryAppender.search(MSG, Level.INFO).size());
    }

    @Test(expected = PathIsNullException.class)
    public void getFilesSortedBySizeNullTest() {
        List<String> result = DiskAnalyzer.getFilesSortedBySize(null);
    }

    @Test
    public void getAvgFilesSizeTest() {
        OptionalDouble result = DiskAnalyzer.getAvgFilesSize(Path.of("test/"));
        assertTrue(result.isPresent());
        assertEquals(3584.0, result.getAsDouble(), 0.1);
    }

    @Test
    public void getAvgFilesSizeNotValidInputTest() {
        OptionalDouble result = DiskAnalyzer.getAvgFilesSize(Path.of("bla-bla"));
        Assert.assertTrue(result.isEmpty());
        assertEquals(1, memoryAppender.search(MSG, Level.INFO).size());
    }

    @Test(expected = PathIsNullException.class)
    public void getAvgFilesSizeNullTest() {
        OptionalDouble result = DiskAnalyzer.getAvgFilesSize(null);
    }

    @Test
    public void divideByFirstLettersTest() {
        Map<Character, Long> expected = new HashMap<>();
        expected.put('4', 1L);
        expected.put('3', 1L);
        expected.put('2', 1L);
        expected.put('1', 1L);
        expected.put('0', 2L);
        Map<Character, Long> result = DiskAnalyzer.divideByFirstLetters(Path.of("test/"));
        assertFalse(result.isEmpty());
        assertEquals(expected, result);
    }

    @Test
    public void divideByFirstLettersNotValidInputTest() {
        Map<Character, Long> result = DiskAnalyzer.divideByFirstLetters(Path.of("bla-bla"));
        Assert.assertTrue(result.isEmpty());
        assertEquals(1, memoryAppender.search(MSG, Level.INFO).size());
    }

    @Test(expected = PathIsNullException.class)
    public void divideByFirstLettersNullTest() {
        Map<Character, Long> result = DiskAnalyzer.divideByFirstLetters(null);
    }
}