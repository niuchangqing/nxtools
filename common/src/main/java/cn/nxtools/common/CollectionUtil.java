package cn.nxtools.common;

import java.util.Collection;
import java.util.Map;

/**
 * @author niuchangqing
 */
public class CollectionUtil {

    /**
     * 私有化构造方法
     */
    private CollectionUtil(){
    }

    /**
     * 判断集合是否为null
     * <p>
     *     null = true;
     *     [] = false;
     * </p>
     * @param collection        集合参数
     * @return                  返回true或者false
     */
    public static boolean isNull(Collection<?> collection) {
        return collection == null;
    }

    /**
     * 判断集合是否不为null
     * <p>
     *     null = false;
     *     [] = true;
     * </p>
     * @param collection        集合参数
     * @return                  返回true或者false
     */
    public static boolean isNotNull(Collection<?> collection) {
        return collection != null;
    }

    /**
     * 判断集合是否为null或者[]
     * <p>
     *     null = true;
     *     [] = true;
     * </p>
     * @param collection        集合参数
     * @return                  返回true或者false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合不是null和[]
     * <p>
     *     null = false;
     *     [] = false;
     * </p>
     * @param collection        集合参数
     * @return                  返回true或者false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 判断map是否为null
     * <p>
     *     null = true;
     *     {} = false;
     * </p>
     * @param map               map参数
     * @return                  返回true或者false
     */
    public static boolean isNull(Map<?, ?> map) {
        return map == null;
    }

    /**
     * 判断map是否不为null
     * <p>
     *     null = false;
     *     {} = true;
     * </p>
     * @param map               map参数
     * @return                  返回true或者false
     */
    public static boolean isNotNull(Map<?, ?> map) {
        return map != null;
    }

    /**
     * 判断map是否为null或{}
     * <p>
     *     null = true;
     *     {} = true;
     *     {"name":"zhang"} = false;
     * </p>
     * @param map               map参数
     * @return                  返回true或者false
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断map是否不为null和{}
     * <p>
     *     null = false;
     *     {} = false;
     *     {"name":"zhang"} = true;
     * </p>
     * @param map               map参数
     * @return                  返回true或者false
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }
}
