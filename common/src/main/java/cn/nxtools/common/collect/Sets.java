package cn.nxtools.common.collect;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static cn.nxtools.common.base.Objects.isNull;
import static cn.nxtools.common.CollectionUtil.isEmpty;

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
        if (isNull(elements)) {
            return newHashSet();
        }
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
        if (isNull(elements)) {
            return newHashSet();
        }
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
        if (isNull(elements)) {
            return newHashSet();
        }
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
        if (isNull(elements)) {
            return newConcurrentHashSet();
        }
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
        if (isNull(elements)) {
            return newConcurrentHashSet();
        }
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
        if (isNull(elements)) {
            return newConcurrentHashSet();
        }
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
        return isNull(elements) ? newLinkedHashSet() : new LinkedHashSet<E>(Arrays.asList(elements));
    }

    /**
     * 创建LinkedHashSet集合,并初始化数据
     * 可用于List,Set,Vector转LinkedHashSet
     * @param elements                  集合参数
     * @param <E>                       E
     * @return                          LinkedHashSet
     */
    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> elements) {
        if (isNull(elements)) {
            return newLinkedHashSet();
        }
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
        if (isNull(elements)) {
            return newLinkedHashSet();
        }
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
        return isNull(elements) ? newTreeSet() : new TreeSet<>(Arrays.asList(elements));
    }

    /**
     * 创建TreeSet集合,并初始化数据
     * 可用户Set,List等集合转TreeSet
     * @param elements              集合参数
     * @param <E>                   E
     * @return                      TreeSet
     */
    public static <E> TreeSet<E> newTreeSet(Iterable<? extends E> elements) {
        if (isNull(elements)) {
            return newTreeSet();
        }
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
        if (isNull(elements)) {
            return newTreeSet();
        }
        TreeSet<E> set = newTreeSet();
        Iterators.addAll(set, elements);
        return set;
    }

    /**
     * 取集合交集, 所有集合中都包含的元素即为交集
     * <pre>{@code
     * // 实例:
     * Set<String> set1 = Sets.newHashSet("1", "2", "3");
     * Set<String> set2 = Sets.newHashSet("2", "3", "4", "5");
     * Set<String> set3 = Sets.newHashSet("3", "4", "5", "6");
     * Set<String> result = Sets.intersection(set1, set2, set3);
     * // 结果: ["3"]
     * }</pre>
     * @param set1          参数1
     * @param set2          参数2
     * @param sets          其他参数
     * @param <E>           集合元素
     * @return              返回交集集合(LinkedHashSet), 不会返回null
     * @since 1.0.7
     */
    public static <E> Set<E> intersection(final Set<E> set1, final Set<E> set2, final Set<E>... sets) {
        if (isEmpty(set1) || isEmpty(set2) || isNull(sets)) {
            return newLinkedHashSet();
        }
        Set<E> result = intersection(set1, set2);
        for (Set<E> s : sets) {
            result = intersection(result, s);
            if (isEmpty(result)) {
                // 存在空集合直接返回
                return newLinkedHashSet();
            }
        }
        return result;
    }

    /**
     * 取集合交集, 所有集合中都包含的元素即为交集
     * <pre>{@code
     * // 实例:
     * Set<String> set1 = Sets.newHashSet("1", "2", "3");
     * Set<String> set2 = Sets.newHashSet("2", "3", "4", "5");
     * Set<String> result = Sets.intersection(set1, set2);
     * // 结果: ["2", "3"]
     * }</pre>
     * @param set1          参数1
     * @param set2          参数2
     * @param <E>           集合元素
     * @return              返回交集集合(LinkedHashSet), 不会返回null
     * @since 1.0.7
     */
    public static <E> Set<E> intersection(final Set<E> set1, final Set<E> set2) {
        if (isEmpty(set1) || isEmpty(set2)) {
            return newLinkedHashSet();
        }
        return set1.stream().filter(f -> set2.contains(f)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * 取 set1 - set2 的差集, 在set1集合中有的元素在set2集合中不存在的元素
     * <pre>{@code
     * // 实例:
     * Set<String> set1 = Sets.newHashSet("1", "2", "3", "4");
     * Set<String> set2 = Sets.newHashSet("3", "4", "5");
     * Set<String> result = Sets.difference(set1, set2);
     * // 结果: ["1", "2"]
     * }</pre>
     * @param set1          set1
     * @param set2          set2
     * @param <E>           集合元素
     * @return              返回差集集合(LinkedHashSet), 不会返回null
     * @since 1.0.7
     */
    public static <E> Set<E> difference(final Set<E> set1, final Set<E> set2) {
        if (isEmpty(set1)) {
            return newLinkedHashSet();
        }
        if (isEmpty(set2)) {
            // 集合2为空, 直接返回集合1
            return newLinkedHashSet(set1);
        }
        return set1.stream().filter(f -> !set2.contains(f)).collect(Collectors.toCollection(LinkedHashSet::new));
    }


    /**
     * 取集合并集, 所有集合中的元素
     * <pre>{@code
     * // 实例:
     * Set<String> set1 = Sets.newHashSet("1", "2", "3");
     * Set<String> set2 = Sets.newTreeSet("3", "4", "5");
     * Set<String> set3 = Sets.newLinkedHashSet("6");
     * Set<String> result5 = Sets.union(set1, set2, set3);
     * // 结果: ["1", "2", "3", "4", "5", "6"]
     * }</pre>
     * @param set1          set集合参数1
     * @param set2          set集合参数2
     * @param sets          其他集合参数
     * @param <E>           集合元素
     * @return              返回并集集合(LinkedHashSet), 不会返回null
     * @since 1.0.7
     */
    public static <E> Set<E> union(final Set<E> set1, final Set<E> set2, final Set<E>... sets) {
        Set<E> union = union(set1, set2);
        if (isNull(sets)) {
            return union;
        }
        for (Set<E> s : sets) {
            union = union(union, s);
        }
        return union;
    }

    /**
     * 取集合并集, set1 + set2集合中的所有元素
     * <pre>{@code
     * // 实例:
     * Set<String> set1 = Sets.newHashSet("1", "2", "3");
     * Set<String> set2 = Sets.newTreeSet("3", "4", "5");
     * Set<String> result = Sets.union(set1, set2);
     * // 结果: ["1", "2", "3", "4", "5"]
     * }</pre>
     * @param set1          set集合参数1
     * @param set2          set集合参数2
     * @param <E>           集合元素
     * @return              返回并集集合(LinkedHashSet), 不会返回null
     * @since 1.0.7
     */
    public static <E> Set<E> union(final Set<E> set1, final Set<E> set2) {
        if (isEmpty(set1)) {
            return newLinkedHashSet(set2);
        }
        if (isEmpty(set2)) {
            return newLinkedHashSet(set1);
        }
        LinkedHashSet<E> result = newLinkedHashSet(set1);
        result.addAll(set2);
        return result;
    }
}
