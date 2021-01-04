package com.zhangxr.springcloud.forkJoin;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @className ForkJoinCalculate
 * @Description Fork/Join java1.7案例
 * @Author sdzha
 * @Date 2020/12/31 11:15
 * @Version 1.0
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end-start <= THRESHOLD){
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
