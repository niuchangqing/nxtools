package cn.nxtools.common;

import cn.nxtools.common.base.Objects;
import cn.nxtools.common.collect.Lists;
import cn.nxtools.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import static cn.nxtools.common.base.Preconditions.checkState;
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

    /**
     * 当参数集合为null时返回默认值, 不为null时返回目标参数
     * <pre>{@code
     * // 实例:
     * HashSet<Integer> set = null;
     * HashSet<Integer> set1 = CollectionUtil.defaultIfNull(set, () -> new HashSet<>());
     * }
     * </pre>
     * @param collection            目标参数
     * @param supplier              默认值, 懒加载函数
     * @param <T>                   集合类型
     * @param <E>                   集合中的元素类型
     * @return                      null时返回defaultCollection, 否则返回collection
     * @see Objects#defaultIfNull(Object, Supplier)
     * @since 1.0.7
     */
    public static <T extends Collection<E>, E> T defaultIfNull(final T collection, final Supplier<? extends T> supplier) {
        return isNull(collection) ? supplier.get() : collection;
    }

    /**
     * 当参数集合为null或[]时返回默认值, 否则返回目标参数
     * <pre>{@code
     * // 实例:
     * HashSet<String> hashSet = Sets.newHashSet();
     * HashSet<String> objects = CollectionUtil.defaultIfEmpty(hashSet, () -> Sets.newHashSet("1"));
     * }
     * </pre>
     * @param collection            目标参数
     * @param supplier              默认值, 懒加载函数
     * @param <T>                   集合类型
     * @param <E>                   集合元素类型
     * @return                      null或[]时返回defaultCollection, 否则返回collection
     * @since 1.0.7
     */
    public static <T extends Collection<E>, E>  T defaultIfEmpty(final T collection, final Supplier<? extends T> supplier) {
        return isEmpty(collection) ? supplier.get() : collection;
    }

    /**
     * 当map为null时返回默认值, 否则返回目标参数
     * <pre>{@code
     * HashMap<String, Integer> map = null;
     * HashMap<String, Integer> map1 = CollectionUtil.defaultIfNull(map, () -> Maps.newHashMap());
     * }
     * </pre>
     * @param map                   目标参数
     * @param supplier              默认值, 懒加载函数
     * @param <T>                   map类型
     * @param <K>                   map key类型
     * @param <V>                   map value类型
     * @return                      null时返回map, 否则返回defaultMap
     * @see Objects#defaultIfNull(Object, Supplier)
     * @since  1.0.7
     */
    public static <T extends Map<K, V>, K, V> T defaultIfNull(final T map, final Supplier<? extends T> supplier) {
        return isNull(map) ? supplier.get() : map;
    }

    /**
     * 当map为null或{}时返回默认值, 否则返回目标参数
     * <pre>{@code
     * LinkedHashMap<String, String> map2 = Maps.newLinkedHashMap();
     * LinkedHashMap<String, String> map4 = Maps.newLinkedHashMap();
     * map4.put("1","1");
     * HashMap<String, String> map3 = CollectionUtil.defaultIfEmpty(map2, () -> map4);
     * }
     * </pre>
     * @param map                   目标参数
     * @param supplier              默认值, 懒加载函数
     * @param <T>                   map类型
     * @param <K>                   map key类型
     * @param <V>                   map value类型
     * @return                      null或{}时返回map, 否则返回defaultMap
     * @see Objects#defaultIfNull(Object, Supplier)
     * @since 1.0.7
     */
    public static <T extends Map<K, V>, K, V> T defaultIfEmpty(final T map, final Supplier<? extends T> supplier) {
        return isEmpty(map) ? supplier.get() : map;
    }

    /**
     * 截取集合中的内容<br>
     * <pre>
     *     1.当start(截取起始位置)小于0时, 截取起始位置等于0
     *     2.当start(截取起始位置)大于或等于被截取集合size时, 返回空数组/[]
     *     3.当length(截取长度)小于或等于0时, 返回空数组
     *     4.截取后的集合长度小于或等于length参数
     *     5.被截取集合为空或空数组, 返回空数组/[]
     * </pre>
     * @param list                  被截取内容
     * @param start                 截取起始位置, 包含该位置
     * @param length                截取长度
     * @param <T>                   集合中元素类型
     * @return                      截取后的集合
     * @since 1.0.7
     */
    public static <T> List<T> sub(List<T> list, int start, int length) {
        if (isEmpty(list) || length <= 0) {
            return Lists.newArrayListWithSize(0);
        }
        int s = Math.max(start, 0);
        final int size = list.size();
        if (s > size - 1) {
            return Lists.newArrayListWithSize(0);
        }
        final List<T> result = Lists.newArrayListWithSize(Math.min(length, size));
        for (int i = s; i < s + length; i++) {
            if (i < size) {
                result.add(list.get(i));
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * 截取集合中的内容<br>
     * <pre>
     *     1.当start(截取起始位置)小于0时, 截取起始位置等于0
     *     2.当start(截取起始位置)大于或等于被截取集合size时, 返回空数组/[]
     *     3.当length(截取长度)小于或等于0时, 返回空数组
     *     4.截取后的集合长度小于或等于length参数
     *     5.被截取集合为空或空数组, 返回空数组/[]
     * </pre>
     * @param collection            被截取内容
     * @param start                 截取起始位置, 包含该位置
     * @param length                截取长度
     * @param <T>                   集合中元素类型
     * @return                      截取后的集合
     * @since 1.0.7
     */
    public static <T> List<T> sub(Collection<T> collection, int start, int length) {
        if (isEmpty(collection)) {
            return Lists.newArrayListWithSize(0);
        }
        final List<T> list = collection instanceof List ? (List<T>) collection : Lists.newArrayList(collection);
        return sub(list, start, length);
    }
}
