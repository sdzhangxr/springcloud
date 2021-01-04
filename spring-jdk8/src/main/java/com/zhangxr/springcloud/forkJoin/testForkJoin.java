package com.zhangxr.springcloud.forkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @className testForkJoin
 * @Description TODO
 * @Author sdzha
 * @Date 2020/12/31 11:13
 * @Version 1.0
 */
public class testForkJoin {
    /**
     * java1.7
     */
    @Test
    public void test() {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 900000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间：" + Duration.between(start,end).toMillis());

    }

    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i <= 900000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间：" + Duration.between(start,end).toMillis());
    }

    /**
     *                  Fork/Join     普通for循环
     *   90000000         160ms          109ms
     *   900000000        541ms          783ms
     *   9000000000       4863ms         5710ms
     *   90000000000      34857ms        38514ms
     */

    /**
     *  java1.8 对 Fork/Join的处理
     */
    @Test
    public void test3(){
        Instant start = Instant.now();

        long reduce = LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);

        Instant end = Instant.now();
        System.out.println("耗费时间：" + Duration.between(start,end).toMillis());
    }
}
