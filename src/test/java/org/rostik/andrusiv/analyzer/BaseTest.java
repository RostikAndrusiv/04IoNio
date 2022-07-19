package org.rostik.andrusiv.analyzer;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.rostik.andrusiv.analyzer.reciever.DiskAnalyzerTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;


public class BaseTest {

    private static final String PATH1 = "test/1s/1as.txt";
    private static final String PATH2 = "test/2s/2ass.txt";
    private static final String PATH3 = "test/3s/3bsss.txt";
    private static final String PATH4 = "test/4s/4zssss.txt";
    private static final String PATH5 = "test/4s/0rrrrr.txt";
    private static final String PATH6 = "test/1s/0pqdsrrrr.txt";

    @BeforeClass
    public static void setup() throws IOException {
        //create paths
        Path p1 = Paths.get(PATH1);
        Path p2 = Paths.get(PATH2);
        Path p3 = Paths.get(PATH3);
        Path p4 = Paths.get(PATH4);
        Path p5 = Paths.get(PATH5);
        Path p6 = Paths.get(PATH6);
        //create directories and files
        List<Path> pathList = List.of(p1, p2, p3, p4, p5, p6);
        pathList.forEach(path -> {
            DiskAnalyzerTest.createDirectories(path);
            DiskAnalyzerTest.createFile(path);
        });
        //setting files sizes
        setFileSize(PATH1, 1024);
        setFileSize(PATH2, 2 * 1024);
        setFileSize(PATH3, 3 * 1024);
        setFileSize(PATH4, 4 * 1024);
        setFileSize(PATH5, 5 * 1024);
        setFileSize(PATH6, 6 * 1024);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        Files.walk(Path.of("test/"))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @SneakyThrows
    protected static Path createFile(Path p) {
        return Files.createFile(p);
    }

    @SneakyThrows
    protected static Path createDirectories(Path p) {
        return Files.createDirectories(p.getParent());
    }

    protected static final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;

    @Before
    public void beforeTest() {
        System.setOut(new PrintStream(baos));
    }

    @After
    public void afterTest() {
        System.setOut(originalOut);
    }

    private static void setFileSize(String pathToFile, int size) {
        final ByteBuffer buf = ByteBuffer.allocate(size);
        final OpenOption[] options = {StandardOpenOption.WRITE, StandardOpenOption.APPEND, StandardOpenOption.SPARSE};
        final Path hugeFile = Paths.get(pathToFile);
        try (final SeekableByteChannel channel = Files.newByteChannel(hugeFile, options)) {
            channel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
