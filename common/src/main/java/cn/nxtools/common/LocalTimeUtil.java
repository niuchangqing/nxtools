package cn.nxtools.common;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static cn.nxtools.common.LocalDateTimeUtil.HH_MM_SS;
import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * jdk8+ LocalTime工具类
 * LocalTime只包含时分秒等信息，不包含年月日
 * 格式类似 HH:mm:ss
 */
public class LocalTimeUtil {

    private LocalTimeUtil() {}

    /**
     * time转字符串,默认HH:mm:ss格式
     * @param localTime             time
     * @return                      时间格式日期字符串
     */
    public static String toString(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return localTime.format(HH_MM_SS);
    }

    /**
     * time转字符串,指定时间字符串格式
     * @param localTime             time
     * @param formatter             时间格式对象,如:DateTimeFormatter.ofPattern("HH:mm:ss");
     * @return                      时间格式日期字符串
     */
    public static String toString(LocalTime localTime, DateTimeFormatter formatter) {
        if (localTime == null) {
            return null;
        }
        checkNotNull(formatter);
        return localTime.format(formatter);
    }

    /**
     * time转字符串,指定时间字符串格式
     * @param localTime             time
     * @param format                时间格式字符串,如: HH:mm:ss
     * @return                      时间格式日期字符串
     */
    public static String toString(LocalTime localTime, String format) {
        if (localTime == null) {
            return null;
        }
        checkNotNull(format);
        return localTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * time字符串转LocalTime
     * @param str                   时间字符串
     * @param formatter             时间格式化对象,如:DateTimeFormatter.ofPattern("HH:mm:ss");
     * @return                      LocalTime
     */
    public static LocalTime ofString(String str, DateTimeFormatter formatter) {
        if (str == null) {
            return null;
        }
        checkNotNull(formatter);
        return LocalTime.parse(str,formatter);
    }

    /**
     * time字符串转LocalTime
      * @param str                  时间字符串
     * @param format                时间格式化字符串,如: HH:mm:ss
     * @return                      LocalTime
     */
    public static LocalTime ofString(String str, String format) {
        if (str == null) {
            return null;
        }
        checkNotNull(format);
        return LocalTime.parse(str, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前时间,默认系统时间时区
     * @return                  LocalTime
     */
    public static LocalTime now() {
        return LocalTime.now();
    }

    /**
     * 获取指定时区的当前时间
     * @param zoneId            时区
     * @return                  LocalTime
     */
    public static LocalTime now(ZoneId zoneId) {
        return LocalTime.now(zoneId);
    }
}
