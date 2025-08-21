package dev.lpa;

import java.util.Arrays;
import java.util.Random;

/**
 * ParallelStreamBenchmark demonstrates the performance difference between
 * serial and parallel stream processing for calculating averages on large datasets.
 * 
 * This benchmark compares the execution time of:
 * - Sequential stream processing (single-threaded)
 * - Parallel stream processing (multi-threaded, utilizing available CPU cores)
 */
public class ParallelStreamBenchmark {
    
    private static final int DEFAULT_ARRAY_SIZE = Integer.MAX_VALUE / 4;
    private static final int DEFAULT_ITERATIONS = 10;
    private static final long RANDOM_SEED = 42; // For reproducible results
    
    private final long[] testData;
    private final int iterations;
    
    /**
     * Creates a benchmark with default parameters
     */
    public ParallelStreamBenchmark() {
        this(DEFAULT_ARRAY_SIZE, DEFAULT_ITERATIONS);
    }
    
    /**
     * Creates a benchmark with custom parameters
     * @param arraySize Size of the test array
     * @param iterations Number of benchmark iterations to run
     */
    public ParallelStreamBenchmark(int arraySize, int iterations) {
        this.iterations = iterations;
        this.testData = generateTestData(arraySize);
        
        System.out.printf("Benchmark Configuration:%n");
        System.out.printf("- Array size: %,d elements%n", arraySize);
        System.out.printf("- Iterations: %d%n", iterations);
        System.out.printf("- Available processors: %d%n", Runtime.getRuntime().availableProcessors());
        System.out.println("- Test data: Random long values");
        System.out.println();
    }
    
    /**
     * Generates test data array with random long values
     */
    private long[] generateTestData(int size) {
        System.out.println("Generating test data...");
        return new Random(RANDOM_SEED).longs(size, 1, size).toArray();
    }
    
    /**
     * Runs the benchmark comparing serial vs parallel stream performance
     */
    public BenchmarkResult runBenchmark() {
        System.out.println("Running benchmark...");
        
        long totalSerialTime = 0;
        long totalParallelTime = 0;
        double serialAverage = 0;
        double parallelAverage = 0;
        
        for (int i = 0; i < iterations; i++) {
            System.out.printf("Iteration %d/%d%n", i + 1, iterations);
            
            // Measure serial stream performance
            long startTime = System.nanoTime();
            serialAverage = Arrays.stream(testData).average().orElseThrow();
            long serialTime = System.nanoTime() - startTime;
            totalSerialTime += serialTime;
            
            // Measure parallel stream performance
            startTime = System.nanoTime();
            parallelAverage = Arrays.stream(testData).parallel().average().orElseThrow();
            long parallelTime = System.nanoTime() - startTime;
            totalParallelTime += parallelTime;
            
            System.out.printf("  Serial: %.2f ms, Parallel: %.2f ms%n", 
                            serialTime / 1_000_000.0, parallelTime / 1_000_000.0);
        }
        
        return new BenchmarkResult(totalSerialTime, totalParallelTime, iterations, 
                                 serialAverage, parallelAverage);
    }
    
    /**
     * Represents the results of a benchmark run
     */
    public static class BenchmarkResult {
        private final long avgSerialTimeNanos;
        private final long avgParallelTimeNanos;
        private final double serialAverage;
        private final double parallelAverage;
        private final int iterations;
        
        public BenchmarkResult(long totalSerialTime, long totalParallelTime, 
                             int iterations, double serialAverage, double parallelAverage) {
            this.avgSerialTimeNanos = totalSerialTime / iterations;
            this.avgParallelTimeNanos = totalParallelTime / iterations;
            this.iterations = iterations;
            this.serialAverage = serialAverage;
            this.parallelAverage = parallelAverage;
        }
        
        public void printResults() {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("BENCHMARK RESULTS");
            System.out.println("=".repeat(60));
            
            System.out.printf("Calculated averages (verification):%n");
            System.out.printf("- Serial result:   %.6f%n", serialAverage);
            System.out.printf("- Parallel result: %.6f%n", parallelAverage);
            System.out.printf("- Results match:   %s%n", 
                            Math.abs(serialAverage - parallelAverage) < 0.000001 ? "✓ Yes" : "✗ No");
            System.out.println();
            
            System.out.printf("Performance comparison (average over %d iterations):%n", iterations);
            System.out.printf("- Serial processing:   %,d ns (%.2f ms)%n", 
                            avgSerialTimeNanos, avgSerialTimeNanos / 1_000_000.0);
            System.out.printf("- Parallel processing: %,d ns (%.2f ms)%n", 
                            avgParallelTimeNanos, avgParallelTimeNanos / 1_000_000.0);
            
            long timeDifference = avgSerialTimeNanos - avgParallelTimeNanos;
            double speedupFactor = (double) avgSerialTimeNanos / avgParallelTimeNanos;
            
            if (timeDifference > 0) {
                System.out.printf("- Performance gain:    %,d ns (%.2f ms)%n", 
                                timeDifference, timeDifference / 1_000_000.0);
                System.out.printf("- Speedup factor:      %.2fx faster%n", speedupFactor);
                System.out.printf("- Performance improvement: %.1f%%%n", 
                                ((speedupFactor - 1) * 100));
            } else {
                System.out.printf("- Performance loss:    %,d ns (%.2f ms)%n", 
                                Math.abs(timeDifference), Math.abs(timeDifference) / 1_000_000.0);
                System.out.printf("- Slowdown factor:     %.2fx slower%n", 1.0 / speedupFactor);
            }
            
            System.out.println("=".repeat(60));
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Parallel Stream Performance Benchmark");
        System.out.println("=====================================");
        System.out.println("This program compares the performance of serial vs parallel");
        System.out.println("stream processing for calculating averages on large datasets.");
        System.out.println();
        
        ParallelStreamBenchmark benchmark = new ParallelStreamBenchmark();
        BenchmarkResult result = benchmark.runBenchmark();
        result.printResults();
        
        System.out.println("\nNote: Parallel streams are most effective for:");
        System.out.println("- Large datasets");
        System.out.println("- CPU-intensive operations");
        System.out.println("- Systems with multiple CPU cores");
        System.out.println("- Operations that can be easily parallelized");
    }
}
