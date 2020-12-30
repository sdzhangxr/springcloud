package com.zhangxr.springcloud.date.oldDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @className test
 * @Description 存在线程安全问题
 * @Author sdzha
 * @Date 2020/12/28 17:42
 * @Version 1.0
 */
public class TestSimpleDateFormat {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    private static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return TestSimpleDateFormat.convert("20201228");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> future = pool.submit(task);
            resultList.add(future);
        }
        for (Future<Date> dateFuture : resultList) {
            System.out.println(dateFuture.get());
        }
        pool.shutdown();
    }

}
