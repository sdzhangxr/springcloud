package com.zhangxr.springcloud.date.newdate;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @className TestLocalDateTime
 * @Description TODO
 * @Author sdzha
 * @Date 2020/12/29 10:01
 * @Version 1.0
 */
public class TestLocalDateTime {

    /**
     * 带时区的时间：
     */
    @Test
    public void testZone(){
        Set<String> ids = ZoneId.getAvailableZoneIds();
//        ids.forEach(System.out::println);

        //获取时间时指定时区
        LocalDateTime shanghai = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(shanghai);
    }

    /**
     * DateTimeFormatter : 格式化时间日期
     */
    @Test
    public void testDateTimeFormatter(){
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME; //2020-12-29T14:44:04.835
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;//2020-12-29T14:46:06.812

//        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE; //2020-12-29
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;//2020-12-29

//        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_TIME;//14:46:47.15
        DateTimeFormatter dtf = DateTimeFormatter.ISO_TIME;//14:47:17.975

        LocalDateTime now = LocalDateTime.now();
        String format = now.format(dtf);
        System.out.println(format);
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;//报错
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_OFFSET_DATE;//报错

        //自定义格式
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format1 = dateTimeFormatter.format(now);
        TemporalAccessor parse = dateTimeFormatter.parse(format1);
        System.out.println(format1);
        System.out.println(parse);

    }

    /**
     * TemporalAdjuster/TemporalAdjusters : 时间矫正
     */
    @Test
    public void testTemporalAdjusters(){
        System.out.println("------当前时间------");
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        System.out.println("------指定月中的天为10------");
        LocalDateTime ldt2 = ldt1.withDayOfMonth(10);
        System.out.println(ldt2);

        System.out.println("------获取下一个周日的时间------");
        LocalDateTime with1 = ldt1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(with1);

        System.out.println("------下一个工作日------");
        LocalDateTime time = ldt1.with((l) -> {
            LocalDateTime ldt3 = (LocalDateTime) l;
            DayOfWeek dow = ldt3.getDayOfWeek();
            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt3.plusDays(3);
            }else if (dow.equals(DayOfWeek.SATURDAY)){
                return ldt3.plusDays(2);
            }else {
                return ldt3.plusDays(1);
            }
        });
        System.out.println(time);

    }

    /**
     * Period : 两个日期的间隔
     */
    @Test
    public void testPeriod(){
        LocalDate localDate1 = LocalDate.of(2019,6,12);
        LocalDate localDate2 = LocalDate.now();
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /**
     * Duration : 计算两个时间的间隔
     */
    @Test
    public void testDuration() throws InterruptedException {
        Instant instant1 = Instant.now();
        Thread.sleep(1000);
        Instant instant2 = Instant.now();
        Duration duration1 = Duration.between(instant1, instant2);
        System.out.println(duration1.toMillis());

        LocalTime localTime1 = LocalTime.now();
        Thread.sleep(1000);
        LocalTime localTime2 = LocalTime.now();
        Duration duration2 = Duration.between(localTime1, localTime2);
        System.out.println(duration2.toMillis());
    }

    /**
     * Instant 时间戳
     */
    @Test
    public void testInstant(){
        Instant now = Instant.now(); //默认获取UTC时区
        System.out.println(now);

        OffsetDateTime odt = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println("-------获取1970.1.1 00:00:00至今的毫秒-------");
        System.out.println(now.toEpochMilli());

        System.out.println("-------获取1970.1.1 00:00:00 加1000毫秒-------");
        Instant instant = Instant.ofEpochSecond(1000);
        System.out.println(instant);
    }


    /**
     * LocalDate 时间
     * LocalTime 日期
     * LocalDateTime 时间日期
     */
    @Test
    public void testLocalDateTime(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime of = LocalDateTime.of(2020, 12, 29, 10, 6, 20);
        System.out.println(of);

        LocalDateTime plusYears = ldt.plusYears(2);
        System.out.println("加两年：" + plusYears);

        LocalDateTime minusMonths = ldt.minusMonths(2);
        System.out.println("减两个月：" + minusMonths);

        int monthValue = minusMonths.getMonthValue();
        System.out.println(monthValue);
    }
}
