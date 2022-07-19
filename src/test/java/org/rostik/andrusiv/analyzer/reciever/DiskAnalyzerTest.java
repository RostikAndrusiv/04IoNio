package org.rostik.andrusiv.analyzer.reciever;

import org.junit.*;
import org.rostik.andrusiv.analyzer.BaseTest;

import java.nio.file.*;
import java.util.*;

import static org.junit.Assert.*;

public class DiskAnalyzerTest extends BaseTest {


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
        Assert.assertTrue(baos.toString().contains("path is not valid:"));
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
        Assert.assertTrue(baos.toString().contains("path is not valid:"));
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
        Assert.assertTrue(baos.toString().contains("path is not valid:"));
    }

    @Test
    public void divideByFirstLettersTest1() {
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
    public void divideByFirstLettersNotValidInputTest1() {
        Map<Character, Long> result = DiskAnalyzer.divideByFirstLetters(Path.of("bla-bla"));
        Assert.assertTrue(result.isEmpty());
        Assert.assertTrue(baos.toString().contains("path is not valid:"));
    }
}