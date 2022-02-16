package cn.nxtools.common.collect;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap;

import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * map工具类
 */
public final class Maps {
    private Maps() {}

    /**
     * 创建HashMap
     * @param <K>           K
     * @param <V>           V
     * @return              a empty HashMap
     */
    public static <K, V> HashMap<K,V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * 创建HashMap,并初始化数据
     * 可用与TreeMap,LinkedHashMap等转HashMap
     * @param map               HashMap,TreeMap等等Map实现
     * @param <K>               K
     * @param <V>               V
     * @return                  HashMap
     */
    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        checkNotNull(map);
        return new HashMap<>(map);
    }

    /**
     * 创建指定大小的HashMap
     * @param size              大小
     * @param <V>               V
     * @param <K>               K
     * @return                  HashMap
     */
    public static <K, V> HashMap<K, V> newHashMapWithSize(int size) {
        return new HashMap<>(size);
    }

    /**
     * 创建LinkedHashMap
     * @param <V>               V
     * @param <K>               K
     * @return                  a empty LinkedHashMap
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * 创建LinkedHashMap,并初始化数据
     * 可用户HashMap,TreeMap等map转LinkedHashMap
     * @param map               HashMap,TreeMap等map参数
     * @param <V>               V
     * @param <K>               K
     * @return                  LinkedHashMap
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        checkNotNull(map);
        return new LinkedHashMap<>(map);
    }

    /**
     * 创建指定大小的LinkedHashMap
     * @param size              大小参数
     * @param <V>               V
     * @param <K>               K
     * @return                  LinkedHashMap
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int size) {
        return new LinkedHashMap<>(size);
    }

    /**
     * 创建ConcurrentHashMap
     * @param <V>               V
     * @param <K>               K
     * @return                  a empty ConcurrentHashMap
     */
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap();
    }

    /**
     * 创建ConcurrentHashMap,并初始化数据
     * 可用于HashMap,LinkedHashMap等map转创建ConcurrentHashMap
     * @param map                       map参数
     * @param <K>                       K
     * @param <V>                       V
     * @return                          a ConcurrentHashMap
     */
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap(Map<? extends K, ? extends V> map) {
        return new ConcurrentHashMap<>(map);
    }

    /**
     * 创建指定大小的ConcurrentHashMap
     * @param size                  大小参数
     * @param <K>                   K
     * @param <V>                   V
     * @return                      ConcurrentHashMap
     */
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMapWithSize(int size) {
        return new ConcurrentHashMap<>(size);
    }

    /**
     * 创建TreeMap
     * @param <K>               K
     * @param <V>               V
     * @return                  a empty TreeMap
     */
    public static <K, V> TreeMap<K,V> newTreeMap() {
        return new TreeMap<>();
    }

    /**
     * 创建TreeMap,并初始化数据
     * 可用于HashMap,ConcurrentHashMap等map转TreeMap
     * @param map               HashMap,TreeMap等map参数
     * @param <K>               K
     * @param <V>               V
     * @return                  TreeMap
     */
    public static <K, V> TreeMap<K,V> newTreeMap(Map<? extends K, ? extends V> map) {
        checkNotNull(map);
        return new TreeMap<>(map);
    }

    /**
     * 创建WeakHashMap
     * @param <K>               K
     * @param <V>               V
     * @return                  a empty WeakHashMap
     */
    public static <K, V> WeakHashMap<K, V> newWeakHashMap() {
        return new WeakHashMap<>();
    }

    /**
     * 创建WeakHashMap,并初始化数据
     * @param map               HashMap,TreeMap等map参数
     * @param <K>               K
     * @param <V>               V
     * @return                  WeakHashMap
     */
    public static <K, V> WeakHashMap<K, V> newWeakHashMap(Map<? extends K, ? extends V> map) {
        checkNotNull(map);
        return new WeakHashMap<>(map);
    }

    /**
     * 创建指定大小的WeakHashMap
     * @param size              数量大小参数
     * @param <K>               K
     * @param <V>               V
     * @return                  a empty WeakHashMap
     */
    public static <K, V> WeakHashMap<K, V> newWeakHashMap(int size) {
        return new WeakHashMap<>(size);
    }
}
