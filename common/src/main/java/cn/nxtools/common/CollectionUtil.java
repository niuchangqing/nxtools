package cn.nxtools.common;

import cn.nxtools.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

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
     *     null = true;<br>
     *     [] = false;<br>
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
     *     null = false;<br>
     *     [] = true;<br>
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
     *     null = true;<br>
     *     [] = true;<br>
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
     *     null = false;<br>
     *     [] = false;<br>
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
     *     null = true;<br>
     *     {} = false;<br>
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
     *     null = false;<br>
     *     {} = true;<br>
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
     *     null = true;<br>
     *     {} = true;<br>
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
     *     null = false;<br>
     *     {} = false;<br>
     *     {"name":"zhang"} = true;
     * </p>
     * @param map               map参数
     * @return                  返回true或者false
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 集合转Map, 指定集合对象中的字段作为key或value <br>
     * <pre>
     *     {@code List<User> list = Lists.newArrayList();}
     *     {@code User user = new User();}
     *     {@code user.setId(1L);}
     *     {@code list.add(user);}
     *     {@code Map<Long, User> map = CollectionUtil.toMap(list, User::getId, v -> v);}
     * </pre>
     * @param iterable          集合
     * @param key               map的key
     * @param value             map的value
     * @param <T>               T
     * @param <K>               K
     * @param <V>               V
     * @return                  转换后的结果Map
     * @since 1.0.4
     */
    public static <T, K, V> Map<K, V> toMap(Iterable<T> iterable, Function<T, K> key, Function<T, V> value) {
        return toMap(Maps.newHashMap(), iterable, key, value);
    }

    /**
     * 集合转Map, 指定集合对象中的字段作为key或value <br>
     * 如果map为空，会调用Maps.newHashMap();方法初始化map
     * <pre>
     *     {@code List<User> list = Lists.newArrayList();
     *     User user = new User();
     *     user.setId(1L);
     *     list.add(user);
     *     Map<Long, User> map = Maps.newHashMap();
     *     map = CollectionUtil.toMap(map, list, k -> k.getId(), v -> v);}
     * </pre>
     * @param map               转换的Map
     * @param iterable          集合
     * @param key               key
     * @param value             value
     * @param <T>               T
     * @param <K>               K
     * @param <V>               V
     * @return                  转换后的结果Map
     * @since 1.0.4
     */
    public static <T, K, V> Map<K, V> toMap(Map<K, V> map, Iterable<T> iterable, Function<T, K> key, Function<T, V> value) {
        if (iterable == null) {
            return map;
        }
        if (map == null) {
            map = Maps.newHashMap();
        }
        for (T t : iterable) {
            if (t == null) {
                map.put(null, null);
            } else {
                map.put(key.apply(t), value.apply(t));
            }
        }
        return map;
    }
}
