package cn.nxtools.common;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static cn.nxtools.common.LocalDateTimeUtil.YYYY_MM_DD;
import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * jdk8+ LocalDate工具
 * LocalDate只包含日期，不包含时分秒等，格式类似yyyy-MM-dd
 */
public class LocalDateUtil {

    private LocalDateUtil() {}

    /**
     * LocalDate日期转日期格式字符串,默认yyyy-MM-dd格式
     * @param localDate             时间
     * @return                      日期格式字符串
     */
    public static String toString(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(YYYY_MM_DD);
    }

    /**
     * LocalDate日期转日期格式字符串,指定日期字符串格式
     * @param localDate             时间
     * @param formatter             格式化日期格式对象,如:DateTimeFormatter.ofPattern("yyyy-MM-dd");
     * @return                      日期格式字符串
     */
    public static String toString(LocalDate localDate, DateTimeFormatter formatter) {
        if (localDate == null) {
            return null;
        }
        checkNotNull(formatter);
        return localDate.format(formatter);
    }

    /**
     * LocalDate日期转日期格式字符串,指定日期字符串格式
     * @param localDate             时间
     * @param format                格式化日期格式字符串,如:yyyy-MM-dd
     * @return                      日期格式字符串
     */
    public static String toString(LocalDate localDate, String format) {
        if (localDate == null) {
            return null;
        }
        checkNotNull(format);
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 字符串日期转LocalDate
     * @param str                   字符串格式日期
     * @param formatter             日期格式化对象,如:DateTimeFormatter.ofPattern("yyyy-MM-dd");
     * @return                      LocalDate
     */
    public static LocalDate ofString(String str, DateTimeFormatter formatter) {
        if (str == null) {
            return null;
        }
        checkNotNull(formatter);
        return LocalDate.parse(str, formatter);
    }

    /**
     * 字符串日期转LocalDate
     * @param str                   字符串格式日期
     * @param format                日期格式化字符串,如:yyyy-MM-dd
     * @return                      LocalDate
     */
    public static LocalDate ofString(String str, String format) {
        if (str == null) {
            return null;
        }
        checkNotNull(format);
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前日期,默认当前系统时区
     * @return              LocalDate
     */
    public static LocalDate now() {
        return LocalDate.now();
    }

    /**
     * 获取指定时区的当前日期
     * @param zoneId        时区
     * @return              LocalDate
     */
    public static LocalDate now(ZoneId zoneId) {
        return LocalDate.now(zoneId);
    }
}
