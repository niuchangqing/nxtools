package cn.nxtools.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * jdk8+ LocalDateTime工具
 * LocalDateTime包含日期和时间,格式类似yyyy-MM-dd HH:mm:ss
 */
public class LocalDateTimeUtil {

    /**
     * yyyy-mm-dd格式
     * 2021-03-18
     */
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * HH:mm:ss
     * 21:35:31
     */
    public static final DateTimeFormatter HH_MM_SS = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * yyyy-MM-dd HH:mm:ss
     * 2021-03-18 21:35:31
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     * 2021-03-18 21:36:28.760
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * yyyy/MM/dd
     * 2021/03/18
     */
    public static final DateTimeFormatter YYYY_MM_DD_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /**
     * yyyy/MM/dd HH:mm:ss
     * 2021/03/18 21:35:31
     */
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * yyyy/MM/dd HH:mm:ss.SSS
     * 2021/03/18 21:36:28.760
     */
    private static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_SSS_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    private LocalDateTimeUtil() {}

    /**
     * 获取当前时间的毫秒时间戳,默认系统时区
     * @return          毫秒时间戳
     */
    public static long currentTimeMillis() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    /**
     * 获取指定时区当前时间毫秒时间戳
     * @param zoneId            时区
     * @return                  毫秒时间戳
     */
    public static long currentTimeMillis(ZoneId zoneId) {
        return LocalDateTime.now(zoneId).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取当前时间的秒时间戳,默认系统时区
     * @return              秒时间戳
     */
    public static long currentTimeSecond() {
        return currentTimeMillis() / 1000;
    }

    /**
     * 获取指定时区当前时间秒时间戳
     * @param zoneId            时区
     * @return                  秒时间戳
     */
    public static long currentTimeSecond(ZoneId zoneId) {
        return currentTimeMillis(zoneId) / 1000;
    }

    /**
     * 毫秒时间戳转LocalDateTime,默认系统时区
     * @param timestamp         毫秒时间戳
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofMillis(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
    }

    /**
     * 毫秒时间戳转LocalDateTime,指定时区进行转换,默认时间戳时区为当前系统时区
     * @param timestamp         毫秒时间戳
     * @param zoneId            时区
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofMillis(long timestamp, ZoneId zoneId) {
        return ofMillis(timestamp, ZoneId.systemDefault(), zoneId);
    }

    /**
     * 毫秒时间戳转LocalDateTime，指定时区，指定时间戳时区
     * @param timestamp         毫秒时间戳
     * @param timestampZoneId   时间戳时区
     * @param zoneId            时区
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofMillis(long timestamp, ZoneId timestampZoneId, ZoneId zoneId) {
        checkNotNull(timestampZoneId);
        checkNotNull(zoneId);
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = localDateTime.atZone(timestampZoneId);
        //转换对应时区后的时间戳
        long millis = zonedDateTime.withZoneSameInstant(zoneId).toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis),ZoneId.systemDefault());
    }

    /**
     * 秒时间戳转LocalDateTime,默认系统时区
     * @param timestamp         时间戳,单位秒
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofSecond(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 秒时间戳转LocalDateTime,指定时区,默认timestamp的时区为当前系统时区
     * @param timestamp         时间戳,单位秒
     * @param zoneId            时区
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofSecond(long timestamp, ZoneId zoneId) {
        return ofSecond(timestamp, ZoneId.systemDefault(), zoneId);
    }

    /**
     * 秒时间戳转LocalDateTime,指定时区,指定时间戳的时区
     * @param timestamp                 时间戳
     * @param timestampZoneId           时间戳时区
     * @param zoneId                    转换后的LocalDateTime时区
     * @return                          LocalDateTime
     */
    public static LocalDateTime ofSecond(long timestamp, ZoneId timestampZoneId, ZoneId zoneId) {
        checkNotNull(timestampZoneId);
        checkNotNull(zoneId);
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        ZonedDateTime zonedDateTime = localDateTime.atZone(timestampZoneId);
        //转换对应时区后的时间戳
        long millis = zonedDateTime.withZoneSameInstant(zoneId).toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis),ZoneId.systemDefault());
    }

    /**
     * LocalDateTime日期转字符串,默认yyyy-MM-dd HH:mm:ss格式
     * @param localDateTime     时间
     * @return                  时间格式化后的字符串
     */
    public static String toString(LocalDateTime localDateTime) {
        if (isNull(localDateTime)) {
            return null;
        }
        return localDateTime.format(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * LocalDateTime日期转字符串,指定字符串日期格式
     * @param localDateTime     时间
     * @param formatter         字符串日期格式对象,如:DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
     * @return                  时间格式化后的字符串
     */
    public static String toString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        if (isNull(localDateTime)) {
            return null;
        }
        if (isNull(formatter)) {
            formatter = YYYY_MM_DD_HH_MM_SS;
        }
        return localDateTime.format(formatter);
    }

    /**
     * LocalDateTime转日期格式字符串,指定字符串日期格式
     * @param localDateTime     时间
     * @param format            日期格式化字符串,如:yyyy-MM-dd HH:mm:ss
     * @return                  时间格式化后的字符串
     */
    public static String toString(LocalDateTime localDateTime, String format) {
        if (isNull(localDateTime)) {
            return null;
        }
        checkNotNull(format);
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 日期格式字符串转LocalDateTime
     * @param str               日期字符串,如:yyyy-MM-dd HH:mm:ss
     * @param formatter         字符串日期格式对象,如:DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofString(String str, DateTimeFormatter formatter) {
        if (isNull(str)) {
            return null;
        }
        checkNotNull(formatter);
        return LocalDateTime.parse(str, formatter);
    }

    /**
     * 日期格式字符串转LocalDateTime
     * @param str               日期字符串,如:yyyy-MM-dd HH:mm:ss
     * @param format            字符串日期格式,如:yyyy-MM-dd HH:mm:ss
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofString(String str, String format) {
        if (isNull(str)) {
            return null;
        }
        checkNotNull(format);
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前时间,默认时区为当前系统时区
     * @return                  LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 获取指定时区的当前时间
     * @param zoneId            时区
     * @return                  LocalDateTime
     */
    public static LocalDateTime now(ZoneId zoneId) {
        return LocalDateTime.now(zoneId);
    }

    /**
     * LocalDateTime转Date,默认时区为当前系统时区,LocalDateTime的时区默认为当前系统时区
     * @param localDateTime       LocalDateTime
     * @return                    Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (isNull(localDateTime)) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime转Date,指定时区,默认LocalDateTime时间为当前系统时区时间
     * @param localDateTime         LocalDateTime
     * @param zoneId                时区
     * @return                      Date
     */
    public static Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
        if (isNull(localDateTime)) {
            return null;
        }
        return toDate(localDateTime, ZoneId.systemDefault(), zoneId);
    }

    /**
     * LocalDateTime转Date,指定时区,指定LocalDateTime的时区进行转换
     * @param localDateTime                 LocalDateTime
     * @param dateTimeZoneId                localDateTime的时区
     * @param zoneId                        转换指定date的时区
     * @return                              Date
     */
    public static Date toDate(LocalDateTime localDateTime, ZoneId dateTimeZoneId, ZoneId zoneId) {
        if (isNull(localDateTime)) {
            return null;
        }
        checkNotNull(dateTimeZoneId);
        checkNotNull(zoneId);
        ZonedDateTime zonedDateTime = localDateTime.atZone(dateTimeZoneId);
        return new Date(zonedDateTime.withZoneSameInstant(zoneId).toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * Date转LocalDateTime,默认为当前系统时区
     * @param date              date
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofDate(Date date) {
        if (isNull(date)) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
    }

    /**
     * Date转LocalDateTime,指定时区
     * @param date              Date
     * @param zoneId            时区
     * @return                  LocalDateTime
     */
    public static LocalDateTime ofDate(Date date, ZoneId zoneId) {
        if (isNull(date)) {
            return null;
        }
        checkNotNull(zoneId);
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }
}