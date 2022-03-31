package cn.nxtools.common.base;

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
     * @param obj           指定对象
     * @param defaultValue  obj参数为空,返回的参数
     * @param <T>           T
     * @return              返回obj or defaultValue
     */
    public static <T> T defaultIfNull(T obj, T defaultValue) {
        return obj == null ? defaultValue : obj;
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
}
