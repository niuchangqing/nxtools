package cn.nxtools.common.collect;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * set集合操作
 */
public final class Sets {

    /**
     * 私有构造方法
     */
    private Sets() {}

    /**
     * 创建HashSet集合
     * @param <E>               E
     * @return                  HashSet
     */
    public static <E> HashSet<E> newHashSet() {
        return new HashSet<E>();
    }

    /**
     * 创建HashSet集合,并初始化数据
     * 数组转HashSet
     * @param elements          数组参数
     * @param <E>               E
     * @return                  HashSet
     */
    public static <E> HashSet<E> newHashSet(E... elements) {
        checkNotNull(elements);
        HashSet<E> hashSet = new HashSet<>(elements.length);
        Collections.addAll(hashSet,elements);
        return hashSet;
    }

    /**
     * 创建HashSet集合,并初始化数据
     * 可用于List,Set,Vector转HashSet
     * @param elements              初始化参数集合
     * @param <E>                   E
     * @return                      HashSet
     */
    public static <E> HashSet<E> newHashSet(Iterable<? extends E> elements) {
        checkNotNull(elements);
        HashSet<E> set = newHashSet();
        Iterables.addAll(set, elements);
        return set;
    }

    /**
     * 创建HashSet集合,并初始化数据
     * <p>
     *     如:set.iterator();
     * </p>
     * @param elements              初始化参数,Iterator迭代器
     * @param <E>                   E
     * @return                      HashSet
     */
    public static <E> HashSet<E> newHashSet(Iterator<? extends E> elements) {
        HashSet<E> set = newHashSet();
        Iterators.addAll(set, elements);
        return set;
    }

    /**
     * 创建指定大小的HashSet集合
     * @param size                  大小值
     * @param <E>                   E
     * @return                      HashSet
     */
    public static <E> HashSet<E> newHashSetWithSize(int size) {
        return new HashSet<>(size);
    }

    /**
     * 创建一个线程安全的Set集合
     * @param <E>                   E
     * @return                      Set
     */
    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap<E, Boolean>());
    }

    /**
     * 创建一个线程安全的Set集合,并初始化数据
     * @param elements              数组参数
     * @param <E>                   E
     * @return                      Set
     */
    public static <E> Set<E> newConcurrentHashSet(E... elements) {
        checkNotNull(elements);
        Set<E> set = newConcurrentHashSet(elements.length);
        Collections.addAll(set,elements);
        return set;
    }

    /**
     * 创建一个线程安全的Set集合,并初始化数据
     * @param elements              集合参数
     * @param <E>                   E
     * @return                      Set
     */
    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> elements) {
        checkNotNull(elements);
        Set<E> set = newConcurrentHashSet();
        Iterables.addAll(set, elements);
        return set;
    }

    /**
     * 创建一个线程安全的Set集合,并初始化数据
     * @param elements                  集合参数
     * @param <E>                       E
     * @return                          Set
     */
    public static <E> Set<E> newConcurrentHashSet(Iterator<? extends E> elements) {
        Set<E> set = newConcurrentHashSet();
        Iterators.addAll(set, elements);
        return set;
    }

    /**
     * 创建一个线程安全的Set集合,并指定集合大小
     * @param size                  大小
     * @param <E>                   E
     * @return                      Set
     */
    public static <E> Set<E> newConcurrentHashSet(int size) {
        return Collections.newSetFromMap(new ConcurrentHashMap<E, Boolean>(size));
    }

    /**
     * 创建LinkedHashSet集合
     * @param <E>                   E
     * @return                      LinkedHashSet
     */
    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    /**
     * 创建LinkedHashSet集合,并初始化数据
     * 数组转LinkedHashSet
     * @param elements                  数组参数
     * @param <E>                       E
     * @return                          LinkedHashSet
     */
    public static <E> LinkedHashSet<E> newLinkedHashSet(E... elements) {
        checkNotNull(elements);
        return new LinkedHashSet<E>(Arrays.asList(elements));
    }

    /**
     * 创建LinkedHashSet集合,并初始化数据
     * 可用于List,Set,Vector转LinkedHashSet
     * @param elements                  集合参数
     * @param <E>                       E
     * @return                          LinkedHashSet
     */
    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> elements) {
        checkNotNull(elements);
        LinkedHashSet<E> set = newLinkedHashSet();
        Iterables.addAll(set, elements);
        return set;
    }

    /**
     * 创建LinkedHashSet集合,并初始化数据
     * <p>
     *     如:list.iterator();
     * </p>
     * @param elements                  参数,Iterator迭代器
     * @param <E>                       E
     * @return                          LinkedHashSet
     */
    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterator<? extends E> elements) {
        LinkedHashSet<E> set = newLinkedHashSet();
        Iterators.addAll(set, elements);
        return set;
    }

    /**
     * 创建指定大小的LinkedHashSet集合
     * @param size                      大小
     * @param <E>                       E
     * @return                          LinkedHashSet
     */
    public static <E> LinkedHashSet<E> newLinkedHashSet(int size) {
        return new LinkedHashSet<>(size);
    }

    /**
     * 创建TreeSet集合
     * @param <E>               E
     * @return                  TreeSet
     */
    public static <E> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    /**
     * 创建TreeSet集合,并初始化数据
     * 可用于数组转TreeSet
     * @param elements              数组参数
     * @param <E>                   E
     * @return                      TreeSet
     */
    public static <E> TreeSet<E> newTreeSet(E... elements) {
        checkNotNull(elements);
        return new TreeSet<>(Arrays.asList(elements));
    }

    /**
     * 创建TreeSet集合,并初始化数据
     * 可用户Set,List等集合转TreeSet
     * @param elements              集合参数
     * @param <E>                   E
     * @return                      TreeSet
     */
    public static <E> TreeSet<E> newTreeSet(Iterable<? extends E> elements) {
        checkNotNull(elements);
        TreeSet<E> set = newTreeSet();
        Iterables.addAll(set, elements);
        return set;
    }

    /**
     * 创建TreeSet集合,并初始化数据
     * @param elements              集合参数
     * @param <E>                   E
     * @return                      TreeSet
     */
    public static <E> TreeSet<E> newTreeSet(Iterator<? extends E> elements) {
        TreeSet<E> set = newTreeSet();
        Iterators.addAll(set, elements);
        return set;
    }

}
