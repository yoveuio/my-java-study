package org.example.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author yoveuio
 * @version 1.0
 * @className Main
 * @description 基准测试
 * @date 2021/3/1 12:45
 */
public class Main {
    static public class HelloWorld {
        @Benchmark
        public void m() {

        }
    }

    static public class HelloworldRunner {

        public static void main(String[] args) throws RunnerException {
            Options opt = new OptionsBuilder()
                    .include(HelloWorld.class.getSimpleName())
                    .exclude("Pref")
                    .warmupIterations(10)
                    .measurementIterations(10)
                    .forks(3)
                    .build();

            new Runner(opt).run();
        }
    }
}
