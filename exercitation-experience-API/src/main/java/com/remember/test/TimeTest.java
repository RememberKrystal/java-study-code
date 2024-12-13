package com.remember.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/17 16:17
 * @Description : 判断一个时间是否在某个时间段内
 */
public class TimeTest {
    public static void main(String[] args) {
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 给定的两个时间字符串
        String timeStr1 = "2024-05-30 20:15:07";
        String timeStr2 = "2024-05-27 15:15:07";

        // 将字符串转换为LocalDateTime对象
        LocalDateTime time1 = LocalDateTime.parse(timeStr1, formatter);
        LocalDateTime time2 = LocalDateTime.parse(timeStr2, formatter);

        // 检查时间是否在13:00:00到16:00:00之间
        boolean isTime1InRange = isTimeInRange(time1);
        boolean isTime2InRange = isTimeInRange(time2);

        // 输出结果
        System.out.println("Time 1 in range: " + isTime1InRange);
        System.out.println("Time 2 in range: " + isTime2InRange);
    }

    private static boolean isTimeInRange(LocalDateTime time) {
        // 定义开始和结束时间
        LocalDateTime startTime = time.withHour(13).withMinute(0).withSecond(0);
        LocalDateTime endTime = time.withHour(16).withMinute(0).withSecond(0);

        // 判断时间是否在范围内
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
}
