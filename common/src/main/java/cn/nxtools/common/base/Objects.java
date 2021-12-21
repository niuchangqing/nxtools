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
     * @param nullValue     obj参数为空,返回的参数
     * @param <T>           T
     * @return              返回obj or defaultObj
     */
    public static <T> T defaultIfNull(T obj, T nullValue) {
        return obj == null ? nullValue : obj;
    }
}
