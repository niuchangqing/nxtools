package cn.nxtools.common;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static cn.nxtools.common.LocalDateTimeUtil.ofDate;
import static cn.nxtools.common.StringUtil.isEmpty;
import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * Date工具类
 */
public class DateUtil {
    private DateUtil() {}

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String HH_MM_SS = "HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    public static final String YYYY_MM_DD_FORMAT = "yyyy/MM/dd";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 日期时间转字符串,默认yyyy-MM-dd HH:mm:ss格式
     * @param date              日期
     * @return                  日期时间字符串
     */
    public static String toString(Date date) {
        return toString(date,YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 日期时间转字符串
     * @param date          日期
     * @param format        字符串日期格式
     * @return              日期时间字符串
     */
    public static String toString(Date date, String format) {
        LocalDateTime localDateTime = ofDate(date);
        if (isNull(localDateTime)) {
            return null;
        }
        return LocalDateTimeUtil.toString(localDateTime, format);
    }

    /**
     * 字符串转日期格式
     * @param dateStr           日期时间字符串
     * @param format            日期格式化字符串
     * @return                  日期
     */
    public static Date of(String dateStr , String format){
        if (isEmpty(dateStr)) {
            return null;
        }
        checkNotNull(format);
        LocalDateTime localDateTime = LocalDateTimeUtil.ofString(dateStr, format);
        Date date = LocalDateTimeUtil.toDate(localDateTime);
        return date;
    }

    /**
     * 根据指定日期新增/减少年数后返回一个新的日期
     * @param date              日期,不能为空
     * @param years             增加/减少的数量
     * @return                  新的时间
     */
    public static Date addYears(final Date date, final int years) {
        return add(date, Calendar.YEAR, years);
    }

    /**
     * 根据指定日期新增/减少月数后返回一个新的日期
     * @param date              日期,不能为空
     * @param months            增加/减少的数量
     * @return                  新的时间
     */
    public static Date addMonths(final Date date, final int months) {
        return add(date, Calendar.MONTH, months);
    }

    /**
     * 根据指定日期新增/减少多少周后返回一个新的日期
     * @param date              日期,不能为空
     * @param weeks             增加/减少的数量
     * @return                  新的时间
     */
    public static Date addWeeks(final Date date, final int weeks) {
        return add(date, Calendar.WEEK_OF_YEAR, weeks);
    }

    /**
     * 根据指定日期新增/减少多少天后返回一个新的日期
     * @param date              日期,不能为空
     * @param days              增加/减少的数量
     * @return                  新的时间
     */
    public static Date addDays(final Date date, final int days) {
        return add(date, Calendar.DAY_OF_MONTH, days);
    }

    /**
     * 根据指定日期新增/减少多少小时后返回一个新的日期
     * @param date              日期,不能为空
     * @param hours             增加/减少的数量
     * @return                  新的时间
     */
    public static Date addHours(final Date date, final int hours) {
        return add(date, Calendar.HOUR_OF_DAY, hours);
    }

    /**
     * 根据指定日期新增/减少多少分钟后返回一个新的日期
     * @param date              日期,不能为空
     * @param minutes           增加/减少的数量
     * @return                  新的时间
     */
    public static Date addMinutes(final Date date, final int minutes) {
        return add(date, Calendar.MINUTE, minutes);
    }

    /**
     * 根据指定日期新增/减少多少秒后返回一个新的日期
     * @param date              日期,不能为空
     * @param seconds           增加/减少的数量
     * @return                  新的时间
     */
    public static Date addSeconds(final Date date, final int seconds) {
        return add(date, Calendar.SECOND, seconds);
    }

    /**
     * 根据指定日期新增/减少多少毫秒后返回一个新的日期
     * @param date              日期,不能为空
     * @param milliseconds      增加/减少的数量
     * @return                  新的时间
     */
    public static Date addMilliseconds(final Date date, final int milliseconds) {
        return add(date, Calendar.MILLISECOND, milliseconds);
    }

    /**
     * 对指定日期进行增加减少操作
     * @param date                  日期
     * @param calendarField         增加/减少的类型，年,月,日,时等 {@link Calendar#HOUR}
     * @param param                 增加/减少的数量，减少为负数
     * @return                      返回增加/减少后的时间
     */
    public static Date add(final Date date, final int calendarField, final int param) {
        checkNotNull(date);
        final Calendar c = toCalendar(date);
        c.add(calendarField, param);
        return c.getTime();
    }

    /**
     * date转换为Calendar
     * @param date                  日期
     * @return                      Calendar
     */
    public static Calendar toCalendar(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * date转换为Calendar时指定时区
     * @param date                  日期
     * @param timeZone              时区
     * @return                      Calendar
     */
    public static Calendar toCalendar(final Date date, final TimeZone timeZone) {
        final Calendar c = Calendar.getInstance(timeZone);
        c.setTime(date);
        return c;
    }

    /**
     * 获取指定天的开始时间, 如: 2021-12-07 00:00:00.000
     * @param date                  日期
     * @return                      指定日期的一天开始时间
     */
    public static Date beginOfDay(final Date date) {
        LocalDateTime dateTime = LocalDateTimeUtil.beginOfDay(ofDate(date));
        return LocalDateTimeUtil.toDate(dateTime);
    }

    /**
     * 获取指定天的结束时间, 如: 2021-12-07 23:59:59.999
     * @param date                  日期
     * @return                      指定日期的一天结束时间
     */
    public static Date endOfDay(final Date date) {
        LocalDateTime dateTime = LocalDateTimeUtil.endOfDay(ofDate(date));
        return LocalDateTimeUtil.toDate(dateTime);
    }
}
