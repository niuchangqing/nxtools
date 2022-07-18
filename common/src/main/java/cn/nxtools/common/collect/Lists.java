package cn.nxtools.common.collect;

import cn.nxtools.common.CollectionUtil;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static cn.nxtools.common.base.Preconditions.checkState;
import static cn.nxtools.common.base.Objects.isNull;

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
        if (isNull(elements)) {
            return newArrayList();
        }
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
        if (isNull(elements)) {
            return newArrayList();
        }
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
        if (isNull(elements)) {
            return newArrayList();
        }
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
        if (isNull(elements)) {
            return newLinkedList();
        }
        return new LinkedList<>(Arrays.asList(elements));
    }

    /**
     * 创建LinkedList集合,并初始化数据
     * 可用于Set,Vector,List等集合类型转LinkedList
     * @param elements                  集合参数
     * @param <E>                       E
     * @return                          LinkedList
     */
    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
        if (isNull(elements)) {
            return newLinkedList();
        }
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

    /**
     * <p>
     * 按照指定长度拆分list, 调用{@link List#subList(int, int)}方法, <br>
     * 此方法返回的是原list的数据, 原list发生改变, 拆分后的结果也会发生改变
     * </p>
     * @param list                      待拆分list
     * @param size                      分段长度
     * @param <E>                       E
     * @return                          拆分后的结果
     * @since 1.0.7
     */
    public static <E> List<List<E>> partition(List<E> list, int size) {
        if (CollectionUtil.isEmpty(list)) {
            //集合为空或长度等于0, 该list不可变
            return Collections.emptyList();
        }
        checkState(size > 0, "size must be greater than 0");
        if (list instanceof RandomAccess) {
            return new RandomAccessPartition<>(list, size);
        } else {
            return new Partition<>(list, size);
        }
    }

    /**
     * 创建CopyOnWriteArrayList
     * @param <E>                       E
     * @return                          CopyOnWriteArrayList
     * @since 1.0.7
     */
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    /**
     * 创建CopyOnWriteArrayList,并给定初始值<br>
     * example: <br>
     * {@code Lists.CopyOnWriteArrayList("1","2");}
     * @param elements                  初始化参数
     * @param <E>                       E
     * @return                          CopyOnWriteArrayList
     * @since 1.0.7
     */
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(E... elements) {
        if (isNull(elements)) {
            return newCopyOnWriteArrayList();
        }
        return new CopyOnWriteArrayList<>(elements);
    }

    /**
     * 创建newCopyOnWriteArrayList <br>
     * example: <br>
     * {@code List<String> list = Lists.newArrayList("1", "2");}<br>
     * {@code Lists.newCopyOnWriteArrayList(list);}
     * @param elements                  {@link Iterable}
     * @param <E>                       E
     * @return                          CopyOnWriteArrayList
     * @since 1.0.7
     */
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> elements) {
        if (isNull(elements)) {
            return newCopyOnWriteArrayList();
        }
        Collection<? extends E> collection = (elements instanceof Collection) ? (Collection<? extends E>) elements : newArrayList(elements);
        return new CopyOnWriteArrayList<>(collection);
    }

    /**
     * 创建创建newCopyOnWriteArrayList <br>
     * example: <br>
     * {@code Set<Integer> sets = Sets.newHashSet("1", "2");} <br>
     * {@code Lists.newCopyOnWriteArrayList(sets.iterator());} <br>
     * @param elements                  {@link Iterators}
     * @param <E>                       E
     * @return                          CopyOnWriteArrayList
     * @since 1.0.7
     */
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterator<? extends E> elements) {
        if (isNull(elements)) {
            return newCopyOnWriteArrayList();
        }
        CopyOnWriteArrayList<E> copyOnWriteArrayList = newCopyOnWriteArrayList();
        Iterators.addAll(copyOnWriteArrayList, elements);
        return copyOnWriteArrayList;
    }

    private static class RandomAccessPartition<E extends Object> extends Partition<E> implements RandomAccess {

        public RandomAccessPartition(List<E> list, int size) {
            super(list, size);
        }
    }

    private static class Partition<E extends Object> extends AbstractList<List<E>> {

        /**
         * 被拆分的list
         */
        protected final List<E> list;

        /**
         * 拆分分段大小
         */
        protected final int size;

        public Partition(List<E> list, int size) {
            this.list = list;
            this.size = Math.min(size, list.size());
        }

        @Override
        public List<E> get(int index) {
            int start = index * this.size;
            int end = Math.min(start + this.size, list.size());
            return list.subList(start, end);
        }

        @Override
        public int size() {
            final int total = list.size();
            int length = total / this.size;
            if (total % this.size > 0) {
                length += 1;
            }
            return length;
        }
    }
}
