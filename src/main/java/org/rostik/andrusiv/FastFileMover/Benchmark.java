package org.rostik.andrusiv.FastFileMover;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.rostik.andrusiv.FastFileMover.FastFileMover.*;

public class Benchmark {
    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }


    @State(Scope.Benchmark)
    public static class BenchmarkState {
        @Param({"D:/2/1.jpg"})
        public String in;

        @Param({"D:/2/2.jpg"})
        public String out;
    }

    @org.openjdk.jmh.annotations.Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void testInputStreamMover(BenchmarkState state) {
        inputStreamMover(state.in, state.out);
    }

    @org.openjdk.jmh.annotations.Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void testInputStreamBufferedMover(BenchmarkState state) {
        inputStreamBufferedMover(state.in, state.out);
    }

    @org.openjdk.jmh.annotations.Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void testFileChannelMover(BenchmarkState state) {
        fileChannelMover(state.in, state.out);
    }

    @org.openjdk.jmh.annotations.Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void testNio2Mover(BenchmarkState state) {
        Nio2Mover(state.in, state.out);
    }
}
