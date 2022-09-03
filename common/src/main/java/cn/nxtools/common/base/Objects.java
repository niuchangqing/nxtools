package cn.nxtools.common.base;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author niuchangqing
 * object相关工具方法
 */
public final class Objects {

    /**
     * 对象是否为空
     * @param obj   对象
     * @return      true or false
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 对象非空
     * @param obj       对象
     * @return          true or false
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    /**
     * 对象为空即返回默认值,否则返回当前对象
     * <pre>
     *  Example:
     *      String object = null;
     *      String s2 = Objects.defaultIfNull(object, StringUtil.EMPTY);
     * </pre>
     * @param obj           指定对象
     * @param defaultValue  obj参数为空,返回的参数
     * @param <T>           T
     * @return              返回obj or defaultValue
     */
    public static <T> T defaultIfNull(final T obj, final T defaultValue) {
        return obj == null ? defaultValue : obj;
    }

    /**
     * 对象为空即返回默认值,否则返回当前对象
     * <pre>{@code
     *  // 实例:
     *  String object = null;
     *  String s1 = Objects.defaultIfNull(object, () -> StringUtil.EMPTY);
     * }
     * </pre>
     * @param obj           指定对象
     * @param supplier      懒加载函数
     * @param <T>           参数类型
     * @return              返回obj or defaultValue
     * @since 1.0.7
     */
    public static <T> T defaultIfNull(final T obj, final Supplier<? extends T> supplier) {
        return obj == null ? supplier.get() : obj;
    }

    /**
     * 比较俩个参数是否一样 <br>
     * o1不等于null, 调用 {@link Object#equals(Object)}
     * @param o1            参数1
     * @param o2            参数2
     * @return              true or false
     * @since               1.0.3
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        return o1 != null && o1.equals(o2);
    }

    /**
     * 参数为空返回null，非空执行用户自定义处理逻辑
     * <p>
     *     {@code User user = null;}<br>
     *     {@code String name = Objects.map(user, u -> u.getName());}<br>
     *     {@code System.out.println(name);}<br>
     *     输出: null
     * </p>
     * @param obj               参数
     * @param mapper            参数非空自定义执行逻辑
     * @param <T>               参数T
     * @param <U>               返回结果U
     * @return                  返回结果
     * @since 1.0.5
     */
    public static <T, U> U map(T obj, Function<? super T, ? extends U> mapper) {
        return map(obj, mapper, null);
    }

    /**
     * 参数为空返回自定义的value，非空执行用户自定义处理逻辑
     * <p>
     *     {@code User user = new User();}<br>
     *     {@code user.setName("lisi");}<br>
     *     {@code String name = Objects.map(user, u -> u.getName(), "zhangsan")}<br>
     *     {@code System.out.println(name);}<br>
     *     输出: lisi
     * </p>
     * @param obj               参数
     * @param mapper            参数非空自定义执行逻辑
     * @param defaultValue      参数为空默认返回的值
     * @param <T>               参数T
     * @param <U>               返回U
     * @return                  返回值
     * @since 1.0.5
     */
    public static <T, U> U map(T obj, Function<? super T, ? extends U> mapper, U defaultValue) {
        Preconditions.checkNotNull(mapper, "mapper must not be null");
        if (obj == null) {
            return defaultValue;
        }
        return mapper.apply(obj);
    }

    /**
     * 参数非空时,执行action逻辑, 为空不执行
     * <pre>{@code
     * // 实例:
     * Long millis = 1662205523073L;
     * User user = new User();
     * Long millis = 1662205523073L;
     * Objects.nonNullExec(user, u -> u.setCreateTime(LocalDateTimeUtil.ofMillis(millis)));
     * int result = user.getCreateTime().compareTo(LocalDateTimeUtil.ofMillis(millis));
     * // 结果: 0
     * }</pre>
     * @param obj           目标参数
     * @param action        执行操作
     * @param <T>           参数范型
     * @since 1.0.7
     */
    public static <T> void nonNullExec(T obj, Consumer<? super T> action) {
        Preconditions.checkNotNull(action);
        if (isNull(obj)) {
            return;
        }
        action.accept(obj);
    }
}
