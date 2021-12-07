package cn.nxtools.common;

import cn.nxtools.common.base.Objects;
import cn.nxtools.common.collect.Sets;

import java.util.Set;

/**
 * boolean工具类
 * @author      niuchangqing
 */
public class BooleanUtil {

    /**
     * 表示为true结果的字符串集合
     */
    private static final Set<String> TRUE_SET = Sets.newHashSet("true","1","yes","y","真","对","on");

    /**
     * 是否为true
     * <p>
     *     BooleanUtil.isTrue(true)  = true
     *     BooleanUtil.isTrue(false) = false
     *     BooleanUtil.isTrue(null)  = false
     * </p>
     * @param bool      被检查的参数
     * @return          当值为true返回true其他为false
     */
    public static boolean isTrue(Boolean bool) {
        return Boolean.TRUE.equals(bool);
    }

    /**
     * 是否为false
     * <p>
     *     BooleanUtil.isFalse(false) = true
     *     BooleanUtil.isFalse(true)  = false
     *     BooleanUtil.isFalse(null)  = false
     * </p>
     * @param bool      被检查的参数
     * @return          当值为false返回true 其他返回false
     */
    public static boolean isFalse(Boolean bool) {
        return Boolean.FALSE.equals(bool);
    }

    /**
     * 字符串转boolean
     * @param value     待转换字符串
     * @return          值在 {@link #TRUE_SET}集合中返回true 其他返回false
     */
    public static boolean toBoolean(String value) {
        if (StringUtil.isNotEmpty(value)) {
            value = value.toLowerCase();
            return TRUE_SET.contains(value);
        }
        return false;
    }

    /**
     * boolean转int
     * true=1,false=0
     * @param value     boolean值
     * @return          int
     */
    public static int toInt(boolean value) {
        return value ? 1 : 0;
    }

    /**
     * boolean转long
     * @param value     boolean值
     * @return          long
     */
    public static long toLong(boolean value) {
        return toInt(value);
    }

    /**
     * Long转Boolean, Long为空返回nullValue
     * 非0即为true
     * <p>
     *     BooleanUtil.toBoolean(0L, nullValue)   = false
     *     BooleanUtil.toBoolean(null, nullValue) = defaultValue
     *     BooleanUtil.toBoolean(其他, nullValue)  = true
     * </p>
     * @param value     Long值
     * @param nullValue value为空时,返回的默认值
     * @return          Boolean
     */
    public static Boolean toBoolean(Long value, Boolean nullValue) {
        if (Objects.isNull(value)) {
            return nullValue;
        }
        if (value.compareTo(0L) == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Integer转Boolean, Integer为空返回nullValue
     * 非0即为true
     * <p>
     *     BooleanUtil.toBoolean(0, nullValue)   = false
     *     BooleanUtil.toBoolean(null, nullValue) = nullValue
     *     BooleanUtil.toBoolean(其他, nullValue)  = true
     * </p>
     * @param value         Long值
     * @param defaultValue  value值为空时，返回指定默认值
     * @return              Boolean
     */
    public static Boolean toBoolean(Integer value, Boolean defaultValue) {
        if (Objects.isNull(value)) {
            return defaultValue;
        }
        if (value.compareTo(0) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
