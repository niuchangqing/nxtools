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
}
