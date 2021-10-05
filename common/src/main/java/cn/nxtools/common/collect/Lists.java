package cn.nxtools.common.collect;

import java.util.*;

import static cn.nxtools.common.base.Preconditions.checkNotNull;

/**
 * @author niuchangqing
 * 集合操作工具类
 */
public final class Lists {

    private Lists(){}

    /**
     * 创建ArrayList集合
     * @param <E>               E
     * @return                  a empty ArrayList
     */
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * 创建ArrayList集合,并初始化数据
     * 可用于数组转ArrayList集合
     * @param elements                  初始化数据参数
     * @param <E>                       E
     * @return                          ArrayList
     */
    public static <E> ArrayList<E> newArrayList(E... elements) {
        checkNotNull(elements);
        ArrayList<E> list = new ArrayList<>(elements.length);
        Collections.addAll(list,elements);
        return list;
    }


    /**
     * 创建ArrayList集合,并初始化数据
     * 可用于Set,Vector,List等集合类型转ArrayList
     * @param elements                  初始化数据参数
     * @param <E>                       E
     * @return                          ArrayList
     */
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
        checkNotNull(elements);
        ArrayList<E> list = newArrayList();
        Iterables.addAll(list,elements);
        return list;
    }


    /**
     * 创建ArrayList集合,并初始化数据
     * <p>
     *     如:set.iterator();
     * </p>
     * @param elements                  初始化数据参数,支持Iterator迭代器
     * @param <E>                       E
     * @return                          ArrayList
     */
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list = newArrayList();
        Iterators.addAll(list,elements);
        return list;
    }

    /**
     * 创建指定大小的ArrayList集合
     * @param size                      指定大小
     * @param <E>                       E
     * @return                          ArrayList
     */
    public static <E> ArrayList<E> newArrayListWithSize(int size) {
        return new ArrayList<>(size);
    }

    /**
     * 创建LinkedList集合
     * @param <E>                       E
     * @return                          LinkedList
     */
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * 创建LinkedList集合,并初始化数据
     * 可用于数组转LinkedList集合
     * @param elements                  数组
     * @param <E>                       E
     * @return                          LinkedList
     */
    public static <E> LinkedList<E> newLinkedList(E... elements) {
        checkNotNull(elements);
        return new LinkedList<>(Arrays.asList(elements));
    }

    /**
     * 创建LinkedList集合,并初始化数据
     * 可用于Set,Vector,List等集合类型转ArrayList
     * @param elements                  集合参数
     * @param <E>                       E
     * @return                          LinkedList
     */
    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
        checkNotNull(elements);
        LinkedList<E> list = newLinkedList();
        Iterables.addAll(list, elements);
        return list;
    }

    /**
     * 创建LinkedList集合,并初始化数据
     * <p>
     *     如: set.iterator();
     * </p>
     * @param elements                  参数,Iterator迭代器
     * @param <E>                       E
     * @return                          LinkedList
     */
    public static <E> LinkedList<E> newLinkedList(Iterator<? extends E> elements) {
        LinkedList<E> list = newLinkedList();
        Iterators.addAll(list, elements);
        return list;
    }
}
