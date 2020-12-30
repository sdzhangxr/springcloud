package com.zhangxr.springcloud.date.oldDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @className TestLocalDate
 * @Description JDK1.8之后，解决时间的线程安全问题
 * @Author sdzha
 * @Date 2020/12/29 9:38
 * @Version 1.0
 */
public class TestLocalDate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20201229",dtf);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<LocalDate> submit = pool.submit(task);
            resultList.add(submit);
        }
        for (Future<LocalDate> future : resultList) {
            System.out.println(future.get());
        }
    }
}
